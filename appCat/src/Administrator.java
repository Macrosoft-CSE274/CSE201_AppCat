/**
 * Created by Kunting Qi on 2017/10/22.
 * This class represent the Administor Object
 * It is the subclass of UserAccount
 */
public class Administrator extends UserAccount {

    /**
     * the constructor of this class
     * @param name name of the administrator
     * @param password password for this account
     * @param identifier identifier to represent whether it is a UserAccount, moderator, Administartor, or Visitor
     */
    public Administrator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }

    /**
     * get the identifier
     * @return the int of identifier
     */
    public int getIdentifier()
    {
        return super.getIdentifier();
    }
}
