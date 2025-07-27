package service.to;

import java.math.BigDecimal;

public class ImpuestoTO {
    private Integer id;
    private String nombre;
    private BigDecimal valor;
    private String descripcion;
    
    // Constructores
    public ImpuestoTO() {}
    
    public ImpuestoTO(String nombre, BigDecimal valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public ImpuestoTO(String nombre, BigDecimal valor, String descripcion) {
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
    
    @Override
    public String toString() {
        return "ImpuestoTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
