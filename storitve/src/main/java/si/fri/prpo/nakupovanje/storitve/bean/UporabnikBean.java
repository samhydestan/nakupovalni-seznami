package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikBean {

  private Logger log=Logger.getLogger(UporabnikBean.class.getName());
  private String beanID;

  @PostConstruct
  private void init(){
    log.info(UporabnikBean.class.getSimpleName()+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(UporabnikBean.class.getSimpleName()+" bo sedaj uničen.");
  }

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<Uporabnik> getUporabniki() {
    return (List<Uporabnik>)em.createNamedQuery("Uporabnik.getAll").getResultList();
  }

  public Uporabnik getUporabnik(int id){
    return em.find(Uporabnik.class,id);
  }

  @Transactional
  public Uporabnik addUporabnik(Uporabnik u){
    if(u!=null){
      em.persist(u);
    }
    return u;
  }

  @Transactional
  public void updateUporabnik(int id,Uporabnik u){
    Uporabnik oldu=em.find(Uporabnik.class,id);
    u.setId(oldu.getId());
    em.merge(u);
  }

  @Transactional
  public Integer deleteUporabnik(int id){
    Uporabnik u=em.find(Uporabnik.class,id);
    if(u!=null){
      em.remove(u);
    }
    return id;
  }
}