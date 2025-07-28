package repository;

import java.util.List;

import repository.modelo.Cliente;
import repository.modelo.Factura;

public interface IClienteRepo {
    void insertar(Cliente cliente);

    Cliente buscarPorCedula(String cedula);

    List<Cliente> buscarPorNombre(String nombre);

    List<Cliente> buscarTodos();

    void actualizar(Cliente cliente);

    void eliminar(Cliente cliente);

    List<Factura> buscarFacturasPorCedulaCliente(String cedula);

}
