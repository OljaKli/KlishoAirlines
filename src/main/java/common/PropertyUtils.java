package common;

/**
 * Created by Ola-Mola on 30/05/16.
 */
import java.util.Properties;

public interface PropertyUtils {
    static String getAndRemove(Properties properties, String key) {
        return (String) properties.remove(key);
    }
}