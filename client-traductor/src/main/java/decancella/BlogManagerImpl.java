package decancella;

public class BlogManagerImpl implements Blog.Manager {

    public BlogManagerImpl() {}

    public void save( Blog blog) {
        //-- Save the state of the Blog object, into a database
    }

    public Blog load( String name) {
        //-- Load the Blog instance from a database.
        //-- e.g. using hibernate:
        //-- return (BlogImpl) session.load( BlogImpl.class, name );
        
        //-- this is just for the demonstration purposes
        BlogImpl blog = new BlogImpl();
        blog.setName ( name );
        
        System.out.println ( "Loaded blog: " + blog.getName() );
        return blog;
    }

    public void remove( Blog blog) {
        //-- Remove the persisted state of the object from the
        //-- database..
    }

}
