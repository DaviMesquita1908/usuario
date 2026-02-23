package com.davimesquita.usuario.Business;

import com.davimesquita.usuario.Business.Converter.UsuarioConverter;
import com.davimesquita.usuario.Business.Dto.UsuarioDTO;
import com.davimesquita.usuario.infrastructure.Entity.Usuario;
import com.davimesquita.usuario.infrastructure.Exception.ConflictException;
import com.davimesquita.usuario.infrastructure.Exception.ResourceNotFoundException;
import com.davimesquita.usuario.infrastructure.Repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

private final UsuarioRepository usuarioRepository;
private final UsuarioConverter usuarioConverter;
private final PasswordEncoder passwordEncoder;

public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
    emailExiste(usuarioDTO.getEmail());
    usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
    Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
    return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
}
    public void emailExiste(String email) {
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        }catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
