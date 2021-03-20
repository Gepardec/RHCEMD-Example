package at.gepardec.example.rhcemd;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

@ApplicationScoped
public class Producer {

    @Produces
    @Default
    @Dependent
    Logger createLogger(final InjectionPoint ip) {
        if (ip.getBean() != null) {
            return Logger.getLogger(ip.getBean().getBeanClass().toString());
        } else if (ip.getMember() != null) {
            return Logger.getLogger(ip.getMember().getDeclaringClass().toString());
        } else {
            return Logger.getLogger("default");
        }
    }
}