package br.com.wepdev.realmeet.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// O final na classe determina q ela n pode ser extendida
public final class ResponseEntityUtils {

    // Construtor private para garantir que essa classe não seja instanciada
    private ResponseEntityUtils() {}

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<T> create(T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
