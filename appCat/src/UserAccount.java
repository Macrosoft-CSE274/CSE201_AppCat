import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This class represent the  UserAccount object
 * the registerd user account have some behavior with visitor
 * but can submit app request, and add comment to an app
 */
public class UserAccount extends User implements Serializable {
    private String username;
    private String password;

    /**
     * the constructor of this class
     * @param name name of the UserAccount
     * @param password password for this account
     * @param identifier identifier to represent whether it is a UserAccount, moderator, Administartor, or Visitor
     */
    public UserAccount(String name, String password, int identifier)
    {
        super(identifier);
        this.username = name;
        this.password = password;
    }


    /**
     * get the username is String
     * @return username is string
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * get the password in String
     * @return password in String
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * get the identifier in integer
     * @return the identifier in Integer
     */
    public int getIdentifier()
    {
        return super.identifier;
    }
}
