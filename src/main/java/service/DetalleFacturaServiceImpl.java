package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import repository.IDetalleFacturaRepo;
import repository.IFacturaRepo;
import repository.IProductoRepo;
import repository.modelo.DetalleFactura;
import repository.modelo.Factura;
import repository.modelo.Producto;
import repository.modelo.Producto.CategoriaProducto;
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
    @Transactional
    public void crearDetalle(DetalleFacturaTO detalleTO) {
        if (detalleTO == null) {
            throw new BadRequestException("El detalle de la factura no puede ser nulo.");
        }

        Producto producto = productoRepo.seleccionarPorCodigoBarras(detalleTO.getCodigoBarras());
        if (producto == null) {
            throw new NotFoundException("Producto con código " + detalleTO.getCodigoBarras() + " no existe.");
        }

        Factura factura = this.facturaRepo.obtenerFacturaPorID(detalleTO.getFacturaId());
        if (factura == null) {
            throw new NotFoundException("Factura con ID " + detalleTO.getFacturaId() + " no existe.");
        }

        if (producto.getCategoria() == CategoriaProducto.PRODUCTO) {
            if (producto.getStock() < detalleTO.getCantidad()) {
                throw new BadRequestException("Stock insuficiente para el producto '" + producto.getNombre()
                        + "'. Disponible: " + producto.getStock()
                        + ", Solicitado: " + detalleTO.getCantidad());
            }

            producto.setStock(producto.getStock() - detalleTO.getCantidad());

            productoRepo.actualizarParcialPorCodigoBarras(producto);
        }

        DetalleFactura detalle = DetalleFacturaMapper.toEntity(detalleTO);
        detalle.setProducto(producto);
        detalle.setFactura(factura);

        this.detalleFacturaRepo.insertar(detalle);
    }

    @Override
    public List<DetalleFacturaTO> listarDetallePorIdFactura(Integer id) {
        return DetalleFacturaMapper.toTO(this.detalleFacturaRepo.obtenerDetallePorIdFactura(id));
    }

}
