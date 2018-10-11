import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by john on 10/10/18.
 */
public class Utility {
    private static final Logger LOGGER = Logger.getLogger(Utility.class.getName());

    public static List<String> getLinesFromFile(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        }catch (IOException e) {
            LOGGER.warning("Logger Name: "+LOGGER.getName() + e.getMessage());
        }
        return lines;
    }

    public static Object jsonConvert(String jstr, Class obj) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(jstr, obj);
        } catch (JsonSyntaxException e) {
            LOGGER.warning("Logger Name: "+LOGGER.getName() + e.getMessage());
        }

        return null;
    }

    public static String getUrl(String url) {
        String response = null;
        try {
            response = HttpRequest.get(url).body();
        }catch (HttpRequest.HttpRequestException e) {
            LOGGER.warning("Logger Name: "+LOGGER.getName() + e.getMessage());
        }
        return response;
    }
}
