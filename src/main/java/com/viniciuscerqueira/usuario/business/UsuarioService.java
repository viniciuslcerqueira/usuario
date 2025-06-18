package com.viniciuscerqueira.usuario.business;


import com.viniciuscerqueira.usuario.business.converter.UsuarioConverter;
import com.viniciuscerqueira.usuario.business.dto.UsuarioDTO;
import com.viniciuscerqueira.usuario.infrastructure.entity.Usuario;
import com.viniciuscerqueira.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }




}
