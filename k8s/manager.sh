#!/bin/sh

tab1="\t"
tab2="\t\t"
tab3="\n\t\t\t"



declare -A modules
modules[1]=scheduler
modules[2]=billing
modules[3]=rule-engine
modules[4]=oracolo
modules[5]=quote-engine

declare -A directory_projects
directory_projects[1]=scheduler-core
directory_projects[2]=lancio-billing
directory_projects[3]=lancio-ruleengine
directory_projects[4]=oracolo
directory_projects[5]=quote-engine

project_name="lancio-infrastructure" 
cluster_name="cluster-dev-lancio"
echo "THIS CONFIGURATION WORK WITH THIS : " $project_name 
echo "THIS CONFIGURATION WORK CLUSTER NAME : " $cluster_name 



create_all(){

 	gcloud container clusters get-credentials lasis-cluster --zone europe-west1-b --project lasis-271808
	
	kubectl create -f namespace.yml
	sleep 5
	echo "Waiting create namespace ...."	
	kubectl get namespace
	echo "Current CONTEXT : " $(kubectl config current-context)
 	kubectl config set-context $(kubectl config current-context) --namespace=namespace-dev-lancio
	sleep 5
	echo "Waiting setup current-context ...."	
	kubectl apply -f authorization.yml
	kubectl create -f secrets-db.yml 

	for module in ${modules[*]}
	do(
		cd $module

		echo "Creating resource $module ...."	
		kubectl create -f configmap.yml
		kubectl create -f service.yml
		kubectl create -f loadBalancer.yml
	    kubectl create -f deployment.yml

	    if [ $module == ${modules[1]} ]; then
			echo "giving time to scheduler to startup, wait for 30 seconds to continue ...."
			sleep 30
		fi
	    cd ..
	)
	done
}

delete_all(){

	for module in ${modules[*]}
	do(
		echo "Deleting resource $module ...."	
		kubectl delete deployment $module
		kubectl delete configmap $module
		kubectl delete svc $module
    	kubectl delete svc "$module-service-lb"
    )
	
	kubectl delete secret db-aws
	kubectl delete namespaces namespace-dev-lancio 
	kubectl config delete-context $(kubectl config current-context)

	done
}

logo(){
clear	
echo -e "$tab1 .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------. "
echo -e "$tab1| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |"
echo -e "$tab1| |              | || |              | || |              | || |              | || |              | || |              | |"
echo -e "$tab1| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |"
echo -e "$tab1 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' "
}

main_menu() {
	logo
	echo -e "$tab2**********************************************************"
	echo -e "$tab2******	    MAIN MENU - SELECT NUMBER OPTION   *********"
	echo -e "$tab2**********************************************************"
	
	echo -e "$tab3 1. Operate on single module"   
	echo -e "$tab3 2. Clean all infrastructure"   
	echo -e "$tab3 3. DELETE and Build all infrastructure  (ACTIVE)"
	echo -e "$tab3 4. Compile and publish all image"
	echo -e "$tab3 5. Compile and publish single image"
	echo -e "$tab3 6. Create resources"
	echo -e "$tab3 7. Exit" 

	read option
}

choose_module() {
	logo
	echo -e "$tab2*********************************************************"
	echo -e "$tab2******	     CHOOSE MODULE                  ***********"
	echo -e "$tab2*********************************************************"
	
	echo -e "$tab3 1. Scheduler"   
	echo -e "$tab3 2. Billing"   
	echo -e "$tab3 3. Rule-engine"   
	echo -e "$tab3 4. Oracolo"   
	echo -e "$tab3 5. Quote-Engine" 

	read choose_module
}

operate_module() {
	logo
	
	echo -e "$tab2*********************************************************************"
	echo -e "$tab2**** SELECT NUMBER OPERATION  ON   MODULE ${modules[$choose_module]} ***********"
	echo -e "$tab2*********************************************************************"
	
	echo -e "$tab3 1. Clean single resource (NOT DEVOLOPED)"   
	echo -e "$tab3 2. Clean ALL resource (NOT DEVOLOPED)"   
	echo -e "$tab3 3. Clean and BUILD ALL resource  for module ${modules[$choose_module]}  (ACTIVE)"
	echo -e "$tab3 5. Exit" 

	read operation
}

execute_operation() {

	case "$operation" in
		"3")

			echo "Deleting resource ...."
			kubectl delete deployment ${modules[$choose_module]}
    		kubectl delete configmap ${modules[$choose_module]}
  		   	kubectl delete svc ${modules[$choose_module]}
	    	kubectl delete svc "${modules[$choose_module]}-service-lb"
	    	

	    	cd ${modules[$choose_module]}
	    	echo "current directory "  $PWD

	    	echo "Creating resource ...."	
	    	kubectl create -f deployment.yml
	    	kubectl create -f configmap.yml
	    	kubectl create -f service.yml
	    	kubectl create -f loadBalancer.yml
	        ;;
		*)
		   echo "non faccio niente"

		    ;;
		esac
}

compile_publish_all(){

	cd ..
	mvn clean install package -DskipTests
	cd Docker
	
	for module in ${modules[*]}
	do(
		cd $module
		
		docker build -t gcr.io/$project_name/$module:v1 .
		gcloud docker -- push gcr.io/$project_name/$module:v1
		
		#./build-push-k8s.sh
		echo "current directory "  $PWD
		cd ..
	)
	done
}

compile_publish_single_module(){
	choose_module
	
	
	
	cd ../../${directory_projects[$choose_module]}/
	echo "current directory "  $PWD
	mvn clean install package -DskipTests
	cd ../lancio/Docker/${modules[$choose_module]}/
	echo "current directory "  $PWD
	
	
	
	
	docker build -t gcr.io/$project_name/${modules[$choose_module]}:v1 .
	gcloud docker -- push gcr.io/$project_name/${modules[$choose_module]}:v1
	
	#./build-push-k8s.sh
	kubectl delete pod  -l name=${modules[$choose_module]}
}

main_menu


case "$option" in
	"1")
	    choose_module
	    operate_module
	    execute_operation
	    ;;
	"2")
	    delete_all
	    ;;
	"3")
	    delete_all
	    create_all
	    ;;   
	"4")
	    compile_publish_all
	    ;; 
	"5")
	    compile_publish_single_module
	    ;; 
	"6")
	    create_all
	    ;;	
	*)
	   echo "non faccio niente"

	    ;;
esac



#for project in  $(gcloud config configurations list --format="value(PROJECT)")
#do
 # echo "TEST PROJECT:  $project"
