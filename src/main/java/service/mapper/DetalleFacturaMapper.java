package service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import repository.modelo.DetalleFactura;
import service.to.DetalleFacturaTO;

public class DetalleFacturaMapper {

    public static DetalleFacturaTO toTO(DetalleFactura detalle) {
        if (detalle == null) {
            return null;
        }

        DetalleFacturaTO dto = new DetalleFacturaTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProducto() != null ? detalle.getProducto().getId() : null);
        dto.setCodigoBarras(detalle.getProducto() != null ? detalle.getProducto().getCodigoBarras() : null);
        dto.setNombreProducto(detalle.getProducto() != null ? detalle.getProducto().getNombre() : null);
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecio(detalle.getPrecio());
        dto.setSubtotal(detalle.getSubtotal());
        dto.setTotalImpuestos(detalle.getTotalImpuestos());

        return dto;
    }

    public static List<DetalleFacturaTO> toTO(List<DetalleFactura> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return Collections.emptyList();
        }

        return detalles.stream()
                .map(DetalleFacturaMapper::toTO)
                .collect(Collectors.toList());
    }

    public static DetalleFactura toEntity(DetalleFacturaTO detalleTO) {
        if (detalleTO == null) {
            return null;
        }

        DetalleFactura detalle = new DetalleFactura();
        detalle.setId(detalleTO.getId());
        detalle.setCantidad(detalleTO.getCantidad());
        detalle.setPrecio(detalleTO.getPrecio());
        detalle.setSubtotal(detalleTO.getSubtotal());
        detalle.setTotalImpuestos(detalleTO.getTotalImpuestos());

        return detalle;
    }

    public static List<DetalleFactura> toEntity(List<DetalleFacturaTO> detallesTO) {
        if (detallesTO == null || detallesTO.isEmpty()) {
            return Collections.emptyList();
        }

        return detallesTO.stream()
                .map(DetalleFacturaMapper::toEntity)
                .collect(Collectors.toList());
    }

}
