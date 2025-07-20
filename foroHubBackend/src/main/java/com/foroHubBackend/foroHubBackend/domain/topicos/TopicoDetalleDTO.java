package com.foroHubBackend.foroHubBackend.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDetalleDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Status status,
        String autor,
        String curso
) {

    public TopicoDetalleDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
                );
    }
}
