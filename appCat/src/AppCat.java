import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This is the main program for thsi project
 * THis program nearly hold most of the opratetion
 * between the users and the repository
 *
 * When object of this class is created
 * a defauly visitor will be assign to the user
 * after calling the login method, the current user will switch to
 * another user in the repository
 *
 * by getting the identifier of the current user
 * the current user will have different access to kinds of operation
 *
 */
public class AppCat implements Serializable {

    /**
     * this is the identifier for users
     * 1 is the visitor
     * 2 is the User Account
     * 3 is the Moderator
     * 4 is the Administrator
     */
    public static int VISITOR = 1;
    public static int USER = 2;
    public static int MODERATOR = 3;
    public static int ADMINISTRATOR = 4;

    private Repository infoStored;
    private User currentUser;

    /**
     * this is the constructor of this class
     * start the repository
     * and create a defauly visitor user
     */
    public AppCat()
    {
        infoStored = new Repository(); // I don't know should I use singeleton design pattern at there...
        currentUser = new User(1);
    }

    /**
     * this login process which is actually switch to another user
     * @param username the username of the account in String
     * @param password the password of the account in String
     */
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

    /**
     * the current user will submit a app request
     * @param name name of the app
     * @param developer name of the developer
     * @param platform name of the platform
     * @param version
     * @param description
     * @param webLink
     * @return
     */
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
