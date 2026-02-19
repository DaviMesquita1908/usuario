package com.davimesquita.usuario.infrastructure.Repository;

import com.daviprogramacao.aprendendospring.infrastructure.Entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
