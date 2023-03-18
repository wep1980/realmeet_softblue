package br.com.wepdev.realmeet.core;

import br.com.wepdev.realmeet.Application;
import br.com.wepdev.realmeet.api.ApiClient;
import java.net.MalformedURLException;
import java.net.URL;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

// Teste usando o spring, ao iniciar o servidor a porta sera randomica, o ponto de entrada da execução e o Application.class
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles(profiles = "integration-test") // Utiliza esse application para teste
public abstract class BaseIntegrationTest {

    @Autowired
    private Flyway flyway;

    @LocalServerPort // Pegando o valor da porta randomica gerada ao iniciar os testes de integração
    private int serverPort;

    @BeforeEach // Metodo iniciado antes do teste acontecer
    public void setup() throws Exception {
        setupFlyway(); // Iniciar o flyway
        setupEach(); // Implementação de um Design patterns chamado Template Method, que delega para as sub-classes a implementação do comportamento
    }

    // Permite os testes tambem fazer inicializações
    protected void setupEach() throws Exception {}

    // Define o contexto do servidor, onde esta localizado, como fazer para ter um cliente.
    // Metodo utilitario para facilitar no momento dos testes
    protected void setLocalHostBasePath(ApiClient apiClient, String path) throws MalformedURLException {
        apiClient.setBasePath(new URL("http", "localhost", serverPort, path).toString());
    }

    // Metodo para fazer limpeza e criação das tabelas
    private void setupFlyway() {
        flyway.clean();
        flyway.migrate();
    }
}
