package com.example.firstSteps.servicios;

import com.example.firstSteps.Rutas;
import com.example.firstSteps.models.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

     public void saveOrder(List<Producto> productos) {

         System.out.println("Guardamos en la base de datos...");

         productos.forEach(producto -> logger.debug("el nombre del prpoducto es: {}", producto.nombre));

     }
}
