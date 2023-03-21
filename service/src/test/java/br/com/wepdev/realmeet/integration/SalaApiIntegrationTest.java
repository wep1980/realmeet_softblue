package br.com.wepdev.realmeet.integration;

import br.com.wepdev.realmeet.api.facade.SalaApi;
import br.com.wepdev.realmeet.core.BaseIntegrationTest;
import br.com.wepdev.realmeet.domain.repository.SalaRepository;
import br.com.wepdev.realmeet.utils.TestConstants;
import br.com.wepdev.realmeet.utils.TestDateCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

public class SalaApiIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private SalaApi salaApi;

    @Autowired
    private SalaRepository salaRepository;

    protected void setupEach() throws Exception {
        setLocalHostBasePath(salaApi.getApiClient(), "/v1");
    }

    @Test
    public void testGetSalaSucesso() {
        var sala = TestDateCreator.newSalaBuilder().build();

        salaRepository.saveAndFlush(sala);

        Assertions.assertNotNull(sala.getId());
        Assertions.assertTrue(sala.getAtiva());

        var salaDto = salaApi.getSala(sala.getId());

        Assertions.assertEquals(sala.getId(), salaDto.getId());
        Assertions.assertEquals(sala.getNome(), salaDto.getNome());
        Assertions.assertEquals(sala.getLugares(), salaDto.getLugares());
    }

    @Test
    public void testGetSalaInativa() {
        var sala = TestDateCreator.newSalaBuilder().ativa(false).build();
        salaRepository.saveAndFlush(sala);

        Assertions.assertFalse(sala.getAtiva());
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> salaApi.getSala(sala.getId()));
    }

    @Test
    public void testGetSalaNaoExiste() {
        Assertions.assertThrows(
            HttpClientErrorException.NotFound.class,
            () -> salaApi.getSala(TestConstants.DEFAULT_SALA_ID)
        );
    }
}
