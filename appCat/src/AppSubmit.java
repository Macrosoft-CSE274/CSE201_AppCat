import java.io.Serializable;

/**
 * Created by Kunting Qi on 2017/10/22.
 * This is the class to represent the Application Submit Request
 * have different status, pending, denied and approved
 */
public class AppSubmit implements Serializable {
    private String name;
    private String developer;
    private String platform;
    private String version;
    private String description;
    private String feedback;
    private String webLink;

    //indicate the status of the app submit request
    private int status;

    // here are some Static variable for reference and easy use
    public static int DENIED = 1;
    public static int APPROVED = 2;
    public static int PENDING = 3;

    /**
     * this is the constructor of this class
     * @param name name of the app in String
     * @param developer name of the developer in String
     * @param platform name of the platform it runs on in String
     * @param version name of the verion in String
     * @param description name of the description in String
     * @param webLink name of the webLink in String
     */
    public AppSubmit(String name, String developer, String platform, String version, String description, String webLink)
    {
        this.name = name;
        this.description =description;
        this.developer = developer;
        this.platform = platform;
        this.version = version;
        this.webLink = webLink;
        status = 3;
    }

    /**
     * get the name of App
     * @return name of the app in String
     */
    public String getName()
    {
        return name;
    }

    /**
     * get the name of developer
     * @return name of the developer in String
     */
    public String getDeveloper()
    {
        return developer;
    }

    /**
     * get the name of platform
     * @return name of the platform in String
     */
    public String getPlatform()
    {
        return platform;
    }

    /**
     * get the name of version
     * @return name of the version in String
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * get the name of description
     * @return name of the description in String
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * set the status the this app sumit request
     * @param input the numbers to indicate statu, 1, 2, 3, 4 has been recorded in static variable in this class
     *              for the reference
     */
    public void setStatus(int input)
    {
        status = input;
    }

    /**
     * set the feedback of this app
     * @param feedback
     */
    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }
}
