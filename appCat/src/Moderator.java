import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This class represent the Moderator obejct
 * who have some behavior with user but can delete the comment of an app
 */
public class Moderator extends UserAccount implements Serializable {

    /**
     * the constructor of this class
     * @param name name of the moderator
     * @param password password for this account
     * @param identifier identifier to represent whether it is a UserAccount, moderator, Administartor, or Visitor
     */
    public Moderator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }

    /**
     * get the identifier
     * @return the int of the identifier
     */
    public int getIdentifier()
    {
        return super.identifier;
    }
}
