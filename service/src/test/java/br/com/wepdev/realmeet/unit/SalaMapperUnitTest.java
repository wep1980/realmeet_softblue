package br.com.wepdev.realmeet.unit;

import br.com.wepdev.realmeet.core.BaseUnitTest;
import br.com.wepdev.realmeet.mapper.SalaMapper;
import br.com.wepdev.realmeet.utils.MapperUtils;
import br.com.wepdev.realmeet.utils.TestConstants;
import br.com.wepdev.realmeet.utils.TestDateCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaMapperUnitTest extends BaseUnitTest {
    private SalaMapper victim;

    @BeforeEach
    // Metodo chamado antes de cada teste
    void setupEach() {
        victim = MapperUtils.salaMapper(); // pegando uma instacia(objeto de SalaMapper)
    }

    @Test
    void testeConverteEntidadeEmDTO() {
        //var sala = Sala.newBuilder().id(1L).nome("Sala A").lugares(6).build();
        var sala = TestDateCreator.newSalaBuilder().id(TestConstants.DEFAULT_SALA_ID).build();

        var salaDTO = victim.converteSalaEmSalaDTO(sala);

        Assertions.assertEquals(sala.getId(), salaDTO.getId());
        Assertions.assertEquals(sala.getNome(), salaDTO.getNome());
        Assertions.assertEquals(sala.getLugares(), salaDTO.getLugares());
    }
}
