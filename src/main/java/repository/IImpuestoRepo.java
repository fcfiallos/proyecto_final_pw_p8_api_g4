package repository;

import repository.modelo.Impuesto;
import java.util.List;

public interface IImpuestoRepo {
    public List<Impuesto> seleccionarTodos();
    
    public Impuesto seleccionarPorId(Integer id);
    
    public Impuesto seleccionarPorNombre(String nombre);
    
    public void insertar(Impuesto impuesto);
    
    public void actualizar(Impuesto impuesto);
    
    public void eliminar(Integer id);
    
    public boolean existePorNombre(String nombre);
}
