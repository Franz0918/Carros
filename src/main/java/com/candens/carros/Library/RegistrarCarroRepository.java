package com.candens.carros.Library;
//as
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrarCarroRepository extends JpaRepository<RegistrarCarroEntidad,Long> {
}
