package service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import repository.modelo.Producto;
import service.to.ProductoTO;

import java.util.ArrayList;
import java.util.List;

public class ProductoMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ProductoTO toTo(Producto producto) {
        ProductoTO productoTO = new ProductoTO();
        productoTO.setCodigoBarras(producto.getCodigoBarras());
        productoTO.setNombre(producto.getNombre());
        productoTO.setCategoria(producto.getCategoria());
        productoTO.setStock(producto.getStock());
        productoTO.setPrecio(producto.getPrecio());
        
        // Convertir JSON string a lista
        try {
            if (producto.getImpuestos() != null && !producto.getImpuestos().isEmpty()) {
                List<String> impuestos = objectMapper.readValue(producto.getImpuestos(), new TypeReference<List<String>>() {});
                productoTO.setImpuestos(impuestos);
            }
        } catch (JsonProcessingException e) {
            productoTO.setImpuestos(new ArrayList<>());
        }
        
        return productoTO;
    }

    public static Producto toEntity(ProductoTO productoTO) {
        Producto producto = new Producto();
        producto.setCodigoBarras(productoTO.getCodigoBarras());
        producto.setNombre(productoTO.getNombre());
        producto.setCategoria(productoTO.getCategoria());
        producto.setStock(productoTO.getStock());
        producto.setPrecio(productoTO.getPrecio());
        
        // Convertir lista a JSON string
        try {
            if (productoTO.getImpuestos() != null) {
                String impuestosJson = objectMapper.writeValueAsString(productoTO.getImpuestos());
                producto.setImpuestos(impuestosJson);
            }
        } catch (JsonProcessingException e) {
            producto.setImpuestos("[]");
        }
        
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
            try {
                String impuestosJson = objectMapper.writeValueAsString(productoTO.getImpuestos());
                producto.setImpuestos(impuestosJson);
            } catch (JsonProcessingException e) {
                producto.setImpuestos("[]");
            }
        }
    }
}
