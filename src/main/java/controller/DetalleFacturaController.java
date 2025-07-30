package controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.IDetalleFacturaService;
import service.to.DetalleFacturaTO;

@Path("/detalle")
public class DetalleFacturaController {

    @Inject
    private IDetalleFacturaService detalleFacturaService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarDetalle(@RequestBody DetalleFacturaTO detalleFacturaTO) {
        this.detalleFacturaService.crearDetalle(detalleFacturaTO);
        return Response.status(Response.Status.CREATED).entity("Detalle creado exitosamente.").build();
    }

    @POST
    @Path("/lote")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarDetalles(@RequestBody List<DetalleFacturaTO> detalles) {
        for (DetalleFacturaTO detalle : detalles) {
            this.detalleFacturaService.crearDetalle(detalle);
        }
        return Response.status(Response.Status.CREATED).entity("Detalles creados exitosamente.").build();
    }

}
