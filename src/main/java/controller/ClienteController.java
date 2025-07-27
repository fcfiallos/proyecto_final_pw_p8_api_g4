package controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.iClienteService;
import service.to.ClienteTo;

@Path("/clientes")
public class ClienteController {

    @Inject
    private iClienteService clienteService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCliente(@RequestBody ClienteTo clienteTo) {
        this.clienteService.crearCliente(clienteTo);
        return Response.status(Response.Status.CREATED).entity("Cliente creado exitosamente.").build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosClientes() {
        List<ClienteTo> clientes = this.clienteService.buscarTodos();
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientePorCedula(@PathParam("cedula") String cedula) {
        ClienteTo cliente = this.clienteService.buscarPorCedulaCliente(cedula);
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientesPorNombre(@QueryParam("nombre") String nombre) {
        List<ClienteTo> clientes = this.clienteService.buscarPorNombreCliente(nombre);
        return Response.ok(clientes).build();
    }

    @PUT
    @Path("/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCliente(@PathParam("cedula") String cedula, @RequestBody ClienteTo clienteTo) {

        if (clienteTo.getCedula() != null && !cedula.equals(clienteTo.getCedula())) {
            throw new BadRequestException("La cédula en la URL no coincide con la del cuerpo de la petición.");
        }
        clienteTo.setCedula(cedula);

        this.clienteService.actualizarCliente(clienteTo);
        return Response.ok("Cliente actualizado exitosamente.").build();
    }

    @DELETE
    @Path("/{cedula}")
    public Response eliminarCliente(@PathParam("cedula") String cedula) {
        this.clienteService.eliminarPorCedula(cedula);
        return Response.ok("Cliente eliminado exitosamente.").build();
    }

}
