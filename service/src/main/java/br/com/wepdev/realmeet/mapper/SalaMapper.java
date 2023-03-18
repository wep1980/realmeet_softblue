package br.com.wepdev.realmeet.mapper;

import br.com.wepdev.realmeet.api.model.SalaDTO;
import br.com.wepdev.realmeet.domain.entity.Sala;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Gerenciado pelo spring
public abstract class SalaMapper {

    public abstract SalaDTO converteSalaEmSalaDTO(Sala sala);
}
