package com.candens.carros.Transactions;

import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Library.RegistrarCarroEntidad;
import com.candens.carros.Library.RegistrarCarroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Operaciones {
    // POST
    @Service
    public static class RegistrarCarrosTransaction {
        private final RegistrarCarroRepository repository;

        public RegistrarCarrosTransaction(RegistrarCarroRepository repository) {
            this.repository = repository;
        }

        @Transactional
        public CarroDto ejecutar(CarroDto dto){
            RegistrarCarroEntidad entidad=new RegistrarCarroEntidad();
            entidad.setMarca(dto.getMarca());
            entidad.setNumero_ruedas(dto.getNumero_ruedas());
            entidad.setTipo_transmision(dto.getTipo_transmision());
            RegistrarCarroEntidad saved=repository.save(entidad);
            return new CarroDto(saved.getId(),saved.getMarca(),saved.getNumero_ruedas(),saved.getTipo_transmision());

        }
    }

    //GET
    @Service
    public static class BuscarCarroTransaction {
        private final BuscarCarroRepository repository;

    }
}
