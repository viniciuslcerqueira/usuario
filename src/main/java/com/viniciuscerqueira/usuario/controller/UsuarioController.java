package com.viniciuscerqueira.usuario.controller;


import com.viniciuscerqueira.usuario.business.UsuarioService;
import com.viniciuscerqueira.usuario.business.dto.EnderecoDTO;
import com.viniciuscerqueira.usuario.business.dto.TelefoneDTO;
import com.viniciuscerqueira.usuario.business.dto.UsuarioDTO;
import com.viniciuscerqueira.usuario.infrastructure.entity.Usuario;
import com.viniciuscerqueira.usuario.infrastructure.exceptions.ConflictException;
import com.viniciuscerqueira.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
         return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha())
        );
        return jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario(@RequestBody UsuarioDTO dto,
                                                            @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO dto, @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO dto, @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto));
    }



}
