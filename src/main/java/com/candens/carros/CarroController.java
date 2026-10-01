package com.candens.carros;


import com.candens.carros.Dto.CarroDto;
import com.candens.carros.Transactions.Operaciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")

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
    @PostMapping("/registrar")
    public ResponseEntity<CarroDto> postCarro(@RequestBody CarroDto dto) {
        return ResponseEntity.ok(registrarTransaction.ejecutar(dto));
    }

    /*// GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CarroDto> getCarro(@PathVariable Long id) {
        return ResponseEntity.ok(buscarTransaction.ejecutar(id));
    }*/

    // GET
    @GetMapping("/buscar")
    public ResponseEntity<List<CarroDto>>getCarro() {
        return ResponseEntity.ok(buscarTransaction.ejecutar());
    }

    // PUT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CarroDto> putCarro(@PathVariable Long id, @RequestBody CarroDto dto) {
        return ResponseEntity.ok(actualizarTransaction.ejecutar(id, dto));
    }

    // BORARA
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        borrarTransacion.ejecutar(id);
        return ResponseEntity.noContent().build();
    }
}
