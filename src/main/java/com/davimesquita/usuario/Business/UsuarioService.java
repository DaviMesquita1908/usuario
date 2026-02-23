package com.davimesquita.usuario.Business;

import com.davimesquita.usuario.Business.Converter.UsuarioConverter;
import com.davimesquita.usuario.Business.Dto.UsuarioDTO;
import com.davimesquita.usuario.infrastructure.Entity.Usuario;
import com.davimesquita.usuario.infrastructure.Repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

private final UsuarioRepository usuarioRepository;
private final UsuarioConverter usuarioConverter;

public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
    Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
    return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
}

}
