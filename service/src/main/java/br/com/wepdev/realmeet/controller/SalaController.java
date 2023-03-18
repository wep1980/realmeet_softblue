package br.com.wepdev.realmeet.controller;

import br.com.wepdev.realmeet.api.facade.SalasApi;
import br.com.wepdev.realmeet.api.model.SalaDTO;
import br.com.wepdev.realmeet.service.SalaService;
import br.com.wepdev.realmeet.util.ResponseEntityUtils;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaController implements SalasApi {
    private final Executor controllersExecutor;
    private final SalaService salaService;

    public SalaController(Executor controllersExecutor, SalaService salaService) {
        this.controllersExecutor = controllersExecutor;
        this.salaService = salaService;
    }

    @Override
    public CompletableFuture<ResponseEntity<SalaDTO>> getSala(Long id) {
        //return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(salaService.getSala(id)));
        return CompletableFuture
            .supplyAsync(() -> salaService.getSala(id), controllersExecutor)
            .thenApply(r -> ResponseEntityUtils.ok(r));
    }
}
