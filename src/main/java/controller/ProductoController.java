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
import service.IProductoService;
import service.mapper.ProductoMapper;
import service.to.ProductoTO;

@Path("/productos")
public class ProductoController {
    @Inject
    private IProductoService productoService;

    @GET
    @Path("/{codigoBarras}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorCodigoBarras(@PathParam("codigoBarras") String codigoBarras) {
        ProductoTO productoTO = ProductoMapper.toTo(this.productoService.buscarPorCodigoBarras(codigoBarras));
        return Response.ok(productoTO).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody ProductoTO productoTO) {
        System.out.println("=== DEBUG CONTROLLER ===");
        System.out.println("JSON recibido en controller - Código Bodega: '" + productoTO.getCodigoBodega() + "'");
        System.out.println("========================");
        
        this.productoService.guardar(productoTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    @Path("/{codigoBarras}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("codigoBarras") String codigoBarras, @RequestBody ProductoTO productoTO) {
        this.productoService.actualizarParcialPorCodigoBarras(productoTO, codigoBarras);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{codigoBarras}")
    public Response borrar(@PathParam("codigoBarras") String codigoBarras) {
        this.productoService.borrar(codigoBarras);
        return Response.noContent().build();
    }
}
