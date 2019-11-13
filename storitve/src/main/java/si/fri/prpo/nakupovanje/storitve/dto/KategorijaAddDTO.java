package si.fri.prpo.nakupovanje.storitve.dto;

public class KategorijaAddDTO{
  private Integer kID;
  private Integer nsID;

  public Integer getkID(){
    return kID;
  }

  public void setkID(Integer kID){
    this.kID=kID;
  }

  public Integer getNsID(){
    return nsID;
  }

  public void setNsID(Integer nsID){
    this.nsID=nsID;
  }
}
