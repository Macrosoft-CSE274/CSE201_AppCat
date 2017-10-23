import java.util.ArrayList;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class AppCat {

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


}
