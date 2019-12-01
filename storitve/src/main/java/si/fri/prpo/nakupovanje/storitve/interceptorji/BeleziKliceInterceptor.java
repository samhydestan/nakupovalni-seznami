package si.fri.prpo.nakupovanje.storitve.interceptorji;

import si.fri.prpo.nakupovanje.storitve.anotacije.BeleziKlice;
import si.fri.prpo.nakupovanje.storitve.bean.StevecBean;

import javax.inject.Inject;
import javax.interceptor.*;

@Interceptor
@BeleziKlice
public class BeleziKliceInterceptor {

    @Inject
    private StevecBean sb;

    @AroundInvoke
    public Object dobiSteviloKlicev(InvocationContext ic) throws Exception{
        sb.povecajCounter();
        return ic.proceed();
    }

}
