package controller;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.IImpuestoService;
import service.mapper.ImpuestoMapper;
import service.to.ImpuestoTO;

@Path("/impuestos")
public class ImpuestoController {
    
    @Inject
    private IImpuestoService impuestoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() {
        var impuestos = this.impuestoService.obtenerTodos();
        var impuestosTO = ImpuestoMapper.toTOList(impuestos);
        return Response.ok(impuestosTO).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id) {
        var impuesto = this.impuestoService.buscarPorId(id);
        if (impuesto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var impuestoTO = ImpuestoMapper.toTO(impuesto);
        return Response.ok(impuestoTO).build();
    }

    @GET
    @Path("/nombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorNombre(@PathParam("nombre") String nombre) {
        var impuesto = this.impuestoService.buscarPorNombre(nombre);
        if (impuesto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var impuestoTO = ImpuestoMapper.toTO(impuesto);
        return Response.ok(impuestoTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody ImpuestoTO impuestoTO) {
        // Verificar si ya existe un impuesto con el mismo nombre
        if (this.impuestoService.existePorNombre(impuestoTO.getNombre())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Ya existe un impuesto con el nombre: " + impuestoTO.getNombre())
                    .build();
        }
        
        this.impuestoService.guardar(impuestoTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, @RequestBody ImpuestoTO impuestoTO) {
        var impuestoExistente = this.impuestoService.buscarPorId(id);
        if (impuestoExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Verificar si el nuevo nombre ya existe (excluyendo el impuesto actual)
        if (impuestoTO.getNombre() != null && 
            !impuestoTO.getNombre().equals(impuestoExistente.getNombre()) &&
            this.impuestoService.existePorNombre(impuestoTO.getNombre())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Ya existe un impuesto con el nombre: " + impuestoTO.getNombre())
                    .build();
        }
        
        this.impuestoService.actualizar(id, impuestoTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        var impuesto = this.impuestoService.buscarPorId(id);
        if (impuesto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        this.impuestoService.eliminar(id);
        return Response.noContent().build();
    }
}
