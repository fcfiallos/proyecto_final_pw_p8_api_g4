package service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import repository.modelo.Factura;
import service.to.FacturaTO;

public class FacturaMapper {

    public static FacturaTO toTO(Factura factura) {
        if (factura == null) {
            return null;
        }

        FacturaTO dto = new FacturaTO();
        dto.setId(factura.getId());
        dto.setRucEmpresa(factura.getRucEmpresa());
        dto.setNumeroDocumento(factura.getNumeroDocumento());
        dto.setEstablecimiento(factura.getEstablecimiento());
        dto.setPuntoEmision(factura.getPuntoEmision());
        dto.setFechaEmision(factura.getFechaEmision());
        dto.setCliente(factura.getCliente() != null ? ClienteMapper.toTO(factura.getCliente()) : null);
        dto.setDetalles(factura.getDetalles() != null
                ? DetalleFacturaMapper.toTO(factura.getDetalles())
                : Collections.emptyList());
        dto.setSubtotal(factura.getSubtotal());
        dto.setTotalImpuestos(factura.getTotalImpuestos());
        dto.setTotal(factura.getTotal());

        return dto;
    }

    public static Factura toEntity(FacturaTO facturaTO) {
        if (facturaTO == null) {
            return null;
        }

        Factura factura = new Factura();
        factura.setId(facturaTO.getId());
        factura.setRucEmpresa(facturaTO.getRucEmpresa());
        factura.setNumeroDocumento(facturaTO.getNumeroDocumento());
        factura.setEstablecimiento(facturaTO.getEstablecimiento());
        factura.setPuntoEmision(facturaTO.getPuntoEmision());
        factura.setFechaEmision(facturaTO.getFechaEmision());
        factura.setSubtotal(facturaTO.getSubtotal());
        factura.setTotalImpuestos(facturaTO.getTotalImpuestos());
        factura.setTotal(facturaTO.getTotal());

        return factura;
    }

    public static List<FacturaTO> toTO(List<Factura> facturas) {
        if (facturas == null || facturas.isEmpty()) {
            return Collections.emptyList();
        }
        return facturas.stream()
                .map(FacturaMapper::toTO)
                .collect(Collectors.toList());
    }

    public static List<Factura> toEntity(List<FacturaTO> facturasTo) {
        if (facturasTo == null || facturasTo.isEmpty()) {
            return Collections.emptyList();
        }
        return facturasTo.stream()
                .map(FacturaMapper::toEntity)
                .collect(Collectors.toList());
    }

}
