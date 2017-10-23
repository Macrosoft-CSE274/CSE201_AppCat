/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class UserAccount extends User {
    private String username;
    private String password;

    public UserAccount(String name, String password, int identifier)
    {
        super(identifier);
        this.username = name;
        this.password = password;
    }


    public void submitAppReq(String name, String developer, String platform, String version, String description)
    {
        AppSubmit newOne = new AppSubmit(name, developer, platform, version, description);

    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public int getIdentifier()
    {
        return super.identifier;
    }
}
