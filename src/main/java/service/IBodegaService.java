package service;

import repository.modelo.Bodega;
import service.to.BodegaTO;
import java.util.List;

public interface IBodegaService {
    public List<Bodega> obtenerTodas();
    
    public Bodega buscarPorCodigo(String codigo);

    public void guardar(BodegaTO bodega);

    public void actualizarParcialPorCodigo(BodegaTO bodega, String codigo);

    public void borrar(String codigo);
}
