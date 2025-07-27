package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IDetalleFacturaRepo;
import service.mapper.DetalleFacturaMapper;
import service.to.DetalleFacturaTO;

@ApplicationScoped
public class DetalleFacturaServiceImpl implements IDetalleFacturaService{

    @Inject
    private IDetalleFacturaRepo detalleFacturaRepo;

    @Override
    public void crearDetalle(DetalleFacturaTO detalleFactura) {
        if (detalleFactura != null) {
            this.detalleFacturaRepo.insertar(DetalleFacturaMapper.toEntity(detalleFactura));
        }
    }

    @Override
    public List<DetalleFacturaTO> listarDetallePorIdFactura(Integer id) {
        return DetalleFacturaMapper.toTO(this.detalleFacturaRepo.obtenerDetallePorIdFactura(id));
    }

}
