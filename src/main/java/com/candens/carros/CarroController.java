package com.candens.carros;


import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Transactions.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // INDICA Q ES UN CONTROLADOR
@RequestMapping("/api/carros") //LA DIRECCION DEL URI

public class CarroController {

    private final Transaction.RegistrarCarroEnvio registrarEnvio;
    private final Transaction.BuscarCarroEnvio buscarEnvio;

    private final Transaction.ListaCarroEnvio listaEnvio;
    private final Transaction.ActualizarCarroEnvio actualizaEnvio;
    private final Transaction.BorrarCarroEnvio borraEnvio;

    public CarroController(Transaction.RegistrarCarroEnvio registrarEnvio, Transaction.BuscarCarroEnvio buscarEnvio, Transaction.ListaCarroEnvio listaEnvio, Transaction.ActualizarCarroEnvio actualizaEnvio, Transaction.BorrarCarroEnvio borraEnvio) {
        this.registrarEnvio = registrarEnvio;
        this.buscarEnvio = buscarEnvio;
        this.listaEnvio = listaEnvio;
        this.actualizaEnvio = actualizaEnvio;
        this.borraEnvio = borraEnvio;
    }

    //POST
    @PostMapping("/registrar") //ECUCHA A INTERNET A LA DIRECCION ...registrar, si ESCUCHAS ALGO HAZ
    public ResponseEntity<CarroDto> postCarro(@RequestBody CarroDto dto) {//REQUEST TRADUCE JSON A LENGUAJE JAVA Y GUARDA LOS DATOS ENVIADOS EN BRUNO A LA NUEVA VARIABLE dto DE TIPO CarroDto y lo envia A OPERACIONES AL METODO ejecutar/RESPONSE ENTITY CONTENEDOR RPTAS WEB CON OBJETO TIPO CARRODTO
        return ResponseEntity.ok(registrarEnvio.ejecutarPost(dto)); // LO DEVUELVE A BRUNO CON UN OK 200 PERO ANTES POR MEDIO DE registrarTransaction ENVIA LOS DATOS ESCUCHADOS POR BRUNO A OPERACIONES Y LUEGO LO RECIBE DE OPERACIONES
    }

    // GET ENDPOINT
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CarroDto>getCarro(@PathVariable Long id) {
        return ResponseEntity.ok(buscarEnvio.ejecutarGet(id));
    }

    // GET LIST
    @GetMapping("/listar")
    public ResponseEntity<List<CarroDto>>listCarro() {
        return ResponseEntity.ok(listaEnvio.ejecutarList());
    }

    // PUT  ENDPOINT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CarroDto> putCarro(@PathVariable Long id, @RequestBody CarroDto dto) {
        return ResponseEntity.ok(actualizaEnvio.ejecutarPut(id, dto));
    }

    // BORARA
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) { //CUERPO DE PAQUETE EN RPTA SERA VACIO
        borraEnvio.ejecutarDelete(id);
        return ResponseEntity.noContent().build(); //NOCONTENT PORQ NO ENTREGA CONTENIDO , BUILD PARA FABRICAR EL PAQUETE POSTAL WEB DVACIO DE FORMA CORRECTA
    }
}
