import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 * this App class represent App object
 * an application
 * has name, developer, platform information and etc
 * also can store the collection of comment
 */
public class App implements Serializable {

    public String name;
    public String developer;
    public String platform;
    public String versions;
    public String webLink;
    public ArrayList<Comment> commentList;

    /**
     * the constructor of app
     * @param name name of the app in String
     * @param developer name of the developer in String
     * @param platform the name of the platform it runs on in String
     * @param versions the version information in String
     * @param webLink the webLink it has in String
     */
    public App(String name, String developer, String platform, String versions, String webLink)
    {
        this.name = name;
        this.developer = developer;
        this.platform = platform;
        this.webLink = webLink;
        this.versions = versions;
        this.commentList = new ArrayList<>();

    }

    /**
     * add a comment to the app
     * @param newOne the new comment will be added
     * @return whether an comment is added sucessfully
     */
    public boolean addComment(Comment newOne)
    {
        return commentList.add(newOne);
    }

    /**
     * remove a specific comment from the app
     * @param choosed the comment choosed to be removed
     * @return whether the choosen comment was removed successfully
     */
    public boolean removeComment(Comment choosed) {
        return commentList.remove(choosed);
    }


}
