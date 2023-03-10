package br.com.wepdev.realmeet;

import br.com.wepdev.realmeet.api.facade.RoomsApi;
import br.com.wepdev.realmeet.api.model.Room;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController implements RoomsApi {

    @Override
    public CompletableFuture<ResponseEntity<Room>> listRooms(Long id) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(new Room().id(1L).name("Room 1")));
    }
}
