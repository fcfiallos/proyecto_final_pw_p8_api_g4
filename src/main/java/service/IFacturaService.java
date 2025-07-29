package service;

import java.util.List;

import service.to.FacturaReporteTO;
import service.to.FacturaTO;

public interface IFacturaService {

    void crearFactura(FacturaTO facturaTO);

    List<FacturaTO> listarFacturas();

    List<FacturaReporteTO> obtenerReporteFacturas();
    
    List<FacturaReporteTO> obtenerReporteFacturasPorCedula(String cedula);


}
