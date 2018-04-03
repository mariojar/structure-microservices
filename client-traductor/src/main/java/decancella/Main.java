package decancella;

public class Main {
    public static void main(String[] args) {

        BlogImpl blog, blog2;
        
        Blog.Manager blogManager = new BlogManagerImpl();;
        BlogImpl.setManager( blogManager );
        
        blog = (BlogImpl) BlogImpl.getManager().load ( "John" );
        blog2 = (BlogImpl) BlogImpl.getManager().load ( "Joanna" );
    }
}
