package decancella;

public class BlogImpl implements Blog {

    private static Blog.Manager manager = null;
    public static Blog.Manager getManager () {
        return BlogImpl.manager;
    }
    public static void setManager ( Blog.Manager manager ) {
        BlogImpl.manager = manager;
    }

    public BlogImpl() {}

    private String name = null;
    public String getName() {
        return this.name;
    }
    public void setName( String name ) {
        this.name = name;
    }

    //-- Below go any other properties and methods relevant
    //-- to the Blog entity as a purely Domain Object.
    //.....
}
