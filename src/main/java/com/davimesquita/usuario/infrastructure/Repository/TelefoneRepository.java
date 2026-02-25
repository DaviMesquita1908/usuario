package com.davimesquita.usuario.infrastructure.Repository;

import com.davimesquita.usuario.infrastructure.Entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
