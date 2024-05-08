package es.ieslavereda.springbootbd.service;

import es.ieslavereda.springbootbd.model.Usuario;
import es.ieslavereda.springbootbd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() throws SQLException {
        return usuarioRepository.getAll();
    }

    public Usuario getUserById(Integer id) throws SQLException {
        return usuarioRepository.findUserById(id);
    }

    public Usuario createUser(Usuario user) throws SQLException {
        return usuarioRepository.insertUser(user);
    }

    public Usuario removeUserById(Integer id) throws SQLException {
        return usuarioRepository.deleteUserById(id);
    }

    public Usuario updateUser(Usuario user) throws SQLException {
        return usuarioRepository.updateUser(user);
    }

    public List<Usuario> getUsersByOficio(Integer oficioId) throws SQLException {
        return usuarioRepository.getUsersByOficio(oficioId);
    }
}
