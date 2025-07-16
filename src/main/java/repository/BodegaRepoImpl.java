package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import repository.modelo.Bodega;

@Transactional
@ApplicationScoped
public class BodegaRepoImpl implements IBodegaRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Bodega seleccionarPorCodigo(String codigo) {
        return this.entityManager.createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
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
