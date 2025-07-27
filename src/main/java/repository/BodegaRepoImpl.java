package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import repository.modelo.Bodega;
import java.util.List;

@Transactional
@ApplicationScoped
public class BodegaRepoImpl implements IBodegaRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bodega> seleccionarTodas() {
        try {
            return this.entityManager.createQuery("SELECT b FROM Bodega b ORDER BY b.nombre", Bodega.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al seleccionar todas las bodegas: " + e.getMessage(), e);
        }
    }

    @Override
    public Bodega seleccionarPorCodigo(String codigo) {
        try {
            return this.entityManager.createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } catch (Exception e) {
            throw new RuntimeException("Error al seleccionar la bodega por código: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertar(Bodega bodega) {
        this.entityManager.persist(bodega);
    }

    @Override
    public void actualizarParcialPorCodigo(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void eliminar(String codigo) {
        this.entityManager.remove(this.seleccionarPorCodigo(codigo));
    }

}
