package com.example.firstSteps;


import com.example.firstSteps.models.Libro;
import com.example.firstSteps.models.Producto;
import com.example.firstSteps.models.UserData;
import com.example.firstSteps.servicios.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class Rutas {

    private OrderService orderService;

    private Rutas(OrderService orderService) {
        this.orderService = orderService;
    }


    private final Logger logger = LoggerFactory.getLogger(Rutas.class);



    /* Get */
    @GetMapping("/hola")
    String miPrimeraRuta() {
        return "Hola mundo desde Spring Controller ";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Leyendo el libro id: " + id + ", editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}/editorial/{editorial}")
    String leerLibro2(@PathVariable int id, @RequestParam String params) {
        return "Leyendo el libro id: " + id + "params: " + params;
    }


    /* Post */
    @PostMapping("/libro")
        String guardarLibro(@RequestBody Libro libro) {

            logger.debug("libro {} editorial {}", libro.nombre, libro.editorial);


            return "Libro guardado";
        }

    @GetMapping("/saludar")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "La ruta fue movida permanentemente")
    String miSegundaRutaConStatus() {
        return "Aprendiendo statuses http en Spring Boot";
        }


    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimales(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("caballo, vaca, oveja, gallina");
        } else if (lugar.equals("selva")) {
            return ResponseEntity.status(HttpStatus.OK).body("mono, gorila, puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("lugar no valido");

        }
    }

    @GetMapping("/calcular/{numero}")
    public int getCalculo(@PathVariable int numero) {
        throw new NullPointerException("La clave es 1234");
    }

    @GetMapping("/userData")
    public Map<String, Map<String, Object>> getUserData() {
        return Map.of("user",Map.of("name", "mary", "age", 25));
    }

    @GetMapping("/userDataV2")
    public UserData getUserDataV2() {
        return new UserData("Pablo", 27, "Guacolda #20");
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody List<Producto> productos) {
        orderService.saveOrder(productos);

        return "Producto guardado";
    }
}




