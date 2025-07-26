package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IProductoRepo;
import repository.modelo.Producto;
import service.mapper.ProductoMapper;
import service.to.ProductoTO;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService {
    @Inject
    private IProductoRepo productoRepo;

    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
    }

    @Override
    public void guardar(ProductoTO producto) {
        Producto nuevoProducto = ProductoMapper.toEntity(producto);
        this.productoRepo.insertar(nuevoProducto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(ProductoTO producto, String codigoBarras) {
        Producto actualProducto = this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
        ProductoMapper.actualizarTO(actualProducto, producto);
        this.productoRepo.actualizarParcialPorCodigoBarras(actualProducto);
    }

    @Override
    public void borrar(String codigoBarras) {
        this.productoRepo.eliminar(codigoBarras);
    }
}
