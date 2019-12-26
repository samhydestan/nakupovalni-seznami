package si.fri.prpo.nakupovanje.storitve.interceptorji;

import si.fri.prpo.nakupovanje.storitve.anotacije.BeleziKlice;
import si.fri.prpo.nakupovanje.storitve.bean.StevecBean;

import javax.inject.Inject;
import javax.interceptor.*;

@Interceptor
@BeleziKlice
public class BeleziKliceInterceptor {
    private Logger log = Logger.getLogger(BeleziKliceInterceptor.class.getName());


    @Inject
    private StevecBean sb;

    @AroundInvoke
    public Object dobiSteviloKlicev(InvocationContext ic) throws Exception{
        int st = sb.povecajCounter();
        log.info("St klicev: " + st);

        return ic.proceed();
    }

}
