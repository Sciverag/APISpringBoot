package es.ieslavereda.springbootbd.service;

import es.ieslavereda.springbootbd.model.Oficio;
import es.ieslavereda.springbootbd.repository.OficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OficioService {
    @Autowired
    private OficioRepository oficioRepository;

    public List<Oficio> getAllOficios() throws SQLException {
        return oficioRepository.getAll();
    }

    public Oficio getOficioById(Integer id) throws SQLException {
        return oficioRepository.getById(id);
    }

    public Oficio createOficio(Oficio oficio) throws SQLException {
        return oficioRepository.createOficio(oficio);
    }

    public Oficio removeOficioById(Integer id) throws SQLException {
        return oficioRepository.removeById(id);
    }

    public Oficio updateOficio(Oficio oficio) throws SQLException {
        return oficioRepository.updateOficio(oficio);
    }
}
