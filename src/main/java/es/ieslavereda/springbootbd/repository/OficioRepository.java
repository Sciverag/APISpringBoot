package es.ieslavereda.springbootbd.repository;

import es.ieslavereda.springbootbd.model.Oficio;
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
public class OficioRepository {

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource datasource;

    public List<Oficio> getAll() throws SQLException {
        String sql = "select idOficio,descripcion,url from Oficio";
        List<Oficio> oficios = new ArrayList<>();
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                oficios.add(Oficio.builder()
                        .idOficio(rs.getInt("idOficio"))
                        .descripcion(rs.getString("descripcion"))
                        .url(rs.getString("url"))
                        .build());
            }

            if(oficios.isEmpty()){
                throw new SQLException("No hay oficios");
            }

            return oficios;

        }
    }

    public Oficio getById(Integer id) throws SQLException{
        String sql = "select idOficio,descripcion,url from Oficio where idOficio="+id;
        Oficio oficio = null;
        try(Connection con = datasource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                oficio = Oficio.builder().idOficio(rs.getInt("idOficio"))
                        .descripcion(rs.getString("descripcion"))
                        .url(rs.getString("url"))
                        .build();
            }

            if(oficio == null){
                throw new SQLException("No existe el oficio con id: "+id);
            }

            return oficio;
        }
    }

    public Oficio createOficio(Oficio oficio) throws SQLException{
        String sql = "INSERT INTO Oficio (idOficio,descripcion,url) VALUES ("+oficio.getIdOficio()+",\'"
                +oficio.getDescripcion()+"\',\'"+oficio.getUrl()+"\')";
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement()){

            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se ha podido añadir el oficio por un fallo en la Base de Datos");
            }
            return oficio;
        }
    }

    public Oficio removeById(Integer id) throws SQLException{
        String sql = "DELETE FROM Oficio where idOficio="+id;
        Oficio oficio = getById(id);
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement()){

            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se ha podido eliminar el oficio con id: "+id);
            }
            return oficio;
        }
    }

    public Oficio updateOficio(Oficio oficio) throws SQLException{
        String sql = "UPDATE Oficio SET descripcion=\'"
                +oficio.getDescripcion()+"\'," +
                "url=\'"+oficio.getUrl()+"\'" +
                "WHERE idOficio="+oficio.getIdOficio();
        try(Connection con = datasource.getConnection();
            Statement stmt = con.createStatement()){

            stmt.executeUpdate(sql);
            int updates = stmt.getUpdateCount();
            if(updates == 0){
                throw new SQLException("No se puede actualizar el oficio con id: "+oficio.getIdOficio());
            }
            return oficio;
        }
    }
}
