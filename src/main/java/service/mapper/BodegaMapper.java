package service.mapper;

import repository.modelo.Bodega;
import service.to.BodegaTO;

public class BodegaMapper {
    public static BodegaTO toTo(Bodega bodega) {
        BodegaTO bodegaTO = new BodegaTO();
        bodegaTO.setCodigo(bodega.getCodigo());
        bodegaTO.setNombre(bodega.getNombre());
        bodegaTO.setUbicacion(bodega.getUbicacion());
        return bodegaTO;
    }

    public static Bodega toEntity(BodegaTO bodegaTO) {
        Bodega bodega = new Bodega();
        bodega.setCodigo(bodegaTO.getCodigo());
        bodega.setNombre(bodegaTO.getNombre());
        bodega.setUbicacion(bodegaTO.getUbicacion());
        return bodega;
    }

    public static void actualizarTO(Bodega bodega, BodegaTO bodegaTO) {
        if (bodegaTO.getNombre() != null) {
            bodega.setNombre(bodegaTO.getNombre());
        }
        if (bodegaTO.getUbicacion() != null) {
            bodega.setUbicacion(bodegaTO.getUbicacion());
        }
        if (bodegaTO.getCodigo() != null) {
            bodega.setCodigo(bodegaTO.getCodigo());
        }
    }
}
