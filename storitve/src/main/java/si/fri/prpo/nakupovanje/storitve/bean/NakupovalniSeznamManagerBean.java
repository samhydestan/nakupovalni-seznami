package si.fri.prpo.nakupovanje.storitve.bean;

import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.Kategorija;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.storitve.dto.ArtikelAddDTO;
import si.fri.prpo.nakupovanje.storitve.dto.KategorijaAddDTO;
import si.fri.prpo.nakupovanje.storitve.dto.NakupovalniSeznamCreateDTO;
import si.fri.prpo.nakupovanje.storitve.izjeme.ManjkaUporabnikIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniSeznamManagerBean{
  private Logger log=Logger.getLogger(NakupovalniSeznamManagerBean.class.getName());
  private String beanID;

  @Inject
  private ArtikelBean artikelBean;

  @Inject
  private KategorijaBean kategorijaBean;

  @Inject
  private UporabnikBean uporabnikBean;

  @Inject
  private NakupovalniSeznamBean nakupovalniSeznamBean;

  @PostConstruct
  private void init(){
    log.info(NakupovalniSeznamManagerBean.class.getSimpleName()+" je bil ustvarjen.");
  }

  @PreDestroy
  private void destroy(){
    log.info(NakupovalniSeznamManagerBean.class.getSimpleName()+" bo sedaj uničen.");
  }

  public NakupovalniSeznam createNakupovalniSeznam(NakupovalniSeznamCreateDTO nakupovalniSeznamDTO){
    Uporabnik u=uporabnikBean.getUporabnik(nakupovalniSeznamDTO.getuID());
    if(u==null){
      log.severe("Uporabnik ne obstaja.");
      throw new ManjkaUporabnikIzjema("Uporabnik ne obstaja.");
    }
    List<NakupovalniSeznam> nakupovalniSeznami=u.getNakupovalniSeznami();
    List<Artikel> artikli=new ArrayList<>();
    List<Kategorija> kategorije=new ArrayList<>();
    NakupovalniSeznam ns=new NakupovalniSeznam();
    ns.setUporabnik(u);
    ns.setNaziv(nakupovalniSeznamDTO.getNaziv());
    ns.setOpis(nakupovalniSeznamDTO.getOpis());
    ns.setArtikli(artikli);
    ns.setKategorije(kategorije);
    ns=nakupovalniSeznamBean.addNakupovalniSeznam(ns);
    nakupovalniSeznami.add(ns);
    u.setNakupovalniSeznami(nakupovalniSeznami);
    uporabnikBean.updateUporabnik(nakupovalniSeznamDTO.getuID(),u);
    return ns;
  }


  public NakupovalniSeznam addArtikelIntoNakupovalniSeznam(ArtikelAddDTO artikelAddDTO){
    NakupovalniSeznam ns=nakupovalniSeznamBean.getNakupovalniSeznam(artikelAddDTO.getNsID());
    if(ns==null){
      log.info("Nakupovalni seznam ne obstaja.");
      return null;
    }
    Artikel a=artikelBean.getArtikel(artikelAddDTO.getaID());
    if(a==null){
      log.info("Artikel ne obstaja.");
      return ns;
    }
    if(a.getZaloga()==0){
      log.info("Artikel ni več na zalogi.");
    }
    List<Artikel> artikli=ns.getArtikli();
    List<NakupovalniSeznam> nakupovalniSeznami=a.getNakupovalniSeznami();
    artikli.add(a);
    nakupovalniSeznami.add(ns);
    a.setNakupovalniSeznami(nakupovalniSeznami);
    ns.setArtikli(artikli);
    nakupovalniSeznamBean.updateNakupovalniSeznam(artikelAddDTO.getNsID(),ns);
    artikelBean.updateArtikel(artikelAddDTO.getaID(),a);
    return ns;
  }

  public NakupovalniSeznam addKategorijaIntoNakupovalniSeznam(KategorijaAddDTO kategorijaAddDTO){
    NakupovalniSeznam ns=nakupovalniSeznamBean.getNakupovalniSeznam(kategorijaAddDTO.getNsID());
    if(ns==null){
      log.info("Nakupovalni seznam ne obstaja.");
      return null;
    }
    Kategorija k=kategorijaBean.getKategorija(kategorijaAddDTO.getkID());
    if(k==null){
      log.info("Kategorija ne obstaja.");
      return ns;
    }
    List<Kategorija> kategorije=ns.getKategorije();
    List<NakupovalniSeznam> nakupovalniSeznami=k.getNakupovalniSeznami();
    if(kategorije.contains(k)){
      log.info("Seznam je že v tej kategoriji.");
      return ns;
    }
    nakupovalniSeznami.add(ns);
    kategorije.add(k);
    k.setNakupovalniSeznami(nakupovalniSeznami);
    ns.setKategorije(kategorije);
    nakupovalniSeznamBean.updateNakupovalniSeznam(kategorijaAddDTO.getNsID(),ns);
    return ns;
  }
}
