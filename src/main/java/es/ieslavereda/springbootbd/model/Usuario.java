package es.ieslavereda.springbootbd.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String apellidos;
    private Integer Oficio_idOficio;
}
