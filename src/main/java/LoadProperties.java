import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 class LoadProperties {

    private static Properties prop;
    private static InputStream input = null;

    static {

        try {
            prop = new Properties();
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    static String getPropValue(String key) {
        return prop.getProperty(key);
    }

}
