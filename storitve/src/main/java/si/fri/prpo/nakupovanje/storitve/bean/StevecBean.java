package si.fri.prpo.nakupovanje.storitve.bean;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class StevecBean{
    private Logger log=Logger.getLogger(StevecBean.class.getName());

    private static int counter = 0;

    public int povecajCounter(){
        counter++;
        log.info(StevecBean.class.getSimpleName()+" Stevilo klicev metode: " +counter);
        return counter;
    }
}
