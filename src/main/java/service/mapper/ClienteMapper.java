package service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import repository.modelo.Cliente;
import service.to.ClienteTo;

public class ClienteMapper {
    public static ClienteTo toTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        ClienteTo to = new ClienteTo();
        to.setCedula(cliente.getCedula());
        to.setNombre(cliente.getNombre());
        to.setApellido(cliente.getApellido());
        to.setRazonSocial(cliente.getRazonSocial());
        to.setDireccion(cliente.getDireccion());
        to.setTelefono(cliente.getTelefono());
        to.setEmail(cliente.getEmail());
        return to;
    }

    public static Cliente toEntity(ClienteTo clienteTo) {
        if (clienteTo == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(clienteTo.getCedula());
        cliente.setNombre(clienteTo.getNombre());
        cliente.setApellido(clienteTo.getApellido());
        cliente.setRazonSocial(clienteTo.getRazonSocial());
        cliente.setDireccion(clienteTo.getDireccion());
        cliente.setTelefono(clienteTo.getTelefono());
        cliente.setEmail(clienteTo.getEmail());

        return cliente;
    }

    public static List<ClienteTo> toTO(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::toTO).collect(Collectors.toList());
    }

}
