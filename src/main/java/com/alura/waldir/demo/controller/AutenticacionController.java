package com.alura.waldir.demo.controller;

import com.alura.waldir.demo.domain.usuarios.DataAtenticaionUsuario;
import com.alura.waldir.demo.domain.usuarios.Usuario;
import com.alura.waldir.demo.infra.security.DatosJWTtoken;
import com.alura.waldir.demo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DataAtenticaionUsuario dataAtenticaionUsuario){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAtenticaionUsuario.username(),dataAtenticaionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTtoken =tokenService.tokenGenerator((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
