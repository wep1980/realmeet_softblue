package br.com.wepdev.realmeet.utils;

import br.com.wepdev.realmeet.domain.entity.Sala;

public final class TestDateCreator {

    private TestDateCreator() {}

    public static Sala.Builder newSalaBuilder() {
        return Sala.newBuilder().nome(TestConstants.DEFAULT_SALA_NOME).lugares(TestConstants.DEFAULT_SALA_LUGARES);
    }
}
