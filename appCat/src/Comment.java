import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Comment implements Serializable {
    public String userComment;

    public Comment(String input)
    {
        userComment = input;
    }
}
