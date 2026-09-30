package com.candens.carros.Transactions;

import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Library.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Operaciones {
    // POST
    @Service
    public static class RegistrarCarroTransaction {
        private final RegistrarCarroRepository repository;

        public RegistrarCarroTransaction(RegistrarCarroRepository repository) {
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

        public BuscarCarroTransaction(BuscarCarroRepository repository) {
            this.repository = repository;
        }

        @Transactional(readOnly = true)
        public CarroDto ejecutar(Long id) {
            BuscarCarroEntidad entidad = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("carro no encontrado con ID" + id));
            return new CarroDto(entidad.getId(), entidad.getMarca(), entidad.getNumero_ruedas(), entidad.getTipo_transmision());
        }
    }
    // PUT
    @Service
    public static class ActualizarCarroTransaction {
        private final ActualizarCarroRepository repository;

        public ActualizarCarroTransaction(ActualizarCarroRepository repository) {
            this.repository = repository;
        }

        @Transactional
        public CarroDto ejecutar(Long id, CarroDto dto){
            ActualizarCarroEntidad entidad=repository.findById(id)
                    .orElseThrow(()-> new RuntimeException("no se puede actualizar carro ,ID no encontrado"+id));
            entidad.setMarca(dto.getMarca());
            entidad.setNumero_ruedas(dto.getNumero_ruedas());
            entidad.setTipo_transmision(dto.getTipo_transmision());

            ActualizarCarroEntidad actualizado = repository.save(entidad);
            return new CarroDto(actualizado.getId(),actualizado.getMarca(),actualizado.getNumero_ruedas(),actualizado.getTipo_transmision());

        }

    }

    //BORRAR
    @Service

    public static class BorrarCarroTransaction {
        private final BorrarCarroRepository repository;

        public BorrarCarroTransaction(BorrarCarroRepository repository) {
            this.repository = repository;
        }

        @Transactional
        public void ejecutar(Long id) {
            BorrarCarroEntidad entidad = repository.findById(id)
                    .orElseThrow(()->new RuntimeException("no se puede eliminiar, id no cencontrado"+id));
            repository.delete(entidad);
        }
    }

}
