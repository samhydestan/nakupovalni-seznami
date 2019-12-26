package si.fri.prpo.nakupovanje.api.v1.viri;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovanje.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovanje.storitve.bean.NakupovalniSeznamBean;
import si.fri.prpo.nakupovanje.storitve.bean.NakupovalniSeznamManagerBean;
import si.fri.prpo.nakupovanje.storitve.dto.*;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;


@ApplicationScoped
@Path("nakupovalniSeznami")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class NakupovalniSeznamVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private NakupovalniSeznamBean nsBean;

    @Inject
    private NakupovalniSeznamManagerBean nsmBean;


    @Operation(description = "Vrne seznam nakupovalnih seznamov", summary = "Seznam nakupovalnih seznamov",
            tags = "nakupovalni_seznami", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam nakupovalnih seznamov",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = NakupovalniSeznam.class))),
                    headers = {@Header(name = "X-Total-Count", description = "Št. vrnjenih nakupovalnih seznamov")})
    })
    @GET
    public Response pridobiNakupovalniSeznam(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long uporabnikiCount = nsBean.getNakupovalniSeznamiCount(query);
        return Response
                .ok(nsBean.getNakupovalniSeznami(query))
                .header("X-Total-Count", uporabnikiCount)
                .build();

    }

    @Operation(description = "Vrne podrobnosti nakupovalnega seznama", summary = "Podrobnosti nakupovalnega seznama",
            tags = "nakupovalni_seznami", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Podrobnosti nakupovalnega seznama",
                    content = @Content(schema = @Schema(implementation = NakupovalniSeznam.class))
            )
    })
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

    @Operation(description = "Posodobi nakupovalni seznam", summary = "Posodabljanje nakupovalnega seznama",
            tags = "nakupovalni_seznami", responses = {
            @ApiResponse(responseCode = "201", description = "Nakupovalni seznam uspešno posodobljen"
            )
    })
    @PUT
    @Path("{id}")
    public Response posodobiNakupovalniSeznam(@PathParam("id") Integer id, NakupovalniSeznam ns) {
        return Response
                .status(Response.Status.CREATED)
                .entity(nsBean.updateNakupovalniSeznam(id,ns))
                .build();
    }


    @Operation(description = "Odstrani nakupovalni seznam", summary = "Brisanje nakupovalnega seznama",
            tags = "nakupovalni_seznami",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Nakupovalni seznam uspešno odstranjen"),
                    @ApiResponse(responseCode = "404", description = "Nakupovalni seznam ne obstaja")

    })
    @DELETE
    @Path("{id}")
    public Response odstraniNakupovalniSeznam(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(nsBean.deleteNakupovalniSeznam(id))
                .build();
    }


    //poslovne metode
    @Operation(description = "Dodaj nakupovalni seznam", summary = "Dodajanje nakupovalnega seznama",
            tags = "nakupovalni_seznami", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Nakupovalni seznam uspešno dodan"
            ),
            @ApiResponse(responseCode = "405", description = "Validacijska napaka")
    })
    @POST
    public Response dodajNakupovalniSeznam(NakupovalniSeznamCreateDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.createNakupovalniSeznam(ns))
                .build();
    }


    @POST
    @Path("dodajartikel")
    public Response dodajArtikelIntoNakupovalniSeznam(ArtikelAddDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.addArtikelIntoNakupovalniSeznam(ns))
                .build();
    }

    @POST
    @Path("dodajkategorijo")
    public Response dodajKategorijaIntoNakupovalniSeznam(KategorijaAddDTO ns){

        return Response
                .status(Response.Status.CREATED)
                .entity(nsmBean.addKategorijaIntoNakupovalniSeznam(ns))
                .build();
    }



}
