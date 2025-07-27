package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IImpuestoRepo;
import repository.modelo.Impuesto;
import service.mapper.ImpuestoMapper;
import service.to.ImpuestoTO;

import java.util.List;

@ApplicationScoped
public class ImpuestoServiceImpl implements IImpuestoService {
    
    @Inject
    private IImpuestoRepo impuestoRepo;

    @Override
    public List<Impuesto> obtenerTodos() {
        return this.impuestoRepo.seleccionarTodos();
    }

    @Override
    public Impuesto buscarPorId(Integer id) {
        return this.impuestoRepo.seleccionarPorId(id);
    }

    @Override
    public Impuesto buscarPorNombre(String nombre) {
        return this.impuestoRepo.seleccionarPorNombre(nombre);
    }

    @Override
    public void guardar(ImpuestoTO impuestoTO) {
        Impuesto nuevoImpuesto = ImpuestoMapper.toEntity(impuestoTO);
        this.impuestoRepo.insertar(nuevoImpuesto);
    }

    @Override
    public void actualizar(Integer id, ImpuestoTO impuestoTO) {
        Impuesto impuestoExistente = this.impuestoRepo.seleccionarPorId(id);
        if (impuestoExistente != null) {
            ImpuestoMapper.actualizarTO(impuestoExistente, impuestoTO);
            this.impuestoRepo.actualizar(impuestoExistente);
        }
    }

    @Override
    public void eliminar(Integer id) {
        this.impuestoRepo.eliminar(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.impuestoRepo.existePorNombre(nombre);
    }
}
