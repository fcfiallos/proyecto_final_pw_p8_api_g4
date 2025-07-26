package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import repository.modelo.Producto;

@Transactional
@ApplicationScoped
public class ProductoRepoImpl implements IProductoRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Producto seleccionarPorCodigoBarras(String codigoBarras) {
        return this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class)
                .setParameter("codigoBarras", codigoBarras)
                .getSingleResult();
    }

    @Override
    public void insertar(Producto producto) {
        this.entityManager.persist(producto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(Producto producto) {
        this.entityManager.merge(producto);
    }

    @Override
    public void eliminar(String codigoBarras) {
        this.entityManager.remove(this.seleccionarPorCodigoBarras(codigoBarras));
    }
}
