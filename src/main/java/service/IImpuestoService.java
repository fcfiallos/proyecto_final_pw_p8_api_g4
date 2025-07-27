package service;

import repository.modelo.Impuesto;
import service.to.ImpuestoTO;
import java.util.List;

public interface IImpuestoService {
    public List<Impuesto> obtenerTodos();
    
    public Impuesto buscarPorId(Integer id);
    
    public Impuesto buscarPorNombre(String nombre);
    
    public void guardar(ImpuestoTO impuestoTO);
    
    public void actualizar(Integer id, ImpuestoTO impuestoTO);
    
    public void eliminar(Integer id);
    
    public boolean existePorNombre(String nombre);
}
