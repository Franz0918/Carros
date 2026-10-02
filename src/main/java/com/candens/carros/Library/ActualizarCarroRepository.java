package com.candens.carros.Library;

import org.springframework.data.jpa.repository.JpaRepository; // PERMITE TRADUCIR
import org.springframework.stereotype.Repository;

@Repository // INDICA Q ES EL ENCARGADO DE HABLAR CON DB DE POSTGRES
public interface ActualizarCarroRepository extends JpaRepository<ActualizarCarroEntidad, Long> { // HEREDA METODOS DE JPA EN LUGAR DE ESCRIBIR CODIGO SQL, TAMBIEN TE DICE QUE CLASE VA A MANEJAR ENTRE <> Y QUE TIPO ES SU LLAVE PRIMEARIA ID, GRACIAS A ESTO EN OPERACONES TRADUCE A SQL
    //• repository.save(entidad) ➡️ Traduce a un INSERT INTO o UPDATE en SQL.
    //• repository.findById(id) ➡️ Traduce a un SELECT * FROM carros WHERE id = ?.
    //• repository.delete(entidad) ➡️ Traduce a un DELETE FROM carros WHERE id = ?.
    //• ESTA VACIA LAS LLAVES PORQ SPRING GENERA AUTOMATICAMENTE EL CODIGO SQL
}
