package decancella;

public interface Blog {

    public interface Manager {
        //-- The argument to the load() method is entity's PK
        public Blog load( String name );
        public void save( Blog blog );
        public void remove( Blog blog);
    }

    //-- The rest of the interface goes below
    // .....
}
