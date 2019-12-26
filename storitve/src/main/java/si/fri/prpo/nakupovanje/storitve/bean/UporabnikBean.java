package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.storitve.anotacije.BeleziKlice;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class UporabnikBean {

  private Logger log=Logger.getLogger(UporabnikBean.class.getName());
  private String beanID;



  @PostConstruct
  private void init(){
    beanID=UUID.randomUUID().toString();
    log.info(UporabnikBean.class.getSimpleName()+" z UUID "+beanID+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(UporabnikBean.class.getSimpleName()+" z UUID "+beanID+" bo sedaj uničen.");
  }

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<Uporabnik> getUporabniki() {
    return (List<Uporabnik>)em.createNamedQuery("Uporabnik.getAll").getResultList();
  }

  public Uporabnik getUporabnik(int id){
    return em.find(Uporabnik.class,id);
  }

  //
  public List<Uporabnik> getUporabniki(QueryParameters query) {
    return JPAUtils.queryEntities(em, Uporabnik.class, query);
  }

  public Long getUporabnikiCount(QueryParameters query) {
    return JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
  }


  @Transactional
  @BeleziKlice
  public Uporabnik addUporabnik(Uporabnik u){
    if(u!=null){
      em.persist(u);
    }
    return u;
  }

  @Transactional
  public Uporabnik updateUporabnik(int id,Uporabnik u){
    Uporabnik oldu=em.find(Uporabnik.class,id);
    u.setId(oldu.getId());
    em.merge(u);
    return em.find(Uporabnik.class,id);
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