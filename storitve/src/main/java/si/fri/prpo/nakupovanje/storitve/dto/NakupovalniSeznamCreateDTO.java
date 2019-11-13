package si.fri.prpo.nakupovanje.storitve.dto;

public class NakupovalniSeznamCreateDTO{
  private Integer uID;
  private String naziv;
  private String opis;

  public Integer getuID(){
    return uID;
  }

  public void setuID(Integer uID){
    this.uID=uID;
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
}
