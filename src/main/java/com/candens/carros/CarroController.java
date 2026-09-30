package com.candens.carros;

import com.candens.carros.Transactions.Operaciones;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carros")

public class CarroController {

    private final Operaciones.RegistrarCarroTransaction registrarTransaction;
    private final Operaciones.BuscarCarroTransaction buscarTransaction;
    private final Operaciones.

}
