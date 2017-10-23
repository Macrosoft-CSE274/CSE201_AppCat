import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Repository {
    private ArrayList<UserAccount> users;
    private ArrayList<App> apps;
    private ArrayList<AppSubmit> requests;

    public Repository()
    {
        this.users = new ArrayList<>();
        apps = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUsers()
    {
        return users;
    }

    public ArrayList<App> getApps()
    {
        return apps;
    }

    public ArrayList<AppSubmit> request()
    {
        return requests;
    }

    public boolean addUser(UserAccount newOne)
    {
        return users.add(newOne);
    }

    public boolean addApp(App newOne)
    {
        return apps.add(newOne);
    }

    public boolean addRequest(AppSubmit newOne)
    {
        return requests.add(newOne);
    }

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
