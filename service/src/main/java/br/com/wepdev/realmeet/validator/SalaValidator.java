package br.com.wepdev.realmeet.validator;

import br.com.wepdev.realmeet.api.model.CriarSalaDTO;
import org.springframework.stereotype.Component;

@Component
public class SalaValidator {

    public SalaValidator() {
    }

    public void validate(CriarSalaDTO criarSalaDTO){
        var validationErrors = new ValidationErros();
        ValidatorUtils.validateRequired(criarSalaDTO.getNome(), ValidatorConstants.SALA_NOME, validationErrors);
        ValidatorUtils.validateMaxLength(criarSalaDTO.getNome(), ValidatorConstants.SALA_NOME, ValidatorConstants.SALA_NOME_TAMANHO_MAXIMO, validationErrors);

        ValidatorUtils.throwOnError(validationErrors);
    }
}
