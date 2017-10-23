import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This is simply a repository to store the information
 * I think later we will use the MySql to replace or improve this class
 */
public class Repository implements Serializable{
    private ArrayList<UserAccount> users;
    private ArrayList<App> apps;
    private ArrayList<AppSubmit> requests;

    /**
     * the constructor
     */
    public Repository()
    {
        this.users = new ArrayList<>();
        apps = new ArrayList<>();
        requests = new ArrayList<>();
    }

    /**
     * get the list of user
     * @return list of user
     */
    public ArrayList<UserAccount> getUsers()
    {
        return users;
    }

    /**
     * get list of apps
     * @return list of apps
     */
    public ArrayList<App> getApps()
    {
        return apps;
    }

    /**
     * list of app submit request
     * @return list of app request
     */
    public ArrayList<AppSubmit> request()
    {
        return requests;
    }

    /**
     * add a new user
     * @param newOne new User
     * @return  whether the process is successful
     */
    public boolean addUser(UserAccount newOne)
    {
        return users.add(newOne);
    }

    /**
     * add a new app
     * @param newOne the new app
     * @return whether the process is successful
     */
    public boolean addApp(App newOne)
    {
        return apps.add(newOne);
    }

    /**
     * add a new request
     * @param newOne the new request
     * @return whether the process is successful
     */
    public boolean addRequest(AppSubmit newOne)
    {
        return requests.add(newOne);
    }

    /**
     * whether the user has existed in the collection
     * @param username the username of the user
     * @return user exist or not
     */
    public boolean containsUser(String username)
    {
        for(UserAccount each : users)
        {
            if (each.getUsername().equals(username))
                return true;
        }
        return false;
    }


}
