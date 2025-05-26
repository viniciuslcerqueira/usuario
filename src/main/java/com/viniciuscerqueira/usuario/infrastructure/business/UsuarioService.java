package com.viniciuscerqueira.usuario.infrastructure.business;


import com.viniciuscerqueira.usuario.infrastructure.business.converter.UsuarioConverter;
import com.viniciuscerqueira.usuario.infrastructure.business.dto.UsuarioDTO;
import com.viniciuscerqueira.usuario.infrastructure.entity.Usuario;
import com.viniciuscerqueira.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
