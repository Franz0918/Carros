    package com.candens.carros.Library;

import com.candens.carros.Dto.CarroDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class Operaciones {
    // POST
    @Service // ESTO INDICA A SPRING Q ESTO TIENE LOGICA DE NEGOCIO
    public static class RegistrarCarroTransaction {

        // DATOS CONEXIONS BADE DE DATOS PGADMIN4

        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        //AQUI EMPIEZA  A TRABAJAR BRUNO ESPERANDO

        public CarroDto ejecutar(CarroDto dto){//ACA SE PIDE EL ARIBUTO CREADO EN EL CONTROLADOR dto TIPO CarroDto
            String sql = "INSERT INTO carros (marca, numero_ruedas, tipo_transmision) VALUES (?, ?, ?)";
            Long idGenerado = null;

            // aaalll Bloque try-with-resources que maneja y cierra la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
                throw new RuntimeException("Error al insertar el carro en la base de datos: " + e.getMessage());
            }
            // Retornamos el DTO final con su ID real de base de datos
            return new CarroDto(idGenerado, dto.getMarca(), dto.getNumero_ruedas(), dto.getTipo_transmision());
        }
    }



    //GET
    @Service
    public static class BuscarCarroTransaction {

        // DATOS CONEXIONS BADE DE DATOS PGADMIN4

        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        //AQUI EMPIEZA  A TRABAJAR BRUNO

        public CarroDto ejecutar(Long id){//ACA SE PIDE EL ARIBUTO CREADO EN EL CONTROLADOR dto TIPO CarroDto
            String sql = "SELECT id, marca, numero_ruedas, tipo_transmision FROM carros WHERE id = ?";
            CarroDto carroEncontrado = null;

            // aaalll Bloque try-with-resources que maneja y cierra la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conexion.prepareStatement(sql)) {

                ps.setLong(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    // Avanzamos el cursor a la primera fila si es que existe
                    if (rs.next()) { //CUANDO LLEGAR A LA DB EL PUNTERO ESTA POSICIONADO EN UNA LINEA VACIA , POSICION CERO, MUEVE EL LECTOR  ALA PARIMERA LINEA DE DATOS
                        carroEncontrado = new CarroDto(
                                rs.getLong("id"),
                                rs.getString("marca"),
                                rs.getInt("numero_ruedas"),
                                rs.getString("tipo_transmision")
                        );
                    } else {
                        throw new RuntimeException("No existe ningún carro en pgAdmin con el ID: " + id);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("Error en la consulta de búsqueda: " + e.getMessage());
            }

            // 5. Devolvemos el DTO relleno con los datos reales
            return carroEncontrado;
        }
    }

    // GET LIST


    @Service
    public static class ListaCarrosTransaction {

        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        public List<CarroDto> ejecutar() {
            // Sentencia SQL nativa para traer todas las filas ordenadas por ID
            String sql = "SELECT id, marca, numero_ruedas, tipo_transmision FROM carros ORDER BY id ASC";
            List<CarroDto> listaCarros = new java.util.ArrayList<>();

            // aaalll Bloque try-with-resources que maneja y cierra la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()){

                CarroDto carro = new CarroDto(
                        rs.getLong("id"),
                        rs.getString("marca"),
                        rs.getInt("numero_ruedas"),
                        rs.getString("tipo_transmision")
                );
                // Añadimos cada carro procesado a nuestra lista dinámica de Java
                listaCarros.add(carro);
            }

                } catch (Exception e) {
                    throw new RuntimeException("Error al listar los carros de la base de datos: " + e.getMessage());
                }

                // Devolvemos la lista completa empaquetada
                return listaCarros;
            }
        }

    // PUT
    @Service
    public static class ActualizarCarroTransaction {

        // DATOS CONEXIONS BADE DE DATOS PGADMIN4

        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        public CarroDto ejecutar(Long id, CarroDto dto){//ACA SE PIDE EL ARIBUTO CREADO EN EL CONTROLADOR dto TIPO CarroDto
            String sql = "UPDATE carros SET marca = ?, numero_ruedas = ?, tipo_transmision = ? WHERE id = ?";

            // aaalll Bloque try-with-resources que maneja y cierra la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conexion.prepareStatement(sql)) {

                // Pasamos los parámetros del DTO al Query de SQL
                ps.setString(1, dto.getMarca());
                ps.setInt(2, dto.getNumero_ruedas());
                ps.setString(3, dto.getTipo_transmision());
                ps.setLong(4, id);

                // Se ejecuta la inserción en la base de datos
                int filasModificadas = ps.executeUpdate();

                if (filasModificadas==0) {
                    throw new RuntimeException("No se pudo actualizar. El carrito con ID " + id + " no existe.");
                }

            } catch (Exception e) {
                throw new RuntimeException("PROBLEMITA AL INSERTAR TU CARRITO: " + e.getMessage());
            }
            // Retornamos el DTO final con su ID real de base de datos
            return new CarroDto(id, dto.getMarca(), dto.getNumero_ruedas(), dto.getTipo_transmision());

        }

    }


    //BORRAR
    @Service

    public static class BorrarCarroTransaction {

        // Datos directos de conexión a tu base de datos de pgAdmin 4
        private final String url = "jdbc:postgresql://localhost:5432/carritos";
        private final String user = "postgres";
        private final String password = "Los;petetes;2026";

        public void ejecutar(Long id) {
            String sql = "DELETE FROM carros WHERE id = ?";

            // 2. Bloque try-with-resources para manejar y cerrar la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conexion.prepareStatement(sql)) {

                ps.setLong(1, id);

                int filasDeleted = ps.executeUpdate();

                if (filasDeleted == 0) {
                    throw new RuntimeException("No se pudo actualizar. El carro con ID " + id + " no existe.");
                }

            } catch (Exception e) {
                throw new RuntimeException("PROBLEMITA ELIMINIAR: " + e.getMessage());
            }


            }
    }

}
