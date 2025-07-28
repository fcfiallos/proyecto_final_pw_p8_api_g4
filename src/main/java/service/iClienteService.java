package service;

import java.util.List;

import service.to.ClienteTo;
import service.to.FacturaResumenTO;

public interface iClienteService {
    void crearCliente(ClienteTo clienteTo);

    ClienteTo buscarPorCedulaCliente(String cedula);

    List<ClienteTo> buscarPorNombreCliente(String nombre);

    List<ClienteTo> buscarTodos();

    void actualizarCliente(ClienteTo clienteTo);

    void eliminarPorCedula(String cedula);

    List<FacturaResumenTO> buscarFacturasPorCedula(String cedula);

}
