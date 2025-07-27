package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import repository.modelo.Impuesto;

import java.util.List;

@Transactional
@ApplicationScoped
public class ImpuestoRepoImpl implements IImpuestoRepo {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Impuesto> seleccionarTodos() {
        return this.entityManager.createQuery("SELECT i FROM Impuesto i ORDER BY i.nombre", Impuesto.class)
                .getResultList();
    }

    @Override
    public Impuesto seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Impuesto.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Impuesto seleccionarPorNombre(String nombre) {
        try {
            return this.entityManager.createQuery("SELECT i FROM Impuesto i WHERE i.nombre = :nombre", Impuesto.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insertar(Impuesto impuesto) {
        this.entityManager.persist(impuesto);
    }

    @Override
    public void actualizar(Impuesto impuesto) {
        this.entityManager.merge(impuesto);
    }

    @Override
    public void eliminar(Integer id) {
        Impuesto impuesto = this.seleccionarPorId(id);
        if (impuesto != null) {
            this.entityManager.remove(impuesto);
        }
    }

    @Override
    public boolean existePorNombre(String nombre) {
        Long count = this.entityManager.createQuery("SELECT COUNT(i) FROM Impuesto i WHERE i.nombre = :nombre", Long.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
        return count > 0;
    }
}
