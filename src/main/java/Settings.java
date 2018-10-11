import com.beust.jcommander.Parameter;

/**
 * Created by john on 10/10/18.
 */
public class Settings {
    @Parameter(names = "-c", description = "Cities configuration file path", required = false)
    private String path;

    @Parameter(names = "-a", description = "Authentication token", required = false)
    private String authenticationToken;

    @Parameter(names = "-s", description = "Specific city", required = false)
    private String city;

    @Parameter(names = "--help", description = "Help", help = true ,required = false)
    private boolean help = false;

    public String getPath() {
        return this.path;
    }

    public String getCity() {
        return this.city;
    }

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public boolean getHelp() {
        return this.help;
    }

}
