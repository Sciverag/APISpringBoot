package es.ieslavereda.springbootbd.controller;

import es.ieslavereda.springbootbd.model.Oficio;
import es.ieslavereda.springbootbd.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class OficioController extends BaseController{

    private final Logger LOGGER = Logger.getLogger(OficioController.class.getName());

    @Autowired
    private OficioService oficioService;

    @GetMapping("/oficios")
    public ResponseEntity<?> getOficios(){
        LOGGER.log(Level.INFO,"Obteniendo todos los oficios");
        try{
            List<Oficio> oficios = oficioService.getAllOficios();
            return new ResponseEntity<>(oficios, HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @GetMapping("/oficio/{id}")
    public ResponseEntity<?> getOficio(@PathVariable Integer id){
        LOGGER.log(Level.INFO,"Obteniendo al oficio con id: "+id);
        try{
            Oficio oficio = oficioService.getOficioById(id);
            return new ResponseEntity<>(oficio,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @PostMapping("/oficio")
    public ResponseEntity<?> createOficio(@RequestBody Oficio oficio){
        LOGGER.log(Level.INFO,"Creando el oficio con id "+oficio.getIdOficio());
        try{
            Oficio o = oficioService.createOficio(oficio);
            return new ResponseEntity<>(o,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @DeleteMapping("/oficio/{id}")
    public ResponseEntity<?> deleteOficio(@PathVariable Integer id){
        LOGGER.log(Level.INFO,"Eliminando el oficio "+id);
        try{
            Oficio oficio = oficioService.removeOficioById(id);
            return new ResponseEntity<>(oficio,HttpStatus.OK);
        }catch (SQLException e){
            return  getResponseError(e);
        }
    }


    @PutMapping("/oficio")
    public ResponseEntity<?> updateOficio(@RequestBody Oficio oficio){
        LOGGER.log(Level.INFO, "Actualizando al usuario con id: "+oficio.getIdOficio());
        try{
            Oficio o = oficioService.updateOficio(oficio);
            return new ResponseEntity<>(o,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }
}
