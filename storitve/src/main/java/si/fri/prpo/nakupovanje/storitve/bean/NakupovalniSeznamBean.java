package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniSeznamBean{
  private Logger log=Logger.getLogger(NakupovalniSeznamBean.class.getName());
  private String beanID;

  @PostConstruct
  private void init(){
    log.info(NakupovalniSeznamBean.class.getSimpleName()+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(NakupovalniSeznamBean.class.getSimpleName()+" bo sedaj uničen.");
  }

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<NakupovalniSeznam> getNakupovalniSeznami() {
    return (List<NakupovalniSeznam>)em.createNamedQuery("NakupovalniSeznam.getAll").getResultList();
  }

  public NakupovalniSeznam getNakupovalniSeznam(int id){
    return em.find(NakupovalniSeznam.class,id);
  }

  @Transactional
  public NakupovalniSeznam addNakupovalniSeznam(NakupovalniSeznam ns){
    if(ns!=null){
      em.persist(ns);
    }
    return ns;
  }

  @Transactional
  public void updateNakupovalniSeznam(int id,NakupovalniSeznam ns){
    NakupovalniSeznam oldns=em.find(NakupovalniSeznam.class,id);
    ns.setId(oldns.getId());
    em.merge(ns);
  }

  @Transactional
  public Integer deleteNakupovalniSeznam(int id){
    NakupovalniSeznam ns=em.find(NakupovalniSeznam.class,id);
    if(ns!=null){
      em.remove(ns);
    }
    return id;
  }
}
