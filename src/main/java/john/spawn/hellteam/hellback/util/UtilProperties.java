package john.spawn.hellteam.hellback.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class UtilProperties {

    private UtilProperties() {
    }

    public static String[] getProperties() {

        Properties properties = new Properties();

        try {
            properties.load(UtilProperties.class.getClassLoader().getResourceAsStream("security.properties"));
            return Arrays.stream(properties.getProperty("secu.request-matcher").split(","))
                    .map(String::trim)
                    .filter(path -> !path.isEmpty())
                    .toArray(String[]::new);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
