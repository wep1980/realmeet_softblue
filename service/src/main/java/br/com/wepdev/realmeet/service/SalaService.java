package br.com.wepdev.realmeet.service;

import br.com.wepdev.realmeet.api.model.CriarSalaDTO;
import br.com.wepdev.realmeet.api.model.SalaDTO;
import br.com.wepdev.realmeet.domain.entity.Sala;
import br.com.wepdev.realmeet.domain.repository.SalaRepository;
import br.com.wepdev.realmeet.exception.SalaNotFoundException;
import br.com.wepdev.realmeet.mapper.SalaMapper;
import br.com.wepdev.realmeet.validator.SalaValidator;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
    //@Autowired // Limita na hora escrever testes.
    // Como esse salaRepossitory não sofre alteração e uma boa prática colocar o final, assim evita que a variavel seja reatribuida
    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;
    private  final SalaValidator salaValidator;

    /*
    Construtor criado para fazer injeção de dependencia.
    Como e gerenciado pelo spring, esse construtor e chamado automaticamente
     */
    public SalaService(SalaRepository salaRepository, SalaMapper salaMapper, SalaValidator salaValidator) {
        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
        this.salaValidator = salaValidator;
    }



    public SalaDTO getSala(Long id) {
        Objects.requireNonNull(id); // Verifica se o Id esta nulo antes do acesso a base de dados
        Sala sala = salaRepository
            .findByIdAndAtiva(id, true)
            .orElseThrow(() -> new SalaNotFoundException("Sala " + id + " não existe"));
        return salaMapper.converteSalaEmSalaDTO(sala);
        //return new SalaDTO().nome(sala.getNome()).id(sala.getId()).lugares(sala.getLugares());
    }

    public SalaDTO criarSala(CriarSalaDTO criarSalaDTO) {
        //var sala = Sala.newBuilder().lugares(criarSalaDTO.getLugares()).nome(criarSalaDTO.getNome()).build();
        salaValidator.validate(criarSalaDTO);
        var sala = salaMapper.converteCriarSalaDtoEmSala(criarSalaDTO);
        salaRepository.save(sala);

        //return new SalaDTO().id(sala.getId()).lugares(sala.getLugares()).nome(sala.getNome());
        return salaMapper.converteSalaEmSalaDTO(sala);
    }
}
