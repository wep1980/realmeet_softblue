package br.com.wepdev.realmeet.validator;

import br.com.wepdev.realmeet.exception.InvalidRequestException;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;


public final class ValidatorUtils { // com final a classe n pode ser sobrescrita

    // Nao pode ser criado objetos de fora da propria classe
    private ValidatorUtils(){

    }

    // Metodo que lança uma excessão em caso de error
    public static void throwOnError(ValidationErros validationErrors){
        if(validationErrors.temError()){
           throw new InvalidRequestException(validationErrors);
        }
    }

    public static boolean validateRequired(String field, String fieldName, ValidationErros validationErrors){
        if(StringUtils.isBlank(field)){ // se não foi preenchido nenhum valor na String field. isBlanl checa nulos, string vazia e com espaços em branco
            validationErrors.adicionaErros(fieldName, fieldName + ValidatorConstants.AUSENTE);
            return false;
        }
        return true;
    }

    public static boolean validateRequired(Object field, String fieldName, ValidationErros validationErrors){
        if(Objects.isNull(field)){
            validationErrors.adicionaErros(fieldName, fieldName + ValidatorConstants.AUSENTE);
            return false;
        }
        return true;
    }

    // Varifica se string n excedeu o tamanho determindado
    public static boolean validateMaxLength(String field, String fieldName, int maxLength, ValidationErros validationErrors){
        if(!StringUtils.isBlank(field) && field.trim().length() > maxLength){ // se a string n esta vazia e o tamanho for maior q o maximo permitido
            validationErrors.adicionaErros(fieldName, fieldName + ValidatorConstants.EXCEEDS_MAX_LENGTH);
            return false;
        }
        return true;
    }
}
