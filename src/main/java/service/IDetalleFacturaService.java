package service;

import java.util.List;

import service.to.DetalleFacturaTO;

public interface IDetalleFacturaService {

    void crearDetalle(DetalleFacturaTO detalleFactura);

    List<DetalleFacturaTO> listarDetallePorIdFactura(Integer id);

}
