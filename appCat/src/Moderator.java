/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Moderator extends UserAccount {

    public Moderator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }

    public void deleteComment(Comment choosed, App appChoosed)
    {
        appChoosed.removeComment(choosed);
    }

    public int getIdentifier()
    {
        return super.identifier;
    }
}
