package com.viniciuscerqueira.usuario.business.converter;


import com.viniciuscerqueira.usuario.business.dto.EnderecoDTO;
import com.viniciuscerqueira.usuario.business.dto.TelefoneDTO;
import com.viniciuscerqueira.usuario.business.dto.UsuarioDTO;
import com.viniciuscerqueira.usuario.infrastructure.entity.Endereco;
import com.viniciuscerqueira.usuario.infrastructure.entity.Telefone;
import com.viniciuscerqueira.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))

                .build();
    }

    //Converter Lista Endereco DTO para Lista Endereco
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoDTO enderecoDTO : enderecoDTOS){
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }

    //Converter Endereco DTO para Endereco
    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }
    //Converter Lista TelefoneDTO para Lista Telefone
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        List<Telefone> telefones = new ArrayList<>();
        for(TelefoneDTO telefoneDTO : telefoneDTOS){
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;
    }


    //Converter objeto TelefoneDTO para Telefone
    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

//--------------------------------------------------------------------------

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))

                .build();
    }

    //Converter Lista Endereco para Lista EnderecoDTO
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos){
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        for(Endereco endereco : enderecos){
            enderecosDTO.add(paraEnderecoDTO(endereco));
        }
        return enderecosDTO;
    }

    //Converter Endereco DTO para Endereco
    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }
    //Converter Lista Telefone para Lista TelefoneDTO
    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones){
        List<TelefoneDTO> telefonesDTO = new ArrayList<>();
        for(Telefone telefone : telefones){
            telefonesDTO.add(paraTelefoneDTO(telefone));
        }
        return telefonesDTO;
    }


    //Converter objeto Telefone para TelefoneDTO
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id (entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

}
