package repository.modelo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_codigo_barras", unique = true)
    private String codigoBarras;

    @Column(name = "prod_nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_categoria")
    private CategoriaProducto categoria;

    @Column(name = "prod_stock")
    private Integer stock;

    @Column(name = "prod_precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "prod_impuestos")
    private String impuestos; // JSON string con lista de impuestos

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prod_bode_id")
    private Bodega bodega;

    public enum CategoriaProducto {
        PRODUCTO, SERVICIO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }
}
