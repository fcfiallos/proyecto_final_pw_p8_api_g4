package repository;

import repository.modelo.Producto;

public interface IProductoRepo {
    public Producto seleccionarPorCodigoBarras(String codigoBarras);

    public void insertar(Producto producto);

    public void actualizarParcialPorCodigoBarras(Producto producto);

    public void eliminar(String codigoBarras);
}
