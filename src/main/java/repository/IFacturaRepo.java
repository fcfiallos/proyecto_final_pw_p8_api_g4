package repository;

import java.util.List;

import repository.modelo.Factura;

public interface IFacturaRepo {

    void insertar(Factura factura);

    List<Factura> obtenerFacturas();
}
