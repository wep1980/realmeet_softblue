package br.com.wepdev.realmeet.unit;

import br.com.wepdev.realmeet.core.BaseUnitTest;
import br.com.wepdev.realmeet.domain.repository.SalaRepository;
import br.com.wepdev.realmeet.exception.SalaNotFoundException;
import br.com.wepdev.realmeet.service.SalaService;
import br.com.wepdev.realmeet.utils.MapperUtils;
import br.com.wepdev.realmeet.utils.TestConstants;
import br.com.wepdev.realmeet.utils.TestDateCreator;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


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
    void testaGetSalaSucesso() {
        var sala = TestDateCreator.newSalaBuilder().id(TestConstants.DEFAULT_SALA_ID).build();
        Mockito
            .when(salaRepository.findByIdAndAtiva(TestConstants.DEFAULT_SALA_ID, true))
            .thenReturn(Optional.of(sala));

        var dto = victim.getSala(TestConstants.DEFAULT_SALA_ID);
        Assertions.assertEquals(sala.getId(), dto.getId());
        Assertions.assertEquals(sala.getNome(), dto.getNome());
        Assertions.assertEquals(sala.getLugares(), dto.getLugares());
    }

    @Test
    void testaGetSalaNotFound() {
        Mockito.when(salaRepository.findByIdAndAtiva(TestConstants.DEFAULT_SALA_ID, true)).thenReturn(Optional.empty());

        Assertions.assertThrows(SalaNotFoundException.class, () -> victim.getSala(TestConstants.DEFAULT_SALA_ID));
    }
}
