import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This is a comment class simply represent the comment object
 */
public class Comment implements Serializable {

    /**
     * this is the public variable for comment
     */
    public String userComment;

    /**
     * this is the constructor
     * @param input the content for the new Comment in String
     */
    public Comment(String input)
    {
        userComment = input;
    }
}
