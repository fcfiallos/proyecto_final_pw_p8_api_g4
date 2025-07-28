package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IFacturaRepo;
import service.mapper.FacturaMapper;
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
        return this.facturaRepo.obtenerFacturas().stream().map(f -> {
            FacturaReporteTO reporte = new FacturaReporteTO();
            reporte.setNumeroDocumento(f.getNumeroDocumento());
            reporte.setIdentificacionCliente(f.getCliente().getCedula());
            reporte.setNombreCliente(f.getCliente().getNombre() + " " + f.getCliente().getApellido());
            reporte.setCantidadItems(f.getDetalles().size());
            reporte.setTotalImpuestos(f.getTotalImpuestos());
            reporte.setTotal(f.getTotal());
            return reporte;
        }).toList();
    }

}
