package si.fri.prpo.nakupovanje.api.v1.viri;

import si.fri.prpo.nakupovanje.storitve.bean.ArtikelBean;
import si.fri.prpo.nakupovanje.entitete.Artikel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationScoped
@Path("artikli")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ArtikelVir {

    @Inject
    private ArtikelBean aBean;

    @GET
    public Response pridobiArtikle(){
        return Response.ok(aBean.getArtikli()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiArtikel(@PathParam("id") Integer id) {

        Artikel ns = aBean.getArtikel(id);

        if(ns != null){
            return Response.ok(ns).build();
        }else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    public Response dodajArtikel(Artikel a){

        return Response
                .status(Response.Status.CREATED)
                .entity(aBean.addArtikel(a))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@PathParam("id") Integer id, Artikel a) {
        return Response
                .status(Response.Status.CREATED)
                .entity(aBean.updateArtikel(id,a))
                .build();
    }


    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(aBean.deleteArtikel(id))
                .build();
    }
}
