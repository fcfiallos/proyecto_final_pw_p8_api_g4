package repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "impuesto")
public class Impuesto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imp_id")
    private Integer id;
    
    @Column(name = "imp_nombre", unique = true, nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "imp_valor", precision = 5, scale = 4, nullable = false)
    private BigDecimal valor; // Valor del impuesto (ejemplo: 0.15 para 15%)
    
    @Column(name = "imp_descripcion", length = 200)
    private String descripcion;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "impuestos")
    private List<Producto> productos;
    
    // Constructores
    public Impuesto() {}
    
    public Impuesto(String nombre, BigDecimal valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public Impuesto(String nombre, BigDecimal valor, String descripcion) {
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    // Método de utilidad para calcular el valor del impuesto sobre un monto
    public BigDecimal calcularImpuesto(BigDecimal monto) {
        if (monto == null || valor == null) {
            return BigDecimal.ZERO;
        }
        return monto.multiply(valor);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Impuesto)) return false;
        Impuesto impuesto = (Impuesto) o;
        return nombre != null ? nombre.equals(impuesto.nombre) : impuesto.nombre == null;
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "Impuesto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
