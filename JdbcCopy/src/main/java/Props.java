import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrey_Gladyshev on 23.11.2016.
 */
public final class Props {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            if (inputStream == null) {
                throw new IOException("db.properties is missing!");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
