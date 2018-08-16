import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class PrintProperties {
    public static void main(String[] args) {
        PrintProperties app = new PrintProperties();
        app.printThemAll();
    }

    private void printThemAll() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            //input = getClass().getClassLoader().getResourceAsStream(filename);
            input = new FileInputStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println(key + " : " + value);
            }

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
}
