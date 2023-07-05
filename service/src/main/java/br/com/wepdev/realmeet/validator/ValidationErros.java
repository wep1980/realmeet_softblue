package br.com.wepdev.realmeet.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.util.Streamable;

// Streamable<ValidationError> Permite trabalhar com essas chamadas dentro da stream api, facilita na hora de extrair as informações de dentro do objeto
public class ValidationErros implements Streamable<ValidationError> {
    private List<ValidationError> validatorErrorList;

    public ValidationErros() {
        this.validatorErrorList = new ArrayList<>();
    }

    public ValidationErros adicionaErros(String field, String errorCode) {
        return adicionaErros(new ValidationError(field, errorCode));
    }

    public ValidationErros adicionaErros(ValidationError validationError) {
        validatorErrorList.add(validationError);
        return this; // retorna o proprio objeto, isso e interessante para Apis fluentes, onde existe o encadeamento de chamadas.
    }

    /*
       Retorna o erro com base no index
     */
    public ValidationError getError(int index) {
        return validatorErrorList.get(index);
    }

    /*
       Retorna a quantidade de erros
     */
    public int getQuantidadeDeErros() {
        return validatorErrorList.size();
    }

    /*
    Metodo que verifica se tem erros na lista
     */
    public boolean temError() {
        return !validatorErrorList.isEmpty(); // Se a lista não estiver vazia retorna true
    }

    @Override
    public String toString() {
        return "ValidationErros{" + "validatorErrorList=" + validatorErrorList + '}';
    }

    // Itera na lista de validationError
    @Override
    public Iterator<ValidationError> iterator() {
        return validatorErrorList.iterator();
    }
}
