package service;

import java.util.List;

import service.to.FacturaTO;

public interface IFacturaService {

    void crearFactura(FacturaTO facturaTO);

    List<FacturaTO> listarFacturas();

}
