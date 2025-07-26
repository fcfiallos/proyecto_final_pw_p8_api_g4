package repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import repository.modelo.Cliente;

@ApplicationScoped
@Transactional
public class ClienteRepoImpl implements IClienteRepo {

    @Inject
    private EntityManager entityManager;

    @Override
    public void insertar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    @Override
    public Cliente buscarPorCedula(String cedula) {
        try {
            TypedQuery<Cliente> query = this.entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class);
            query.setParameter("cedula", cedula);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Collections.emptyList();
        }

        TypedQuery<Cliente> query = this.entityManager.createQuery(
                "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", Cliente.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> buscarTodos() {
        TypedQuery<Cliente> query = this.entityManager.createQuery(
                "SELECT c FROM Cliente c ORDER BY c.apellido, c.nombre",
                Cliente.class);
        return query.getResultList();
    }

    @Override
    public void actualizar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
         if (!this.entityManager.contains(cliente)) {
            cliente = this.entityManager.merge(cliente);

        }
        this.entityManager.remove(cliente);
    }
}
