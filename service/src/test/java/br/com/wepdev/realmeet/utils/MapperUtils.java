package br.com.wepdev.realmeet.utils;

import br.com.wepdev.realmeet.mapper.SalaMapper;
import org.mapstruct.factory.Mappers;

// Classe que contem apenas metodos estaticos
public final class MapperUtils {

    // Construtor privado para evitar que ela seja instaciada de fora
    private MapperUtils() {}

    /*
    Forma de conseguir uma instacia do mapper para teste
     */
    public static SalaMapper salaMapper() {
        return Mappers.getMapper(SalaMapper.class);
    }
}
