package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.Kategorija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class KategorijaBean{
  private Logger log=Logger.getLogger(KategorijaBean.class.getName());
  private String beanID;

  @PostConstruct
  private void init(){
    log.info(KategorijaBean.class.getSimpleName()+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(KategorijaBean.class.getSimpleName()+" bo sedaj uničen.");
  }

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<Kategorija> getKategorije() {
    return (List<Kategorija>)em.createNamedQuery("Kategorija.getAll").getResultList();
  }

  public Kategorija getKategorija(int id){
    return em.find(Kategorija.class,id);
  }

  @Transactional
  public Kategorija addKategorija(Kategorija k){
    if(k!=null){
      em.persist(k);
    }
    return k;
  }

  @Transactional
  public Kategorija updateKategorija(int id,Kategorija k){
    Kategorija oldk=em.find(Kategorija.class,id);
    k.setId(oldk.getId());
    em.merge(k);
    return em.find(Kategorija.class,id);
  }

  @Transactional
  public Integer deleteKategorija(int id){
    Kategorija k=em.find(Kategorija.class,id);
    if(k!=null){
      em.remove(k);
    }
    return id;
  }
}
