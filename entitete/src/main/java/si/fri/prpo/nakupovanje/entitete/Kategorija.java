package si.fri.prpo.nakupovanje.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kategorija")
@NamedQueries(value=
    {
        @NamedQuery(name="Kategorija.getAll", query="SELECT k FROM Kategorija k"),
        @NamedQuery(name="Artikel.getNakupovalniSeznambyId",
            query="SELECT k.nakupovalniSeznami FROM Kategorija k WHERE k.id=:id"),
        @NamedQuery(name="Kategorija.getOpis",
            query="SELECT CONCAT(\"Opis: \",k.opis) FROM Kategorija k WHERE k.id=:id"),
        @NamedQuery(name="Kategorija.getNaziv",
            query="SELECT CONCAT(\"Naziv: \",k.naziv) FROM Kategorija k WHERE k.id=:id")
    })
public class Kategorija{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="naziv")
  private String naziv;

  @Column(name="opis")
  private String opis;

  //@JsonbTransient
  @ManyToMany(mappedBy="kategorije")
  private List<NakupovalniSeznam> nakupovalniSeznami;

  public Integer getId(){
    return id;
  }

  public void setId(Integer id){
    this.id=id;
  }

  public String getNaziv(){
    return naziv;
  }

  public void setNaziv(String naziv){
    this.naziv=naziv;
  }

  public String getOpis(){
    return opis;
  }

  public void setOpis(String opis){
    this.opis=opis;
  }

  public List<NakupovalniSeznam> getNakupovalniSeznami(){
    return nakupovalniSeznami;
  }

  public void setNakupovalniSeznami(List<NakupovalniSeznam> nakupovalniSeznami){
    this.nakupovalniSeznami=nakupovalniSeznami;
  }
}