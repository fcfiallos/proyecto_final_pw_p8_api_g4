package repository;

import repository.modelo.Bodega;

public interface IBodegaRepo {
    public Bodega seleccionarPorCodigo(String codigo);

    public void insertar(Bodega bodega);

    public void actualizarParcialPorCodigo(Bodega bodega);

    public void eliminar(String codigo);
}
