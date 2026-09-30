// POST 🐻n
//     (())
package com.candens.carros.Library;

import jakarta.persistence.*;

@Entity
@Table(name="carros")

public class RegistrarCarroEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marca;
    private int numero_ruedas;
    private String tipo_transmision;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
