package service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.IFacturaRepo;
import service.mapper.FacturaMapper;
import service.to.FacturaTO;

@ApplicationScoped
public class FacturaServiceImpl implements IFacturaService{

    @Inject
    private IFacturaRepo facturaRepo;

    @Override
    public void crearFactura(FacturaTO facturaTO) {
        if(facturaTO != null){
            this.facturaRepo.insertar(FacturaMapper.toEntity(facturaTO));
        }
    }

    @Override
    public List<FacturaTO> listarFacturas() {
        return FacturaMapper.toTO(this.facturaRepo.obtenerFacturas());
    }

}
