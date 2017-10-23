/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Administrator extends UserAccount {

    public Administrator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }

    public int getIdentifier()
    {
        return super.getIdentifier();
    }
}
