    package com.candens.carros.Transactions;

import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Library.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class Operaciones {
    // POST
    @Service // ESTO INDICA A SPRING Q ESTO TIENE LOGICA DE NEGOCIO
    public static class RegistrarCarroTransaction {
        private final RegistrarCarroRepository repository;//CREO LA VARIABLE repository QUE ES DE TIPO RegistrarCarroRepository, INMUTABle y privada

        public RegistrarCarroTransaction(RegistrarCarroRepository repository) { //ACA SE CREA EL CONECTOR/VARIABLE TEMPORAL repository

            this.repository = repository;         // de 14 a 18 es INYECCION DE DEPENDENCIAS POR CONSTRUCTOR
        }

        //AQUI EMPIEZA  A TRABAJAR BRUNO
        @Transactional //pOR SEGURIDAD LO TRATA COMO UNA SOLA UNIDAD DE TRABAJO, LE DICE AL DB SI ALGO LEGA A FALLAR ACA DENTRO, HAZ UN ROLLBACK PARA QUE LA DB NO QUEDE CORRUPTA
        public CarroDto ejecutar(CarroDto dto){//ACA SE PIDE EL ARIBUTO CREADO EN EL CONTROLADOR dto TIPO CarroDto
            RegistrarCarroEntidad entidad=new RegistrarCarroEntidad();
            entidad.setMarca(dto.getMarca());
            entidad.setNumero_ruedas(dto.getNumero_ruedas());
            entidad.setTipo_transmision(dto.getTipo_transmision());

                RegistrarCarroEntidad saved=repository.save(entidad); // ACA LO GUARMADMOS EN POSTGRES y luego lo devolvemos en saved(SE GUARDA(.save) TODO LO DE entidad EN repository
            return new CarroDto(saved.getId(),saved.getMarca(),saved.getNumero_ruedas(),saved.getTipo_transmision()); //LO DEVUELVE AL CONTROLADOR

        }
    }

    /*//GET
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
    }*/
    //GET
    @Service
    public static class BuscarCarroTransaction {
        private final BuscarCarroRepository repository;

        public BuscarCarroTransaction(BuscarCarroRepository repository) {

            this.repository = repository;
        }

        @Transactional(readOnly = true)
        public List<CarroDto> ejecutar() {
            List<BuscarCarroEntidad> entidadLista = repository.findAll(); //EJECUTA COMANDO SQL

            return entidadLista.stream()// REGRESA LAS ENTIDADES EN UNA LISTRA ORDENADA  A MANERA DE FAJA TRANSPORTADORA
                    .map(entidad -> new CarroDto(   //TRANSORMADOR DINAMICO , LLAMA AL CONSTRUCTOR LLENO DE CARRODTO Y ´POR CADA ENYIFDAD EXTRAE SUS DATOS
                            entidad.getId(),
                            entidad.getMarca(),
                            entidad.getNumero_ruedas(),
                            entidad.getTipo_transmision()
                    ))
                    .collect(Collectors.toList()); // RECOGE LOS NUEVOS Y LOS EMPQQUETA EN UNA NUELA LISTA
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
            repository.delete(entidad); //BOORA EN SQL
        }
    }

}
