package br.com.wepdev.realmeet.domain.repository;

import br.com.wepdev.realmeet.domain.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {}
