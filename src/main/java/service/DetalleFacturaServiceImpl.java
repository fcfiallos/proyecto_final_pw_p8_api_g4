package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IDetalleFacturaRepo;
import repository.IFacturaRepo;
import repository.IProductoRepo;
import repository.modelo.DetalleFactura;
import repository.modelo.Factura;
import repository.modelo.Producto;
import service.mapper.DetalleFacturaMapper;
import service.to.DetalleFacturaTO;

@ApplicationScoped
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    @Inject
    private IDetalleFacturaRepo detalleFacturaRepo;

    @Inject
    private IProductoRepo productoRepo;

    @Inject
    private IFacturaRepo facturaRepo;

    @Override
    public void crearDetalle(DetalleFacturaTO detalleFactura) {
        if (detalleFactura != null) {
            DetalleFactura detalle = DetalleFacturaMapper.toEntity(detalleFactura);

            Producto producto = productoRepo.seleccionarPorCodigoBarras(detalleFactura.getCodigoBarras());
            if (producto == null) {
                throw new RuntimeException("Producto no existe");
            }
            detalle.setProducto(producto);

            Factura factura = this.facturaRepo.obtenerFacturaPorID(detalleFactura.getFacturaId());
            if (factura == null) {
                throw new RuntimeException("Factura no existe");
            }
            detalle.setFactura(factura);

            this.detalleFacturaRepo.insertar(detalle);
        }
    }

    @Override
    public List<DetalleFacturaTO> listarDetallePorIdFactura(Integer id) {
        return DetalleFacturaMapper.toTO(this.detalleFacturaRepo.obtenerDetallePorIdFactura(id));
    }

}
