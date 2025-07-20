package com.foroHubBackend.foroHubBackend.domain.topicos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topico(TopicosAddDTO topicosAddDTO) {
        this.id = null;
        this.titulo = topicosAddDTO.titulo();
        this.mensaje = topicosAddDTO.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.status = topicosAddDTO.status();
        this.autor = topicosAddDTO.autor();
        this.curso = topicosAddDTO.curso();
    }

    public void actualizarInformacion(TopicoDetalleDTO topicoDetalleDTO) {
        this.titulo = topicoDetalleDTO.titulo();
        this.mensaje = topicoDetalleDTO.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.status = topicoDetalleDTO.status();
        this.autor = topicoDetalleDTO.autor();
        this.curso = topicoDetalleDTO.curso();
    }
}
