package es.ieslavereda.springbootbd.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Oficio {
    private int idOficio;
    private String descripcion;
    private String url;
}
