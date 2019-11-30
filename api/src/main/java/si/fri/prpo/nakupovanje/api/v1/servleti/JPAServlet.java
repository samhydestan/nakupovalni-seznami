package si.fri.prpo.nakupovanje.api.v1.servleti;

import si.fri.prpo.nakupovanje.entitete.Artikel;
import si.fri.prpo.nakupovanje.entitete.Kategorija;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.storitve.bean.*;
import si.fri.prpo.nakupovanje.storitve.dto.ArtikelAddDTO;
import si.fri.prpo.nakupovanje.storitve.dto.KategorijaAddDTO;
import si.fri.prpo.nakupovanje.storitve.dto.NakupovalniSeznamCreateDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet{

  @Inject
  private KategorijaBean kBean;
  @Inject
  private ArtikelBean aBean;
  @Inject
  private NakupovalniSeznamManagerBean nsmBean;
  @Inject
  private NakupovalniSeznamBean nsBean;
  @Inject
  private UporabnikBean uBean;

  private Logger log=Logger.getLogger(JPAServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
    resp.setContentType("text/html; charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter pw=resp.getWriter();
    NakupovalniSeznamCreateDTO newnsDTO=new NakupovalniSeznamCreateDTO();
    newnsDTO.setNaziv("Seznam z rožico");
    newnsDTO.setOpis("Seznam z eno samo rožico");
    newnsDTO.setuID(uBean.getUporabniki().get(0).getId());
    NakupovalniSeznam ns1=nsmBean.createNakupovalniSeznam(newnsDTO);
    ArtikelAddDTO aaddDTO=new ArtikelAddDTO();
    aaddDTO.setaID(aBean.getArtikli().get(0).getId());
    aaddDTO.setNsID(ns1.getId());
    ns1=nsmBean.addArtikelIntoNakupovalniSeznam(aaddDTO);
    KategorijaAddDTO kaddDTO=new KategorijaAddDTO();
    kaddDTO.setkID(kBean.getKategorije().get(0).getId());
    kaddDTO.setNsID(ns1.getId());
    nsmBean.addKategorijaIntoNakupovalniSeznam(kaddDTO);
    pw.printf("Nakupovalni seznami po dodajanju:</br></br>");
    String s="";
    for(NakupovalniSeznam ns : nsBean.getNakupovalniSeznami()){
      s+="Naziv: "+ns.getNaziv()+"</br>";
      s+="Opis: "+ns.getOpis()+"</br>";
      s+="Artikli:</br>";
      for(Artikel a:ns.getArtikli()){
        s+="Naziv: "+a.getNaziv()+"</br>";
        s+="Opis: "+a.getOpis()+"</br>";
        s+="Zaloga: "+a.getZaloga()+"</br>";
      }
      s+="Kategorije:</br>";
      for(Kategorija k:ns.getKategorije()){
        s+="Naziv: "+k.getNaziv()+"</br>";
        s+="Opis: "+k.getOpis()+"</br>";
      }
    }
    pw.printf("%s",s);
  }
}