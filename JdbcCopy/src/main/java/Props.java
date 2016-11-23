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
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
            System.out.println(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
