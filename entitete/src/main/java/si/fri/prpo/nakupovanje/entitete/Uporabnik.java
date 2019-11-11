package si.fri.prpo.nakupovanje.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="uporabnik")
@NamedQueries(value=
    {
        @NamedQuery(name="Uporabnik.getAll",query="SELECT u FROM Uporabnik u"),
        @NamedQuery(name="Uporabnik.getAllbyUime",
            query="SELECT u FROM Uporabnik u WHERE u.uime LIKE \":uime\""),
        @NamedQuery(name="Uporabnik.getNakupovalniSeznamibyId",
            query="SELECT u.nakupovalniSeznami FROM Uporabnik u WHERE u.id=:id"),
        @NamedQuery(name="Uporabnik.getEmailbyId",
            query="SELECT u.email FROM Uporabnik u WHERE u.id=:id")
    })
public class Uporabnik{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="ime")
  private String ime;

  @Column(name="priimek")
  private String priimek;

  @Column(name="uime")
  private String uime;

  @Column(name="email")
  private String email;

  @OneToMany(mappedBy="uporabnik",cascade=CascadeType.ALL)
  private List<NakupovalniSeznam> nakupovalniSeznami;

  public Integer getId(){
    return id;
  }

  public void setId(Integer id){
    this.id=id;
  }

  public String getIme(){
    return ime;
  }

  public void setIme(String ime){
    this.ime=ime;
  }

  public String getPriimek(){
    return priimek;
  }

  public void setPriimek(String priimek){
    this.priimek=priimek;
  }

  public String getUime(){
    return uime;
  }

  public void setUime(String uime){
    this.uime=uime;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail(String email){
    this.email=email;
  }

  public List<NakupovalniSeznam> getNakupovalniSeznami(){
    return nakupovalniSeznami;
  }

  public void setNakupovalniSeznami(List<NakupovalniSeznam> nakupovalniSeznami){
    this.nakupovalniSeznami=nakupovalniSeznami;
  }
}