package service.mapper;

import repository.modelo.Bodega;
import service.to.BodegaTO;

public class BodegaMapper {
    public static BodegaTO toTo(Bodega bodega) {
        if (bodega == null) {
            return null;
        }
        
        BodegaTO bodegaTO = new BodegaTO();
        bodegaTO.setId(bodega.getId());
        bodegaTO.setCodigo(bodega.getCodigo());
        bodegaTO.setNombre(bodega.getNombre());
        bodegaTO.setUbicacion(bodega.getUbicacion());
        return bodegaTO;
    }

    public static Bodega toEntity(BodegaTO bodegaTO) {
        if (bodegaTO == null) {
            return null;
        }
        
        Bodega bodega = new Bodega();
        bodega.setId(bodegaTO.getId());
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
        // Nota: No actualizamos el ID porque es generado automáticamente
    }
}
