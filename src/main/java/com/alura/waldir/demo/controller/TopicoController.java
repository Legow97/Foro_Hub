package com.alura.waldir.demo.controller;

import com.alura.waldir.demo.domain.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DataRegisterTopico dataRegisterTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(dataRegisterTopico));
        DataResponseTopico dataResponseTopico = new DataResponseTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseTopico);
    }

    @GetMapping("/listar")
    public ResponseEntity listarTopicos(@PageableDefault(size = 10) Pageable pageable){

        return ResponseEntity.ok(topicoRepository.findByStatusTrue(pageable).map(DataListTopico::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerTopicos(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datos = new DataListTopico(topico);
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DataupdateTopico dataupdateTopico){
//        Topico topico = topicoRepository.getReferenceById(id);
//        topico.dataUpdate(dataupdateTopico);
//        return ResponseEntity.ok(new DataResponseTopico(topico));
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.dataUpdate(dataupdateTopico);
            return ResponseEntity.ok(new DataResponseTopico(topico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
