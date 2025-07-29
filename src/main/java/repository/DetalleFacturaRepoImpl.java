package repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import repository.modelo.DetalleFactura;

@ApplicationScoped
@Transactional
public class DetalleFacturaRepoImpl implements IDetalleFacturaRepo {

    @Inject
    private EntityManager entityManager;

    @Override
    public void insertar(DetalleFactura detalleFactura) {
        this.entityManager.persist(detalleFactura);
    }

    @Override
    public List<DetalleFactura> obtenerDetallePorIdFactura(Integer id) {
        try {
            TypedQuery<DetalleFactura> query = entityManager.createQuery(
                    "SELECT d FROM DetalleFactura d JOIN FETCH d.factura WHERE d.factura.id = :id", DetalleFactura.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException e) {
            return List.of(); // Devuelve una lista vacía en lugar de null
        }
    }
}
