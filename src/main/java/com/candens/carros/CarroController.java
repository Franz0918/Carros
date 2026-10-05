package com.candens.carros;


import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Transactions.Operaciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // INDICA Q ES UN CONTROLADOR
@RequestMapping("/api/carros") //LA DIRECCION DEL URI

public class CarroController {

    private final Operaciones.RegistrarCarroTransaction registrarTransaction;
    private final Operaciones.BuscarCarroTransaction buscarTransaction;
    private final Operaciones.ActualizarCarroTransaction actualizarTransaction;
    private final Operaciones.BorrarCarroTransaction borrarTransacion;

    public CarroController(Operaciones.RegistrarCarroTransaction registrarTransaction, Operaciones.BuscarCarroTransaction buscarTransaction, Operaciones.ActualizarCarroTransaction actualizarTransaction, Operaciones.BorrarCarroTransaction borrarTransacion) {
        this.registrarTransaction = registrarTransaction;
        this.buscarTransaction = buscarTransaction;
        this.actualizarTransaction = actualizarTransaction;
        this.borrarTransacion = borrarTransacion;
    }

    //POST
    @PostMapping("/registrar") //ECUCHA A INTERNET A LA DIRECCION ...registrar, si ESCUCHAS ALGO HAZ
    public ResponseEntity<CarroDto> postCarro(@RequestBody CarroDto dto) {//REQUEST TRADUCE JSON A LENGUAJE JAVA Y GUARDA LOS DATOS ENVIADOS EN BRUNO A LA NUEVA VARIABLE dto DE TIPO CarroDto y lo envia A OPERACIONES AL METODO ejecutar/RESPONSE ENTITY CONTENEDOR RPTAS WEB CON OBJETO TIPO CARRODTO
        return ResponseEntity.ok(registrarTransaction.ejecutar(dto)); // LO DEVUELVE A BRUNO CON UN OK 200 PERO ANTES POR MEDIO DE registrarTransaction ENVIA LOS DATOS ESCUCHADOS POR BRUNO A OPERACIONES Y LUEGO LO RECIBE DE OPERACIONES
    }

    /*// GET
    @GetMapping("/buscar/{id}")
    PERMISO         LO QUE                      NOMBRE      LO QUE NECESITA
    ENTRADA         DEVUELVE
    public          ResponseEntity<CarroDto>    getCarro    (@PathVariable Long id) {
        return ResponseEntity.ok(buscarTransaction.ejecutar(id));
    }*/

    // GET ENDPOINT
    @GetMapping("/buscar")
    public ResponseEntity<List<CarroDto>>getCarro() {
        return ResponseEntity.ok(buscarTransaction.ejecutar());
    }

    // PUT  ENDPOINT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CarroDto> putCarro(@PathVariable Long id, @RequestBody CarroDto dto) {
        return ResponseEntity.ok(actualizarTransaction.ejecutar(id, dto));
    }

    // BORARA
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) { //CUERPO DE PAQUETE EN RPTA SERA VACIO
        borrarTransacion.ejecutar(id);
        return ResponseEntity.noContent().build(); //NOCONTENT PORQ NO ENTREGA CONTENIDO , BUILD PARA FABRICAR EL PAQUETE POSTAL WEB DVACIO DE FORMA CORRECTA
    }
}
