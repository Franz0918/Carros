    package com.candens.carros.Transactions;

import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Library.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class Operaciones {
    // POST
    @Service //ssss ESTO INDICA A SPRING Q ESTO TIENE LOGICA DE NEGOCIO
    public static class RegistrarCarroTransaction {

        // DATOS CONEXIONS BADE DE DATOS PGADMIN4
        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        //AQUI EMPIEZA  A TRABAJAR BRUNO

        public CarroDto ejecutar(CarroDto dto){//ACA SE PIDE EL ARIBUTO CREADO EN EL CONTROLADOR dto TIPO CarroDto
            String sql = "INSERT INTO carros (marca, numero_ruedas, tipo_transmision) VALUES (?, ?, ?)"; //? COMODIN O CASILLERO VACIAO PARA EVTIAR ATAQUES DE SQ, IN YECTION
            Long idGenerado = null; // GENERAMOS VARIABLE VACIA PARA QUE POSTRES LE ASIGNE ID

            // aaalll Bloque try-with-resources que maneja y cierra la conexión automáticamente
            // ACA ABRIMOS CANAL DE COMUNICACION
            try (Connection conexion = DriverManager.getConnection(url, user, password); //ABRE CANAL CE COMUNICACION OCN LOS DATOS DE ACCESO
                 // SE VIAJA AL SERVIDOR Y VERIGICA Q LA SINTAXIS ESTE BIEN - PREPARACION
                 PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { //TOMA EL TEXTO SQL Y LO PREPARA EN EL SERVIDO POSTEGRES Y GENERA UNA COPIA DEL ID GENERICO

                // Pasamos los parámetros de tu DTO al Query de SQL
                ps.setString(1, dto.getMarca());
                ps.setInt(2, dto.getNumero_ruedas());
                ps.setString(3, dto.getTipo_transmision());

                // Se ejecuta la inserción en la base de datos
                ps.executeUpdate();

                // ssRecuperamos el ID autoincremental que le asignó pgAdmin
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getLong(1);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("PROBLEMITA al insertar el acrro en la base de datos: " + e.getMessage());
            }
            // Retornamos el DTO final con su ID real de base de datos
            return new CarroDto(idGenerado, dto.getMarca(), dto.getNumero_ruedas(), dto.getTipo_transmision());
        }
    }


    /*
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
    */
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
    //sssGET
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
