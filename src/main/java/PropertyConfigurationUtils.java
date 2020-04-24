import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfigurationUtils {

    public static String getPropertyFromFile(String propertyName){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("D:\\testGradle\\src\\main\\resources\\configuration.propeties")) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
