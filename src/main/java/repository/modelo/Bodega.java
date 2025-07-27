package repository.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bodega")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bode_id")
    private Integer id;
    @Column(name = "bode_codigo", unique = true)
    private String codigo;
    @Column(name = "bode_nombre")
    private String nombre;
    @Column(name = "bode_ubicacion")
    private String ubicacion;

    @OneToMany(mappedBy = "bodega")
    private List<Producto> producto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }
}
