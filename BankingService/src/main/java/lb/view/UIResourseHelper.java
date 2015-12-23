package lb.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by root on 23.12.2015.
 */
@Component("res")
@PropertySource(value = {"classpath:bankmodel.properties"})
public final class UIResourseHelper {
    @Autowired
    private static Environment env;

    private static UIResourseHelper resourceHelper = new UIResourseHelper();
    private UIResourseHelper() {
    }
    private static UIResourseHelper getResourceHelper() {
        return resourceHelper;
    }
    public static String getResourceKey(final String param ){
        return (String) env.getRequiredProperty(param);
    }
}
