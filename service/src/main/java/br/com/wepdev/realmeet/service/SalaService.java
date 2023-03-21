package br.com.wepdev.realmeet.service;

import br.com.wepdev.realmeet.api.model.SalaDTO;
import br.com.wepdev.realmeet.domain.entity.Sala;
import br.com.wepdev.realmeet.domain.repository.SalaRepository;
import br.com.wepdev.realmeet.exception.SalaNotFoundException;
import br.com.wepdev.realmeet.mapper.SalaMapper;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
    //@Autowired // Limita na hora escrever testes.
    // Como esse salaRepossitory não sofre alteração e uma boa prática colocar o final, assim evita que a variavel seja reatribuida
    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    /*
    Construtor criado para fazer injeção de dependencia.
    Como e gerenciado pelo spring, esse construtor e chamado automaticamente
     */
    public SalaService(SalaRepository salaRepository, SalaMapper salaMapper) {
        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
    }

    public SalaDTO getSala(Long id) {
        Objects.requireNonNull(id); // Verifica se o Id esta nulo antes do acesso a base de dados
        Sala sala = salaRepository
            .findByIdAndAtiva(id, true)
            .orElseThrow(() -> new SalaNotFoundException("Sala " + id + " não existe"));
        return salaMapper.converteSalaEmSalaDTO(sala);
        //return new SalaDTO().nome(sala.getNome()).id(sala.getId()).lugares(sala.getLugares());
    }
}
