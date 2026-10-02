package com.candens.carros.Library;

import jakarta.persistence.*;

@Entity //LE DICE A SPRING E HIBERNATE QUE NO ES UN OBJETO COMUN SINO UNA ENTIDAD  DE DB
@Table(name="carros") //ESPECIFICA EL NOMBRE DE LA TABLA FISICA EN LA DB

public class ActualizarCarroEntidad {
    @Id // VARIABLE DE LLAVE PRIMARIA Q GARANTIZA QUE NO HABRAN DOS CON ID IGUALES
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PERMITE QUE POSTGRES Q PONGA LOS ID Y NO JAVA
    private Long id;
    private String marca;
    private int numero_ruedas;
    private String tipo_transmision;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getNumero_ruedas() {
        return numero_ruedas;
    }

    public void setNumero_ruedas(int numero_ruedas) {
        this.numero_ruedas = numero_ruedas;
    }

    public String getTipo_transmision() {
        return tipo_transmision;
    }

    public void setTipo_transmision(String tipo_transmision) {
        this.tipo_transmision = tipo_transmision;
    }
}
