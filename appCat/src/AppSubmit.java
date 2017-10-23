/**
 * Created by Kunting Qi on 2017/10/22.
 */
public class AppSubmit {
    private String name;
    private String developer;
    private String platform;
    private String version;
    private String description;
    private String feedback;
    private int status;
    public static int DENIED = 1;
    public static int APPROVED = 2;
    public static int PENDING = 3;

    public AppSubmit(String name, String developer, String platform, String version, String description)
    {
        this.name = name;
        this.description =description;
        this.developer = developer;
        this.platform = platform;
        this.version = version;
        status = 3;
    }

    public String getName()
    {
        return name;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getVersion()
    {
        return version;
    }

    public String getDescription()
    {
        return description;
    }

    public void setStatus(int input)
    {
        status = input;
    }

    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }
}
