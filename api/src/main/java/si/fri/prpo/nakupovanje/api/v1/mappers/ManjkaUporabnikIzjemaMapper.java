package si.fri.prpo.nakupovanje.api.v1.mappers;

import si.fri.prpo.nakupovanje.storitve.izjeme.ManjkaUporabnikIzjema;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ManjkaUporabnikIzjemaMapper implements ExceptionMapper<ManjkaUporabnikIzjema> {

    @Override
    public Response toResponse(ManjkaUporabnikIzjemaMapper exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}")
                .build();
    }

}
