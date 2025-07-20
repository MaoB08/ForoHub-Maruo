package com.foroHubBackend.foroHubBackend.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicosAddDTO(
         Long id,
         @NotNull String titulo,
         @NotNull String mensaje,
         LocalDateTime fecha_creacion,
         @NotNull Status status,
         @NotNull String autor,
         @NotNull String curso
) {
}
