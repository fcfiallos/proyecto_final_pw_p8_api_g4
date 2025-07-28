package service.mapper;

import repository.modelo.Factura;
import service.to.FacturaResumenTO;

public class FacturaResumenMapper {
    public static FacturaResumenTO toTO(Factura factura) {
        if (factura == null) {
            return null;
        }

        FacturaResumenTO to = new FacturaResumenTO();

        to.setNumeroDocumento(factura.getNumeroDocumento());
        to.setFechaEmision(factura.getFechaEmision());
        to.setTotal(factura.getTotal());

        int cantidad = (factura.getDetalles() != null) ? factura.getDetalles().size() : 0;
        to.setCantidadItems(cantidad);

        return to;
    }
}
