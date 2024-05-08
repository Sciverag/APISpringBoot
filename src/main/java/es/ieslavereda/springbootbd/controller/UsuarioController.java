package es.ieslavereda.springbootbd.controller;

import es.ieslavereda.springbootbd.model.Usuario;
import es.ieslavereda.springbootbd.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UsuarioController extends BaseController{

    Logger LOGGER = Logger.getLogger(UsuarioController.class.getName());
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        LOGGER.log(Level.INFO,"Obteniendo todos los usuarios");
        try{
            List<Usuario> usuarios = usuarioService.findAll();
            return new ResponseEntity<>(usuarios,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        LOGGER.log(Level.INFO,"Obteniendo al usuario con id: "+id);
        try{
            Usuario user = usuarioService.getUserById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody Usuario user){
        LOGGER.log(Level.INFO,"Creando el usuario con id "+user.getIdUsuario());
        try{
            Usuario u = usuarioService.createUser(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        LOGGER.log(Level.INFO,"Eliminando el usuario "+id);
        try{
            Usuario user = usuarioService.removeUserById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (SQLException e){
            return  getResponseError(e);
        }
    }


    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody Usuario user){
        LOGGER.log(Level.INFO, "Actualizando al usuario con id: "+user.getIdUsuario());
        try{
            Usuario u = usuarioService.updateUser(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }

    @GetMapping("/users/{oficioId}")
    public ResponseEntity<?> getUserByOficio(@PathVariable Integer oficioId){
        LOGGER.log(Level.INFO, "Buscando a los usuarios con el oficio: "+oficioId);
        try{
            List<Usuario> usuarios = usuarioService.getUsersByOficio(oficioId);
            return new ResponseEntity<>(usuarios,HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }
    }
}
