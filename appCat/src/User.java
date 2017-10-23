import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This is the class represent the simple user
 * have basic behavior, browse apps, search and compare apps
 * This is simply a visitor
 * is also the super class of UserAccount
 */
public class User implements Serializable{
    /**
     * identifier user for indentify from visitor, user account, moderator, and administrator
     */
    public int identifier;

    /**
     * get the indentifier
     * @param identifier integer for identifier
     */
    public User(int identifier){
        this.identifier = identifier;
    }

}
