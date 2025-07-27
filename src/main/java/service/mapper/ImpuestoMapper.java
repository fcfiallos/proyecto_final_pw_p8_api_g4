package service.mapper;

import repository.modelo.Impuesto;
import service.to.ImpuestoTO;

import java.util.List;
import java.util.stream.Collectors;

public class ImpuestoMapper {
    
    public static ImpuestoTO toTO(Impuesto impuesto) {
        if (impuesto == null) {
            return null;
        }
        
        ImpuestoTO impuestoTO = new ImpuestoTO();
        impuestoTO.setId(impuesto.getId());
        impuestoTO.setNombre(impuesto.getNombre());
        impuestoTO.setValor(impuesto.getValor());
        impuestoTO.setDescripcion(impuesto.getDescripcion());
        
        return impuestoTO;
    }
    
    public static Impuesto toEntity(ImpuestoTO impuestoTO) {
        if (impuestoTO == null) {
            return null;
        }
        
        Impuesto impuesto = new Impuesto();
        impuesto.setId(impuestoTO.getId());
        impuesto.setNombre(impuestoTO.getNombre());
        impuesto.setValor(impuestoTO.getValor());
        impuesto.setDescripcion(impuestoTO.getDescripcion());
        
        return impuesto;
    }
    
    public static List<ImpuestoTO> toTOList(List<Impuesto> impuestos) {
        if (impuestos == null) {
            return null;
        }
        
        return impuestos.stream()
                .map(ImpuestoMapper::toTO)
                .collect(Collectors.toList());
    }
    
    public static List<Impuesto> toEntityList(List<ImpuestoTO> impuestosTO) {
        if (impuestosTO == null) {
            return null;
        }
        
        return impuestosTO.stream()
                .map(ImpuestoMapper::toEntity)
                .collect(Collectors.toList());
    }
    
    public static void actualizarTO(Impuesto impuesto, ImpuestoTO impuestoTO) {
        if (impuestoTO.getNombre() != null) {
            impuesto.setNombre(impuestoTO.getNombre());
        }
        if (impuestoTO.getValor() != null) {
            impuesto.setValor(impuestoTO.getValor());
        }
        if (impuestoTO.getDescripcion() != null) {
            impuesto.setDescripcion(impuestoTO.getDescripcion());
        }
    }
}
