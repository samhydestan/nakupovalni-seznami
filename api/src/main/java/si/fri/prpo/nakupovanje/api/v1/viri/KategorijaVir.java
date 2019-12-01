package si.fri.prpo.nakupovanje.api.v1.viri;

import si.fri.prpo.nakupovanje.entitete.Kategorija;
import si.fri.prpo.nakupovanje.storitve.bean.KategorijaBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationScoped
@Path("kategorije")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class KategorijaVir {

    @Inject
    private KategorijaBean kBean;

    @GET
    public Response pridobiKategorije(){
        return Response.ok(kBean.getKategorije()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiKategorijo(@PathParam("id") Integer id) {

        Kategorija ns = kBean.getKategorija(id);

        if(ns != null){
            return Response.ok(ns).build();
        }else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    public Response dodajKategorijo(Kategorija k){

        return Response
                .status(Response.Status.CREATED)
                .entity(kBean.addKategorija(k))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiKategorijo(@PathParam("id") Integer id, Kategorija k) {
        return Response
                .status(Response.Status.CREATED)
                .entity(kBean.updateKategorija(id,k))
                .build();
    }


    @DELETE
    @Path("{id}")
    public Response odstraniKategorijo(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(kBean.deleteKategorija(id))
                .build();
    }
}
