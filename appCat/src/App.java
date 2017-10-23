import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class App{

    public String name;
    public String developer;
    public String platform;
    public String versions;
    public String webLink;
    public ArrayList<Comment> commentList;

    public App(String name, String developer, String platform, String versions, String webLink)
    {
        this.name = name;
        this.developer = developer;
        this.platform = platform;
        this.webLink = webLink;
        this.versions = versions;
        this.commentList = new ArrayList<>();

    }

    public boolean addComment(Comment newOne)
    {
        return commentList.add(newOne);
    }

    public boolean removeComment(Comment choosed) {
        return commentList.remove(choosed);
    }


}
