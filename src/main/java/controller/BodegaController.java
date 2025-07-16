package controller;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.IBodegaService;
import service.mapper.BodegaMapper;
import service.to.BodegaTO;

@Path("/bodegas")
public class BodegaController {
    @Inject
    private IBodegaService bodegaService;

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorCodigo(@PathParam("codigo") String codigo) {
        BodegaTO bodegaTO = BodegaMapper.toTo(this.bodegaService.buscarPorCodigo(codigo));
        return Response.ok(bodegaTO).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody BodegaTO bodegaTO) {
        this.bodegaService.guardar(bodegaTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("codigo") String codigo, @RequestBody BodegaTO bodegaTO) {
        this.bodegaService.actualizarParcialPorCodigo(bodegaTO, codigo);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response borrar(@PathParam("codigo") String codigo) {
        this.bodegaService.borrar(codigo);
        return Response.noContent().build();
    }
}
