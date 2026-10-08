package com.candens.carros.Transactions;

import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Library.Operaciones;
import org.springframework.stereotype.Service;

import java.util.List;

public class Transaction {

    //POST
    @Service("RegistrarCarroEnvio")
    public static class RegistrarCarroEnvio {
        private final Operaciones.RegistrarCarroTransaction registrarTransaction = new Operaciones.RegistrarCarroTransaction();
        public CarroDto ejecutarPost(CarroDto dto) {
            CarroDto enviaPost = registrarTransaction.ejecutar(dto);
            return enviaPost;

        }
    }

    //GET
    @Service("BuscarCarroEnvio")
    public static class BuscarCarroEnvio {
        private final Operaciones.BuscarCarroTransaction buscarTransaction = new Operaciones.BuscarCarroTransaction();
        public CarroDto ejecutarGet(Long id) {
            CarroDto enviaGet = buscarTransaction.ejecutar(id);
            return enviaGet;

        }
    }


    //LIST
    @Service("ListaCarroEnvio")
    public static class ListaCarroEnvio {
        private final Operaciones.ListaCarrosTransaction listarTransaction = new Operaciones.ListaCarrosTransaction();
        public List<CarroDto> ejecutarList() {
            List<CarroDto> enviaList = listarTransaction.ejecutar();
            return enviaList;

        }

    }

    //PUT
    @Service("ActualizarCarroEnvio")
    public static class ActualizarCarroEnvio {
        private final Operaciones.ActualizarCarroTransaction actualizarTransaction = new Operaciones.ActualizarCarroTransaction();
        public CarroDto ejecutarPut(Long id, CarroDto dto) {
            CarroDto enviaPut = actualizarTransaction.ejecutar(id, dto);
            return enviaPut;

        }
    }

    //BORRAR
    @Service("BorrarCarroEnvio")
    public static class BorrarCarroEnvio {
        private final Operaciones.BorrarCarroTransaction borrarTransaction = new Operaciones.BorrarCarroTransaction();
        public void ejecutarDelete(Long id) {
            borrarTransaction.ejecutar(id);

        }
    }
}