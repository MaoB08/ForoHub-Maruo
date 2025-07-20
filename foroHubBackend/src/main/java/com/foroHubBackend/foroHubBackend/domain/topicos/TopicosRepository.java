package com.foroHubBackend.foroHubBackend.domain.topicos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository <Topico, Long> {
    boolean existsByTituloAndMensaje(@NotNull String titulo, @NotNull String mensaje);
}
