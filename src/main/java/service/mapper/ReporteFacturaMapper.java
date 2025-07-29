package service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import repository.modelo.Factura;
import service.to.FacturaReporteTO;

public class ReporteFacturaMapper {

    public static FacturaReporteTO toTO(Factura f) {
        FacturaReporteTO reporte = new FacturaReporteTO();
        reporte.setNumeroDocumento(f.getNumeroDocumento());
        reporte.setIdentificacionCliente(f.getCliente().getCedula());
        reporte.setNombreCliente(f.getCliente().getNombre() + " " + f.getCliente().getApellido());
        reporte.setCantidadItems(f.getDetalles().size());
        reporte.setTotalImpuestos(f.getTotalImpuestos());
        reporte.setTotal(f.getTotal());
        return reporte;
    }

    public static List<FacturaReporteTO> toTO(List<Factura> facturas) {
        return facturas.stream()
                .map(ReporteFacturaMapper::toTO)
                .collect(Collectors.toList());
    }
}
