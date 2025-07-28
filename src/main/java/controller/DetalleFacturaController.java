package controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.IDetalleFacturaService;
import service.to.DetalleFacturaTO;

@Path("/detalles")
public class DetalleFacturaController {

    @Inject
    private IDetalleFacturaService detalleFacturaService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDetallesPorIdFactura(@PathParam("id") Integer id) {
        List<DetalleFacturaTO> detalles = this.detalleFacturaService.listarDetallePorIdFactura(id);
        return Response.ok(detalles).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarDetalle(@RequestBody DetalleFacturaTO detalleFacturaTO) {
        this.detalleFacturaService.crearDetalle(detalleFacturaTO);
        return Response.status(Response.Status.CREATED).entity("Detalle creado exitosamente.").build();
    }
    
}
