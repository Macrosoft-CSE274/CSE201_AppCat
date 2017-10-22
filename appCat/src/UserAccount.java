/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class UserAccount extends User {
    private String username;
    private String password;

    public UserAccount(String name, String password)
    {
        this.username = name;
        this.password = password;
    }


    public void submitAppReq(String name)
    {

    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
