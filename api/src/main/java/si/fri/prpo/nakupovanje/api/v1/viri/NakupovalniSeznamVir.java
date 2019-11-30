package si.fri.prpo.nakupovanje.api.v1.viri;

import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.storitve.bean.NakupovalniSeznamBean;
import si.fri.prpo.nakupovanje.storitve.bean.NakupovalniSeznamManagerBean;
import si.fri.prpo.nakupovanje.storitve.dto.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationScoped
@Path("nakupovalniSeznam")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class NakupovalniSeznamVir {

    @Inject
    private NakupovalniSeznamBean nsBean;

    @Inject
    private NakupovalniSeznamManagerBean nsmBean;

    @GET
    public Response pridobiNakupovalniSeznam(){
        return Response.ok(nsBean.getNakupovalniSeznami()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiNakupovalniSeznam(@PathParam("id") Integer id) {

        NakupovalniSeznam ns = nsBean.getNakupovalniSeznam(id);

        if(ns != null){
            return Response.ok(ns).build();
        }else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    /*
    @POST
    public Response dodajNakupovalniSeznam(NakupovalniSeznam ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsBean.addNakupovalniSeznam(ns))
                .build();
    }
    */

    @PUT
    @Path("{id}")
    public Response posodobiNakupovalniSeznam(@PathParam("id") Integer id, NakupovalniSeznam ns) {
        return Response
                .status(Response.Status.CREATED)
                .entity(nsBean.updateNakupovalniSeznam(id,ns))
                .build();
    }


    @DELETE
    @Path("{id}")
    public Response odstraniNakupovalniSeznam(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(nsBean.deleteNakupovalniSeznam(id))
                .build();
    }

    //poslovne metode
    @POST
    public Response dodajNakupovalniSeznam(NakupovalniSeznamCreateDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.createNakupovalniSeznam(ns))
                .build();
    }

    @POST
    public Response dodajArtikelIntoNakupovalniSeznam(ArtikelAddDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.addArtikelIntoNakupovalniSeznam(ns))
                .build();
    }

    @POST
    public Response dodajKategorijaIntoNakupovalniSeznam(KategorijaAddDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.addKategorijaIntoNakupovalniSeznam(ns))
                .build();
    }



}
