package br.com.wepdev.realmeet.unit;

import br.com.wepdev.realmeet.core.BaseUnitTest;
import br.com.wepdev.realmeet.domain.repository.SalaRepository;
import br.com.wepdev.realmeet.service.SalaService;
import br.com.wepdev.realmeet.utils.MapperUtils;
import br.com.wepdev.realmeet.utils.TestConstants;
import br.com.wepdev.realmeet.utils.TestDateCreator;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

class SalaServiceUnitTest extends BaseUnitTest {
    private SalaService victim;

    @Mock
    private SalaRepository salaRepository;

    @BeforeEach
    // Metodo chamado antes de cada teste
    void setupEach() {
        victim = new SalaService(salaRepository, MapperUtils.salaMapper());
    }

    @Test
    void testaGetSala() {
        var sala = TestDateCreator.newSalaBuilder().id(TestConstants.DEFAULT_SALA_ID).build();
        Mockito.when(salaRepository.findById(TestConstants.DEFAULT_SALA_ID)).thenReturn(Optional.of(sala));

        var salaDTO = victim.getSala(TestConstants.DEFAULT_SALA_ID);
        Assertions.assertEquals(sala.getId(), salaDTO.getId());
        Assertions.assertEquals(sala.getNome(), salaDTO.getNome());
        Assertions.assertEquals(sala.getLugares(), salaDTO.getLugares());
    }
}
