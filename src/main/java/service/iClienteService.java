package service;

import java.util.List;

import service.to.ClienteTo;

public interface iClienteService {
    void crearCliente(ClienteTo clienteTo);

    ClienteTo buscarPorCedulaCliente(String cedula);

    List<ClienteTo> buscarPorNombreCliente(String nombre);

    List<ClienteTo> buscarTodos();

    void actualizarCliente(ClienteTo clienteTo);

    void eliminarPorCedula(String cedula);

}
