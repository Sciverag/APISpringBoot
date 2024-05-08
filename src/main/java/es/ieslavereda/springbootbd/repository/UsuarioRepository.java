package es.ieslavereda.springbootbd.repository;

import es.ieslavereda.springbootbd.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource datasource;


    public List<Usuario> getAll() throws SQLException {

        String sql = "select * from Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){

            while (rs.next()) {
                usuarios.add(Usuario.builder().idUsuario(rs.getInt("idUsuario"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .Oficio_idOficio(rs.getInt("Oficio_idOficio"))
                        .build());
            }

            if(usuarios.isEmpty()){
                throw new SQLException("No existen usuarios");
            }

            return usuarios;

        }
    }

    public Usuario findUserById(Integer id) throws SQLException {
        String sql = "select * from Usuario where idUsuario = "+id;
        Usuario usuario = null;
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){

            while (rs.next()) {
                usuario = Usuario.builder().idUsuario(rs.getInt("idUsuario"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .Oficio_idOficio(rs.getInt("Oficio_idOficio"))
                        .build();
            }

            if(usuario == null){
                throw new SQLException("No existe el usuario con id: "+id);
            }

            return usuario;

        }
    }

    public Usuario insertUser(Usuario user) throws SQLException {
        String sql = "INSERT INTO Usuario VALUES ("+user.getIdUsuario()+",\'"
                +user.getNombre()+"\',\'"
                +user.getApellidos()+"\',"
                +user.getOficio_idOficio()+")";
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement();
        ){
            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se puede añadir el usuario por un fallo en la Base de Datos");
            }
            return user;
        }
    }

    public Usuario deleteUserById(Integer id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE idUsuario = "+id;
        Usuario usuario = findUserById(id);
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement();
        ){
            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se puede eliminar el usuario con id: "+id);
            }
            return usuario;
        }
    }

    public Usuario updateUser(Usuario user) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = \'"+user.getNombre()
                +"\',apellidos = \'"+user.getApellidos()
                +"\',Oficio_idOficio = "+user.getOficio_idOficio()+" WHERE idUsuario="+ user.getIdUsuario();

        try(Connection con = datasource.getConnection();
        Statement stmt = con.createStatement();
        ){
            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se puede atualizar el usuario con id: "+user.getIdUsuario());
            }
            return user;
        }
    }

    public List<Usuario> getUsersByOficio(Integer oficioId) throws SQLException {
        String sql = "select * from Usuario where Oficio_idOficio = "+oficioId;
        List<Usuario> usuarios = new ArrayList<>();

        try(Connection con = datasource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                usuarios.add(Usuario.builder().idUsuario(rs.getInt("idUsuario"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .Oficio_idOficio(rs.getInt("Oficio_idOficio"))
                        .build());
            }

            if(usuarios.isEmpty()){
                throw new SQLException("No hay usuarios en el oficio: "+oficioId);
            }

            return usuarios;
        }
    }
}
