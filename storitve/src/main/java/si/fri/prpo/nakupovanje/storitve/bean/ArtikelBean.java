package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.Artikel;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ArtikelBean{
  private Logger log=Logger.getLogger(ArtikelBean.class.getName());
  private String beanID;

  @PostConstruct
  private void init(){
    log.info(ArtikelBean.class.getSimpleName()+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(ArtikelBean.class.getSimpleName()+" bo sedaj uničen.");
  }

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<Artikel> getArtikli() {
    return (List<Artikel>)em.createNamedQuery("Artikel.getAll").getResultList();
  }

  public Artikel getArtikel(int id){
    return em.find(Artikel.class,id);
  }

  @Transactional
  public Artikel addArtikel(Artikel a){
    if(a!=null){
      em.persist(a);
    }
    return a;
  }

  @Transactional
  public void updateArtikel(int id,Artikel a){
    Artikel olda=em.find(Artikel.class,id);
    a.setId(olda.getId());
    em.merge(a);
  }

  @Transactional
  public Integer deleteArtikel(int id){
    Artikel a=em.find(Artikel.class,id);
    if(a!=null){
      em.remove(a);
    }
    return id;
  }
}
