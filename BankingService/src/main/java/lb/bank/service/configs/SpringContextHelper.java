package lb.bank.service.configs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Overhead solution for spring integration with UI
 * Created by root on 22.12.2015.
 */
public final class SpringContextHelper {
    private final AbstractApplicationContext context;

    public SpringContextHelper() {
        this.context = new AnnotationConfigApplicationContext(BankServiceSpringConfig.class);
    }
    public Object getBean(final String beanPath) {
        return context.getBean(beanPath);
    }
}
