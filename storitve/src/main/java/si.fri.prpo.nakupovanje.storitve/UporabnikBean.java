package si.fri.prpo.nakupovanje.storitve;

import si.fri.prpo.nakupovanje.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikBean {

  private Logger log=Logger.getLogger(UporabnikBean.class.getName());

  @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
  private EntityManager em;

  public List<Uporabnik> getUporabniki() {
    return (List<Uporabnik>)em.createNamedQuery("Uporabnik.getAll").getResultList();
  }
}