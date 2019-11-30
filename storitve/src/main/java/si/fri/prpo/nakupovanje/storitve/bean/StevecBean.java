package si.fri.prpo.nakupovanje.storitve.bean;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class StevecBean{
    private Logger log=Logger.getLogger(StevecBean.class.getName());
    private String beanID;

    private static int Counter = 0;

    public int povecajCounter(){
        Counter++;
        log.info(StevecBean.class.getSimpleName()+" Stevilo klicev metode: " + Counter);
        return Counter;
    }
}
