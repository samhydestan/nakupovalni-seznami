package si.fri.prpo.nakupovanje.api.v1.viri;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovanje.entitete.Uporabnik;
import si.fri.prpo.nakupovanje.storitve.anotacije.BeleziKlice;
import si.fri.prpo.nakupovanje.storitve.bean.UporabnikBean;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.ws.rs.core.Context;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationScoped
@Path("uporabniki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UporabnikVir {

    @Context
    protected UriInfo uriInfo;


    @Inject
    private UporabnikBean uBean;

    @Operation(description = "Vrne seznam uporabnikov", summary = "Seznam uporabnikov",
            tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam uporabnikov",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Uporabnik.class))),
                    headers = {@Header(name = "X-Total-Count", description = "Število vrnjenih uporabnikov")})

    })
    @GET
    public Response pridobiUporabnike()
    {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long uporabnikiCount = uBean.getUporabnikiCount(query);
        return Response
                .ok(uBean.getUporabniki(query))
                .header("X-Total-Count", uporabnikiCount)
                .build();
    }


    @Operation(description = "Vrne podrobnosti uporabnika", summary = "Podrobnosti uporabnika",
            tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Podrobnosti uporabnika",
                    content = @Content(schema = @Schema(implementation = Uporabnik.class))
            )
    })
    @GET
    @Path("{id}")
    public Response pridobiUporabnika(@PathParam("id") Integer id) {

        Uporabnik u = uBean.getUporabnik(id);

        if(u != null){
            return Response.ok(u).build();
        }else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Operation(description = "Dodaj uporabnika", summary = "Dodajanje uporabnika",
            tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Uporabnik uspešno dodan"
            ),
            @ApiResponse(responseCode = "405", description = "Napaka")
    })
    @POST
    @BeleziKlice
    public Response dodajUporabnika(Uporabnik u){

        return Response
                .status(Response.Status.CREATED)
                .entity(uBean.addUporabnik(u))
                .build();
    }


    @Operation(description = "Posodobi uporabnika", summary = "Posodabljanje uporabnika",
            tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "201", description = "Uporabnik uspešno posodobljen"
            )
    })
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik u) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uBean.updateUporabnik(id,u))
                .build();
    }

    @Operation(description = "Odstrani uporabnika", summary = "Brisanje uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Uporabnik uspešno odstranjen"),
                    @ApiResponse(responseCode = "404", description = "Uporabnik ne obstaja")

    })
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(uBean.deleteUporabnik(id))
                .build();
    }
}
