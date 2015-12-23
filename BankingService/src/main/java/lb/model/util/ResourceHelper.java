package lb.model.util;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by root on 20.12.2015.
 */
public final class ResourceHelper {
       private static final String LOCATION_PATH = "/resources/bankmodel.properties";
       private static final Logger LOGGER = Logger.getLogger(ResourceHelper.class);
       private static Properties resources = new Properties();
       public  static ResourceHelper resourceHelper = new ResourceHelper();
       private ResourceHelper() {
               try(final InputStream inputStream = ResourceHelper.class.getResourceAsStream(LOCATION_PATH)) {
                   resources.load(inputStream);
               } catch (IOException e) {
                  LOGGER.error("resources problems", e);
               }
        }
       public static ResourceHelper getResourceHelper() {
            return resourceHelper;
        }
       public static String getResourceKey(final String param ){
            return (String) getResourceHelper().resources.get(param);
       }
}
