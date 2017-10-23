import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Moderator extends UserAccount implements Serializable {

    public Moderator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }


    public int getIdentifier()
    {
        return super.identifier;
    }
}
