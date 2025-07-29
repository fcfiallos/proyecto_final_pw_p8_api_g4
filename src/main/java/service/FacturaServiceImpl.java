package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IClienteRepo;
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

    @Inject
    private IClienteRepo clienteRepo;

    @Override
    public void crearFactura(FacturaTO facturaTO) {
        if (facturaTO != null) {
            Factura factura = FacturaMapper.toEntity(facturaTO);
            if (facturaTO.getCedulaCliente() != null) {
                var cliente = clienteRepo.buscarPorCedula(facturaTO.getCedulaCliente());
                if (cliente == null) {
                    throw new RuntimeException("Cliente no existe");
                }
                factura.setCliente(cliente);
            } else {
                throw new RuntimeException("Debe especificar el cliente");
            }
            this.facturaRepo.insertar(factura);
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
