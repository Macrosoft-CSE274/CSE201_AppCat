import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class User implements Serializable{
    public int identifier;
    public User(int identifier){
        this.identifier = identifier;
    }

}
