package com.candens.carros.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualizarCarroRepository extends JpaRepository<ActualizarCarroEntidad, Long> {
}
