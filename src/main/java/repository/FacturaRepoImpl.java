package repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import repository.modelo.Factura;

@ApplicationScoped
@Transactional
public class FacturaRepoImpl implements IFacturaRepo {

    @Inject
    private EntityManager entityManager;

    @Override
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);
    }

    @Override
    public List<Factura> obtenerFacturas() {
        TypedQuery<Factura> query = this.entityManager.createQuery(
                "SELECT f FROM Factura f LEFT JOIN FETCH f.detalles d LEFT JOIN FETCH d.producto",
                Factura.class);
        return query.getResultList();
    }

    @Override
    public List<Factura> obtenerFacturasPorCedulaCliente(String cedula) {
        TypedQuery<Factura> query = this.entityManager.createQuery(
                "SELECT f FROM Factura f " +
                        "LEFT JOIN FETCH f.detalles d " +
                        "LEFT JOIN FETCH d.producto " +
                        "WHERE f.cliente.cedula = :cedula",
                Factura.class);
        query.setParameter("cedula", cedula);
        return query.getResultList();
    }

    @Override
    public Factura obtenerFacturaPorID(Integer id) {
        TypedQuery<Factura> query = this.entityManager.createQuery(
                "SELECT f FROM Factura f LEFT JOIN FETCH f.detalles d LEFT JOIN FETCH d.producto WHERE f.id = :id",
                Factura.class);
        query.setParameter("id", id);
        List<Factura> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    


}
