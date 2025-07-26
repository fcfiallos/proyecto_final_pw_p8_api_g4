package service;

import repository.modelo.Producto;
import service.to.ProductoTO;

public interface IProductoService {
    public Producto buscarPorCodigoBarras(String codigoBarras);

    public void guardar(ProductoTO producto);

    public void actualizarParcialPorCodigoBarras(ProductoTO producto, String codigoBarras);

    public void borrar(String codigoBarras);
}
