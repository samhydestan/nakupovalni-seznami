package si.fri.prpo.nakupovanje.api.v1.viri;

import si.fri.prpo.nakupovanje.storitve.bean.ArtikelBean;
import si.fri.prpo.nakupovanje.entitete.Artikel;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;

@ApplicationScoped
@Path("artikli")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ArtikelVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ArtikelBean aBean;


    @Operation(description = "Vrne seznam artiklov", summary = "Seznam artiklov",
            tags = "artikli", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam artiklov",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Artikel.class))),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih artiklov")})
    })
    @GET
    public Response pridobiArtikle(){

       // return Response.ok(aBean.getArtikli()).build();
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long artCount = aBean.getArtikliCount(query);
        return Response
                .ok(aBean.getArtikli(query))
                .header("X-Total-Count", artCount)
                .build();

    }

    @Operation(description = "Vrne podrobnosti artikla", summary = "Podrobnosti artikla",
            tags = "artikli", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Podrobnosti artikla",
                    content = @Content(schema = @Schema(implementation = Artikel.class))
            )
    })
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

    @Operation(description = "Dodaj artikel", summary = "Dodajanje artikla",
            tags = "artikli", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Artikel uspešno dodan"
            ),
            @ApiResponse(responseCode = "405", description = "Validacijska napaka")
    })
    @POST
    public Response dodajArtikel(Artikel a){

        return Response
                .status(Response.Status.CREATED)
                .entity(aBean.addArtikel(a))
                .build();
    }

    @Operation(description = "Posodobi artikel", summary = "Posodabljanje artikla",
            tags = "artikli", responses = {
            @ApiResponse(responseCode = "201", description = "Artikel uspešno posodobljen"
            )
    })
    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@PathParam("id") Integer id, Artikel a) {
        return Response
                .status(Response.Status.CREATED)
                .entity(aBean.updateArtikel(id,a))
                .build();
    }

    @Path("{id}")
    public Response zbrisiArtikel(@Parameter(
            description = "Identifikator artikla za brisanje", required = true)
                                  @PathParam("id") Integer id){
        return Response .status(Response.Status.OK)
                .entity(artikelZrno.odstraniArtikela(id))
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
