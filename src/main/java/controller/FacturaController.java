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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import service.IDetalleFacturaService;
import service.IFacturaService;
import service.to.DetalleFacturaTO;
import service.to.FacturaTO;

@Path("/facturas")
public class FacturaController {

    @Inject
    private IFacturaService facturaService;

    @Inject
    private IDetalleFacturaService detalleFacturaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerFacturas(@Context UriInfo uriInfo) {
        List<FacturaTO> facturaTOs = this.facturaService.listarFacturas();
        facturaTOs.forEach(facturaTO -> facturaTO.buildURI(uriInfo));
        return Response.ok(facturaTOs).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarFactura(@RequestBody FacturaTO facturaTo, @Context UriInfo uriInfo) {
        this.facturaService.crearFactura(facturaTo);
        return Response.status(Response.Status.CREATED).entity("Factura creada exitosamente.").build();
    }

    @GET
    @Path("/reporte")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteFacturas() {
        return Response.ok(this.facturaService.obtenerReporteFacturas()).build();
    }

    @GET
    @Path("/reporte/cliente/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReportePorCliente(@jakarta.ws.rs.PathParam("cedula") String cedula) {
        return Response.ok(this.facturaService.obtenerReporteFacturasPorCedula(cedula)).build();
    }

    @GET
    @Path("/{id}/detalle")
    public List<DetalleFacturaTO> obtenerDetallesPorId(@PathParam("id") Integer id) {
        return this.detalleFacturaService.listarDetallePorIdFactura(id);
    }

}
