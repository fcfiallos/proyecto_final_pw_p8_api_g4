package service.mapper;

import repository.modelo.Producto;
import service.to.ProductoTO;

import java.util.ArrayList;

public class ProductoMapper {

    public static ProductoTO toTo(Producto producto) {
        if (producto == null) {
            return null;
        }
        
        ProductoTO productoTO = new ProductoTO();
        productoTO.setCodigoBarras(producto.getCodigoBarras());
        productoTO.setNombre(producto.getNombre());
        productoTO.setCategoria(producto.getCategoria());
        productoTO.setStock(producto.getStock());
        productoTO.setPrecio(producto.getPrecio());
        
        // Convertir lista de impuestos usando ImpuestoMapper
        if (producto.getImpuestos() != null) {
            productoTO.setImpuestos(ImpuestoMapper.toTOList(producto.getImpuestos()));
        } else {
            productoTO.setImpuestos(new ArrayList<>());
        }
        
        // Convertir información de bodega
        if (producto.getBodega() != null) {
            productoTO.setCodigoBodega(producto.getBodega().getCodigo());
            productoTO.setBodega(BodegaMapper.toTo(producto.getBodega()));
        }
        
        return productoTO;
    }

    public static Producto toEntity(ProductoTO productoTO) {
        if (productoTO == null) {
            return null;
        }
        
        Producto producto = new Producto();
        producto.setCodigoBarras(productoTO.getCodigoBarras());
        producto.setNombre(productoTO.getNombre());
        producto.setCategoria(productoTO.getCategoria());
        producto.setStock(productoTO.getStock());
        producto.setPrecio(productoTO.getPrecio());
        
        // Convertir lista de impuestos usando ImpuestoMapper
        if (productoTO.getImpuestos() != null) {
            producto.setImpuestos(ImpuestoMapper.toEntityList(productoTO.getImpuestos()));
        }
        
        // NOTA: La bodega se establecerá en el servicio usando el codigoBodega
        // para evitar inyección de dependencias en el mapper
        
        return producto;
    }

    public static void actualizarTO(Producto producto, ProductoTO productoTO) {
        if (productoTO.getNombre() != null) {
            producto.setNombre(productoTO.getNombre());
        }
        if (productoTO.getCategoria() != null) {
            producto.setCategoria(productoTO.getCategoria());
        }
        if (productoTO.getStock() != null) {
            producto.setStock(productoTO.getStock());
        }
        if (productoTO.getPrecio() != null) {
            producto.setPrecio(productoTO.getPrecio());
        }
        if (productoTO.getCodigoBarras() != null) {
            producto.setCodigoBarras(productoTO.getCodigoBarras());
        }
        if (productoTO.getImpuestos() != null) {
            producto.setImpuestos(ImpuestoMapper.toEntityList(productoTO.getImpuestos()));
        }
        // NOTA: La bodega se actualizará en el servicio usando el codigoBodega
    }
}
