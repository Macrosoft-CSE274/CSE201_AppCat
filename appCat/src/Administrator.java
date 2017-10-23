/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class Administrator extends UserAccount {

    public Administrator(String name, String password, int identifier)
    {
        super(name, password, identifier);
    }

    public boolean approveSub(AppSubmit submit)
    {
        submit.setStatus(AppSubmit.APPROVED);
        return true;
    }

    public boolean denySub(AppSubmit submit)
    {
        submit.setStatus(AppSubmit.DENIED);
        return true;
    }

    public void giveFeedBack(String feedback, AppSubmit submit)
    {
        submit.setFeedback(feedback);
    }

    public int getIdentifier()
    {
        return super.getIdentifier();
    }
}
