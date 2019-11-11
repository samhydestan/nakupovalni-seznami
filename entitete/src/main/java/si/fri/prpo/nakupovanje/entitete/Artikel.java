package si.fri.prpo.nakupovanje.entitete;

import javax.persistence.*;

@Entity
@Table(name="artikel")
@NamedQueries(value=
    {
        @NamedQuery(name="Artikel.getAll",query="SELECT a FROM Artikel a"),
        @NamedQuery(name="Artikel.getWellStocked",
            query="SELECT a FROM Artikel a WHERE a.zaloga>=:zaloga"),
        @NamedQuery(name="Artikel.getOpis",
            query="SELECT CONCAT(\"Opis: \",a.opis) FROM Artikel a WHERE a.id=:id"),
        @NamedQuery(name="Artikel.getNaziv",
            query="SELECT CONCAT(\"Naziv: \",a.naziv) FROM Artikel a WHERE a.id=:id")
    })
public class Artikel{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="naziv")
  private String naziv;

  @Column(name="opis")
  private String opis;

  @Column(name="zaloga")
  private Integer zaloga;

  @ManyToOne
  @JoinColumn(name="nakupovalni_seznam_id")
  private NakupovalniSeznam nakupovalniSeznam;

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

  public Integer getZaloga(){
    return zaloga;
  }

  public void setZaloga(Integer zaloga){
    this.zaloga=zaloga;
  }

  public NakupovalniSeznam getNakupovalniSeznam(){
    return nakupovalniSeznam;
  }

  public void setNakupovalniSeznam(NakupovalniSeznam nakupovalniSeznam){
    this.nakupovalniSeznam=nakupovalniSeznam;
  }
}
