package com.footballleaguebackend.demo.controllers;

import com.footballleaguebackend.demo.models.Player;
import com.footballleaguebackend.demo.repositories.FakeDataStore;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    // this has to be static because web services are stateless
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

    // map to /players/hello
    @GetMapping("/hello")
    @ResponseBody
    public String SayHello()
    {
        String msg = "Hello, your resources work!";
        return msg;
    }

    //get at /players
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = null;
        // if a player is added, use that method from the fakedatastore
        players = fakeDataStore.getPlayers();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "http://localhost:4200");

        if(players != null) {
            return ResponseEntity.ok().headers(responseHeaders).body(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{playerId}")
    //DELETE at http://localhost:XXXX/players/1
    public ResponseEntity deletePost(@PathVariable int playerId) {
        fakeDataStore.deletePlayer(playerId);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    //POST at http://localhost:XXXX/players/
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        if (!fakeDataStore.addPlayer(player)){
            String entity =  "Player with id " + player.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "players" + "/" + player.getId(); // url of the created player
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @GetMapping("{playerId}") //GET at http://localhost:XXXX/players/1
    public ResponseEntity<Player> getCountryPath(@PathVariable(value = "playerId") int playerId) {
        Player player = fakeDataStore.getPlayer(playerId);

        if(player != null) {
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    //PUT at http://localhost:XXXX/players/
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.updatePlayer(player)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid player id.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{playerId}")
    //PUT at http://localhost:XXXX/players/{playerId}
    public ResponseEntity<Player> updatePlayer(@PathVariable("playerId") int playerId, @RequestParam("playerFirstName") String playerFirstName, @RequestParam("playerLastName") String playerLastName, @RequestParam("playerCountry") String playerCountry,  @RequestParam("playerClub") String playerClub)  {
        Player player = fakeDataStore.getPlayer(playerId);
        if (player == null){
            return new ResponseEntity("Please provide a valid player id.",HttpStatus.BAD_REQUEST);
        }

        player.setFirstName(playerFirstName);
        player.setLastName(playerLastName);
        player.setCountry(playerCountry);
        player.setClub(playerClub);

        return ResponseEntity.noContent().build();
    }

}