package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IBodegaRepo;
import repository.IProductoRepo;
import repository.modelo.Bodega;
import repository.modelo.Producto;
import service.mapper.ProductoMapper;
import service.to.ProductoTO;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService {
    @Inject
    private IProductoRepo productoRepo;
    
    @Inject
    private IBodegaRepo bodegaRepo;

    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
    }

    @Override
    public void guardar(ProductoTO productoTO) {
        Producto nuevoProducto = ProductoMapper.toEntity(productoTO);
        
        // Establecer la bodega si se proporciona el código
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            Bodega bodega = this.bodegaRepo.seleccionarPorCodigo(productoTO.getCodigoBodega());
            if (bodega != null) {
                nuevoProducto.setBodega(bodega);
            }
        }
        
        this.productoRepo.insertar(nuevoProducto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(ProductoTO productoTO, String codigoBarras) {
        Producto actualProducto = this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
        ProductoMapper.actualizarTO(actualProducto, productoTO);
        
        // Actualizar la bodega si se proporciona el código
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            Bodega bodega = this.bodegaRepo.seleccionarPorCodigo(productoTO.getCodigoBodega());
            if (bodega != null) {
                actualProducto.setBodega(bodega);
            }
        }
        
        this.productoRepo.actualizarParcialPorCodigoBarras(actualProducto);
    }

    @Override
    public void borrar(String codigoBarras) {
        this.productoRepo.eliminar(codigoBarras);
    }
}
