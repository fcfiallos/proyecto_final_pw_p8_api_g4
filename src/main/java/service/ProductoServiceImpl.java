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
        // DEBUG: Mostrar todo lo que llega del frontend
        System.out.println("=== DEBUG PRODUCTO RECIBIDO ===");
        System.out.println("Código de barras: " + productoTO.getCodigoBarras());
        System.out.println("Nombre: " + productoTO.getNombre());
        System.out.println("Código Bodega: '" + productoTO.getCodigoBodega() + "'");
        System.out.println("Código Bodega es null? " + (productoTO.getCodigoBodega() == null));
        System.out.println("Bodega TO: " + productoTO.getBodega());
        if (productoTO.getBodega() != null) {
            System.out.println("Bodega TO código: " + productoTO.getBodega().getCodigo());
            System.out.println("Bodega TO ID: " + productoTO.getBodega().getId());
        }
        System.out.println("==============================");
        
        Producto nuevoProducto = ProductoMapper.toEntity(productoTO);
        
        // Intentar establecer la bodega de varias formas
        String codigoBodega = null;
        
        // Opción 1: Si viene codigoBodega directamente
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            codigoBodega = productoTO.getCodigoBodega();
            System.out.println("DEBUG: Usando codigoBodega directo: " + codigoBodega);
        }
        // Opción 2: Si viene el objeto bodega completo (frontend actual)
        else if (productoTO.getBodega() != null && productoTO.getBodega().getCodigo() != null) {
            codigoBodega = productoTO.getBodega().getCodigo();
            System.out.println("DEBUG: Extrayendo código del objeto bodega: " + codigoBodega);
        }
        
        // Buscar y establecer la bodega
        if (codigoBodega != null && !codigoBodega.trim().isEmpty()) {
            Bodega bodega = this.bodegaRepo.seleccionarPorCodigo(codigoBodega);
            if (bodega != null) {
                System.out.println("DEBUG: Bodega encontrada - ID: " + bodega.getId() + ", Código: " + bodega.getCodigo());
                nuevoProducto.setBodega(bodega);
                System.out.println("DEBUG: Bodega establecida en producto - ID: " + nuevoProducto.getBodega().getId());
            } else {
                System.out.println("DEBUG: Bodega NO encontrada para código: " + codigoBodega);
            }
        } else {
            System.out.println("DEBUG: No se pudo obtener código de bodega de ninguna forma");
        }
        
        this.productoRepo.insertar(nuevoProducto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(ProductoTO productoTO, String codigoBarras) {
        Producto actualProducto = this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
        ProductoMapper.actualizarTO(actualProducto, productoTO);
        
        // Intentar establecer la bodega de varias formas
        String codigoBodegaActualizar = null;
        
        // Opción 1: Si viene codigoBodega directamente
        if (productoTO.getCodigoBodega() != null && !productoTO.getCodigoBodega().trim().isEmpty()) {
            codigoBodegaActualizar = productoTO.getCodigoBodega();
        }
        // Opción 2: Si viene el objeto bodega completo
        else if (productoTO.getBodega() != null && productoTO.getBodega().getCodigo() != null) {
            codigoBodegaActualizar = productoTO.getBodega().getCodigo();
        }
        
        // Actualizar la bodega si se proporciona el código
        if (codigoBodegaActualizar != null && !codigoBodegaActualizar.trim().isEmpty()) {
            Bodega bodega = this.bodegaRepo.seleccionarPorCodigo(codigoBodegaActualizar);
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
