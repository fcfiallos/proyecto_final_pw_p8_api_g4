package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import repository.IClienteRepo;
import repository.modelo.Cliente;
import service.mapper.ClienteMapper;
import service.to.ClienteTo;

@ApplicationScoped
public class ClienteServiceImpl implements iClienteService {
    @Inject
    private IClienteRepo clienteRepo;

    @Override
    public void crearCliente(ClienteTo clienteTo) {
        if (this.clienteRepo.buscarPorCedula(clienteTo.getCedula()) != null) {
            throw new BadRequestException("Ya existe un cliente registrado con la cédula: " + clienteTo.getCedula());
        }

        this.clienteRepo.insertar(ClienteMapper.toEntity(clienteTo));
    }

    @Override
    public ClienteTo buscarPorCedulaCliente(String cedula) {
        Cliente cliente = this.clienteRepo.buscarPorCedula(cedula);

        if (cliente == null) {
            throw new NotFoundException("No se encontró ningún cliente con la cédula: " + cedula);
        }

        return ClienteMapper.toTO(cliente);
    }

    @Override
    public List<ClienteTo> buscarPorNombreCliente(String nombre) {
        return ClienteMapper.toTO(this.clienteRepo.buscarPorNombre(nombre));
    }

    @Override
    public List<ClienteTo> buscarTodos() {
        return ClienteMapper.toTO(this.clienteRepo.buscarTodos());
    }

    @Override
    public void actualizarCliente(ClienteTo clienteTo) {
        Cliente clienteExistente = this.clienteRepo.buscarPorCedula(clienteTo.getCedula());
        if (clienteExistente == null) {
            throw new NotFoundException(
                    "Imposible actualizar. No se encontró cliente con cédula: " + clienteTo.getCedula());
        }

        Cliente clienteParaActualizar = ClienteMapper.toEntity(clienteTo);
        clienteParaActualizar.setId(clienteExistente.getId());
        this.clienteRepo.actualizar(clienteParaActualizar);
    }

    @Override
    public void eliminarPorCedula(String cedula) {
        Cliente clienteAEliminar = this.clienteRepo.buscarPorCedula(cedula);
        if (clienteAEliminar == null) {
            throw new NotFoundException("Imposible eliminar. No se encontró cliente con cédula: " + cedula);
        }
        this.clienteRepo.eliminar(clienteAEliminar);
    }

}
