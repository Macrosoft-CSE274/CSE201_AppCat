import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class AppCat implements Serializable {

    public static int VISITOR = 1;
    public static int USER = 2;
    public static int MODERATOR = 3;
    public static int ADMINISTRATOR = 4;

    private Repository infoStored;
    private User currentUser;

    public AppCat()
    {
        infoStored = new Repository(); // I don't know should I use singeleton design pattern at there...
        currentUser = new User(1);
    }

    public void login(String username, String password)
    {
        ArrayList<UserAccount> list = infoStored.getUsers();
        for(UserAccount each : list)
        {
            if(each.getUsername().equals(username) && each.getPassword().equals(password) )
            {
                currentUser = each;
                break;
            }
        }
    }

    public boolean submitAppReq(String name, String developer, String platform, String version, String description, String webLink)
    {
        if(currentUser.identifier == VISITOR)
        {
            return false;
        }
        AppSubmit newOne = new AppSubmit(name, developer, platform, version, description, webLink);
        infoStored.addRequest(newOne);
        return true;

    }

    public boolean addComment(App choosed, String content)
    {
        return choosed.addComment(new Comment(content));
    }

    public boolean deleteComment(Comment choosed, App appChoosed)
    {
        if(currentUser.identifier != MODERATOR && currentUser.identifier != ADMINISTRATOR)
            return false;

        appChoosed.removeComment(choosed);
        return true;
    }

    public boolean approveSub(AppSubmit submit)
    {
        if (currentUser.identifier != ADMINISTRATOR ) return false;
        submit.setStatus(AppSubmit.APPROVED);
        return true;
    }

    public boolean denySub(AppSubmit submit)
    {
        if (currentUser.identifier != ADMINISTRATOR ) return false;
        submit.setStatus(AppSubmit.DENIED);
        return true;
    }

    public boolean giveFeedBack(String feedback, AppSubmit submit)
    {
        if (currentUser.identifier != ADMINISTRATOR) return false;
        submit.setFeedback(feedback);
        return true;
    }

    public Repository getInfoStored()
    {
        return infoStored;
    }

    public void setInfoStored(Repository newOne)
    {
        infoStored = newOne;
    }


}
