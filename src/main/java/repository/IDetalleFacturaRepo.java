package repository;

import java.util.List;

import repository.modelo.DetalleFactura;

public interface IDetalleFacturaRepo {

    void insertar(DetalleFactura detalleFactura);

    List<DetalleFactura> obtenerDetallePorIdFactura(Integer id);
}
