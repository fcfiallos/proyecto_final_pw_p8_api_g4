package repository;

import java.util.List;

import repository.modelo.Cliente;

public interface IClienteRepo {
    public Cliente buscarPorId(Long id);

    public List<Cliente> buscarTodos();

    public void actualizarporId(Cliente cliente);

    public void actualizarParcialPorId(Cliente cliente);

    public void eliminarPorId(Long id);

    public void insertar(Cliente cliente);

}
