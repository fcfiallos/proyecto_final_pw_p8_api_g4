package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IFacturaRepo;
import repository.modelo.Factura;
import service.mapper.FacturaMapper;
import service.mapper.ReporteFacturaMapper;
import service.to.FacturaTO;
import service.to.FacturaReporteTO;

@ApplicationScoped
public class FacturaServiceImpl implements IFacturaService {

    @Inject
    private IFacturaRepo facturaRepo;

    @Override
    public void crearFactura(FacturaTO facturaTO) {
        if (facturaTO != null) {
            this.facturaRepo.insertar(FacturaMapper.toEntity(facturaTO));
        }
    }

    @Override
    public List<FacturaTO> listarFacturas() {
        return FacturaMapper.toTO(this.facturaRepo.obtenerFacturas());
    }

    @Override
    public List<FacturaReporteTO> obtenerReporteFacturas() {
        return ReporteFacturaMapper.toTO(this.facturaRepo.obtenerFacturas());
    }

    @Override
    public List<FacturaReporteTO> obtenerReporteFacturasPorCedula(String cedula) {
        List<Factura> facturas = facturaRepo.obtenerFacturasPorCedulaCliente(cedula);
        return ReporteFacturaMapper.toTO(facturas);
    }

}
