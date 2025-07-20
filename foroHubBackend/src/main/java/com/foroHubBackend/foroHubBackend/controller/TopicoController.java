package com.foroHubBackend.foroHubBackend.controller;


import com.foroHubBackend.foroHubBackend.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foro")
public class TopicoController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private ValidacionesTopicos validacionesTopicos;

    @PostMapping("/topicos")
    public ResponseEntity añadirTopico(@RequestBody @Valid TopicosAddDTO topicosAddDTO){
        boolean variable = validacionesTopicos.validacionTituloyMensaje(topicosAddDTO.titulo(),topicosAddDTO.mensaje());
        if (variable){
            var topico = new Topico(topicosAddDTO);
            topicosRepository.save(topico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new TopicoDetalleDTO(topico));
        }else{
         return ResponseEntity.badRequest().body("El título y mensaje ya existen");
        }
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDetalleDTO>> listarTopicos(@PageableDefault(size = 10, sort = {"titulo"}) Pageable pageable){
        var datos = topicosRepository.findAll(pageable).map(TopicoDetalleDTO :: new);
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTopicosenEspecifico(@PathVariable Long id){
        var datos = topicosRepository.findById(id);
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopcico(@PathVariable Long id, @RequestBody TopicoDetalleDTO topicoDetalleDTO){
        if (topicosRepository.existsById(id)){
            var topico = topicosRepository.getReferenceById(id);
            topico.actualizarInformacion(topicoDetalleDTO);
            return ResponseEntity.ok(topicoDetalleDTO);
        }
        return ResponseEntity.badRequest().body("El Topico que busca no existe");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            topicosRepository.delete(topico.get());
            return ResponseEntity.ok("Tópico eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
