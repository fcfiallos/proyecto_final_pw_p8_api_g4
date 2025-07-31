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
        
        String codigoBodega = null;
        Integer idBodega = null;
        
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            codigoBodega = productoTO.getCodigoBodega();
        }
        else if (productoTO.getBodega() != null && productoTO.getBodega().getCodigo() != null) {
            codigoBodega = productoTO.getBodega().getCodigo();
        }
        else if (productoTO.getBodega() != null && productoTO.getBodega().getId() != null) {
            idBodega = productoTO.getBodega().getId();
        }
        
        Bodega bodega = null;
        if (codigoBodega != null && !codigoBodega.trim().isEmpty()) {
            bodega = this.bodegaRepo.seleccionarPorCodigo(codigoBodega);
        } else if (idBodega != null) {
            bodega = this.bodegaRepo.seleccionarPorId(idBodega);
        }
        
        if (bodega != null) {
            nuevoProducto.setBodega(bodega);
        }
        
        this.productoRepo.insertar(nuevoProducto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(ProductoTO productoTO, String codigoBarras) {
        Producto actualProducto = this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
        ProductoMapper.actualizarTO(actualProducto, productoTO);
        
        String codigoBodegaActualizar = null;
        Integer idBodegaActualizar = null;
        
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            codigoBodegaActualizar = productoTO.getCodigoBodega();
        }
        else if (productoTO.getBodega() != null && productoTO.getBodega().getCodigo() != null) {
            codigoBodegaActualizar = productoTO.getBodega().getCodigo();
        }
        else if (productoTO.getBodega() != null && productoTO.getBodega().getId() != null) {
            idBodegaActualizar = productoTO.getBodega().getId();
        }
        
        Bodega bodegaActualizar = null;
        if (codigoBodegaActualizar != null && !codigoBodegaActualizar.trim().isEmpty()) {
            bodegaActualizar = this.bodegaRepo.seleccionarPorCodigo(codigoBodegaActualizar);
        } else if (idBodegaActualizar != null) {
            bodegaActualizar = this.bodegaRepo.seleccionarPorId(idBodegaActualizar);
        }
        
        if (bodegaActualizar != null) {
            actualProducto.setBodega(bodegaActualizar);
        }
        
        this.productoRepo.actualizarParcialPorCodigoBarras(actualProducto);
    }

    @Override
    public void borrar(String codigoBarras) {
        this.productoRepo.eliminar(codigoBarras);
    }
}
