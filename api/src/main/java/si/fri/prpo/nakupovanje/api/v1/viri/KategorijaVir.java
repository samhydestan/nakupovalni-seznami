package si.fri.prpo.nakupovanje.api.v1.viri;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovanje.entitete.Kategorija;
import si.fri.prpo.nakupovanje.storitve.bean.KategorijaBean;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.ws.rs.core.Context;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationScoped
@Path("kategorije")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class KategorijaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private KategorijaBean kBean;

    @Operation(description = "Vrne seznam kategorij", summary = "Seznam kategorij",
            tags = "kategorije", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam kategorij",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Kategorija.class))),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih kategorij")})
    })
    @GET
    public Response pridobiKategorije(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long katcount = kBean.getKategorijeCount(query);
        return Response
                .ok(kBean.getKategorije(query))
                .header("X-Total-Count", katcount)
                .build();

    }

    @Operation(description = "Vrne podrobnosti kategorije", summary = "Podrobnosti kategorije",
            tags = "kategorije", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Podrobnosti kategorije",
                    content = @Content(schema = @Schema(implementation = Kategorija.class))
            )
    })
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

    @Operation(description = "Dodaj kategorijo", summary = "Dodajanje kategorije",
            tags = "kategorije", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Kategorija uspešno dodan"
            ),
            @ApiResponse(responseCode = "405", description = "Validacijska napaka")
    })
    @POST
    public Response dodajKategorijo(Kategorija k){

        return Response
                .status(Response.Status.CREATED)
                .entity(kBean.addKategorija(k))
                .build();
    }

    @Operation(description = "Posodobi kategorijo", summary = "Posodabljanje kategorije",
            tags = "kategorije", responses = {
            @ApiResponse(responseCode = "201", description = "Kategorija uspešno posodobljen"
            )
    })
    @PUT
    @Path("{id}")
    public Response posodobiKategorijo(@PathParam("id") Integer id, Kategorija k) {
        return Response
                .status(Response.Status.CREATED)
                .entity(kBean.updateKategorija(id,k))
                .build();
    }

    @Operation(description = "Odstrani kategorijo", summary = "Brisanje kategorije",
            tags = "kategorija",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Kategorija uspešno odstranjen"),
                    @ApiResponse(responseCode = "404", description = "Kategorija ne obstaja")

    })
    @DELETE
    @Path("{id}")
    public Response odstraniKategorijo(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(kBean.deleteKategorija(id))
                .build();
    }
}
