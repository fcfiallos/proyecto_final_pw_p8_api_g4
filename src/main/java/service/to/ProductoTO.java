package service.to;

import repository.modelo.Producto.CategoriaProducto;
import java.math.BigDecimal;
import java.util.List;

public class ProductoTO {
    private String codigoBarras;
    private String nombre;
    private CategoriaProducto categoria;
    private Integer stock;
    private BigDecimal precio;
    private List<String> impuestos;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<String> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<String> impuestos) {
        this.impuestos = impuestos;
    }
}
