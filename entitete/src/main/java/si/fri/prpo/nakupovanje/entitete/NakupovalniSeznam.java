package si.fri.prpo.nakupovanje.entitete;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="nakupovalni_seznam")
@NamedQueries(value=
    {
        @NamedQuery(name="NakupovalniSeznam.getAll",query="SELECT ns FROM NakupovalniSeznam ns"),
        @NamedQuery(name="NakupovalniSeznam.getSeznamforUporabnik",
            query="SELECT ns FROM NakupovalniSeznam ns WHERE ns.uporabnik=:uporabnik"),
        @NamedQuery(name="NakupovalniSeznam.getOpis",
            query="SELECT CONCAT(\"Opis: \",ns.opis) FROM NakupovalniSeznam ns WHERE ns.id=:id"),
        @NamedQuery(name="NakupovalniSeznam.getNumofSeznamforUporabnik",
            query="SELECT COUNT(ns) FROM NakupovalniSeznam ns WHERE ns.uporabnik=:uporabnik")
    })
public class NakupovalniSeznam{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="naziv")
  private String naziv;

  @Column(name="opis")
  private String opis;

  @ManyToOne
  @JoinColumn(name="uporabnik_id")
  private Uporabnik uporabnik;

  @ManyToMany(cascade={
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(
      joinColumns=@JoinColumn(name="nakupovalni_seznam_id"),
      inverseJoinColumns=@JoinColumn(name="artikel_id")
  )
  private List<Artikel> artikli;

  @ManyToMany(cascade={
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(
      joinColumns=@JoinColumn(name="nakupovalni_seznam_id"),
      inverseJoinColumns=@JoinColumn(name="kategorija_id")
  )
  private List<Kategorija> kategorije;

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

  public Uporabnik getUporabnik(){
    return uporabnik;
  }

  public void setUporabnik(Uporabnik uporabnik){
    this.uporabnik=uporabnik;
  }

  public List<Artikel> getArtikli(){
    return artikli;
  }

  public void setArtikli(List<Artikel> artikli){
    this.artikli=artikli;
  }

  public List<Kategorija> getKategorije(){
    return kategorije;
  }

  public void setKategorije(List<Kategorija> kategorije){
    this.kategorije=kategorije;
  }
}