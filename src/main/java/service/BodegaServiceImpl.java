package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IBodegaRepo;
import repository.modelo.Bodega;
import service.mapper.BodegaMapper;
import service.to.BodegaTO;
import java.util.List;

@ApplicationScoped
public class BodegaServiceImpl implements IBodegaService {
    @Inject
    private IBodegaRepo bodegaRepo;

    @Override
    public List<Bodega> obtenerTodas() {
        return this.bodegaRepo.seleccionarTodas();
    }

    @Override
    public Bodega buscarPorCodigo(String codigo) {
        return this.bodegaRepo.seleccionarPorCodigo(codigo);
    }

    @Override
    public void guardar(BodegaTO bodega) {
        Bodega nuevBodega = BodegaMapper.toEntity(bodega);
        this.bodegaRepo.insertar(nuevBodega);
    }

    @Override
    public void actualizarParcialPorCodigo(BodegaTO bodega, String codigo) {
        Bodega actualBodega = this.bodegaRepo.seleccionarPorCodigo(codigo);
        BodegaMapper.actualizarTO(actualBodega, bodega);
        this.bodegaRepo.actualizarParcialPorCodigo(actualBodega);
    }

    @Override
    public void borrar(String codigo) {
        this.bodegaRepo.eliminar(codigo);
    }
}
