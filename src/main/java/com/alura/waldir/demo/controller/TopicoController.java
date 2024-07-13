package com.alura.waldir.demo.controller;

import com.alura.waldir.demo.domain.DataRegisterTopico;
import com.alura.waldir.demo.domain.Topico;
import com.alura.waldir.demo.domain.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DataRegisterTopico dataRegisterTopico){
        Topico topico = topicoRepository.save(new Topico(dataRegisterTopico));
        System.out.println(topico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping("/listar")
    public ResponseEntity listarTopicos(@PageableDefault(size = 10) Pageable pageable){

        return ResponseEntity.ok(topicoRepository.findAll());
    }

}
