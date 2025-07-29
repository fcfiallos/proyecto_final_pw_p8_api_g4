package repository;

import repository.modelo.Bodega;
import java.util.List;

public interface IBodegaRepo {
    public List<Bodega> seleccionarTodas();
    
    public Bodega seleccionarPorCodigo(String codigo);
    
    public Bodega seleccionarPorId(Integer id);

    public void insertar(Bodega bodega);

    public void actualizarParcialPorCodigo(Bodega bodega);

    public void eliminar(String codigo);
}
