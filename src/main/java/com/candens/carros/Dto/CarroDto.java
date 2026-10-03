package com.candens.carros.Dto;

public class CarroDto {
    private Long id; // creA UN OBJETO DE TIPO NUMERICO Q TIUENE LA CAPCIDAD DE GUARDAR UN VALOR NUL, en proGRAMACION SE USA POR BUENAS PRACTICAS USAR LONG PARA ESCALABILIDAD DEBIDO A QUE USA 64BITS
    private String marca;
    private int numero_ruedas;
    private String tipo_transmision;

    //CONSTRUCTOR
    public CarroDto(){} //CONSTRUCTOR VACIO PARA QUE LO USE SPRING O HIBERNATE COMO OBJETO CASCARON PARA PODER RELLENAR CON LOS DATOS QUE INGRESEN DE BRINO
    public CarroDto(Long id, String marca, int numero_ruedas, String tipo_transmision) { //PARA FABRICAR UN CARRODTO NECESITAS PADARME ID, MARCA,ETC
        this.id = id;
        this.marca = marca;
        this.numero_ruedas = numero_ruedas;
        this.tipo_transmision = tipo_transmision;
    }

    //GETTERS Y STTERES
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
