package com.example.individualproject.controller;

import com.example.individualproject.model.TeamManager;
import com.example.individualproject.model.Club;
import com.example.individualproject.repository.FakeDataStore;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teamManagers")
public class TeamManagerController {
    // this has to be static because web services are stateless
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

    // map to /teamManager/hello
    @GetMapping("/hello")
    @ResponseBody
    public String SayHello()
    {
        String msg = "Hello, your resources work!";
        return msg;
    }

    //get at /teamManagers
    @GetMapping
    public ResponseEntity<List<TeamManager>> getAllTeamManagers() {
        List<TeamManager> teamManagers = null;
        // if a player is added, use that method from the fakedatastore
        teamManagers = fakeDataStore.getTeamManagers();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "http://localhost:4200");

        if(teamManagers != null) {
            return ResponseEntity.ok().headers(responseHeaders).body(teamManagers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{teamManagerId}")
    //DELETE at http://localhost:XXXX/teamManagers/1
    public ResponseEntity deletePost(@PathVariable int teamManagerId) {
        fakeDataStore.deleteTeamManager(teamManagerId);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    //POST at http://localhost:XXXX/teamManagers/
    public ResponseEntity<TeamManager> createTeamManager(@RequestBody TeamManager teamManager) {
        if (!fakeDataStore.addTeamManager(teamManager)){
            String entity =  "Team manager with id " + teamManager.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "teamManagers" + "/" + teamManager.getId(); // url of the created teamManager
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @GetMapping("{teamManagerId}") //GET at http://localhost:XXXX/teamManagers/1
    public ResponseEntity<TeamManager> getCountryPath(@PathVariable(value = "teamManagerId") int teamManagerId) {
        TeamManager teamManager = fakeDataStore.getTeamManager(teamManagerId);

        if(teamManager != null) {
            return ResponseEntity.ok().body(teamManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    //PUT at http://localhost:XXXX/teamManagers/
    public ResponseEntity<TeamManager> updateTeamManager(@RequestBody TeamManager teamManager) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.updateTeamManager(teamManager)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid team manager id.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{teamMangerId}")
    //PUT at http://localhost:XXXX/teamManagers/{teamManagerId}
    public ResponseEntity<TeamManager> updateTeamManager(@PathVariable("teamManagerId") int teamManagerId, @RequestParam("teamManagerFirstName") String teamManagerFirstName, @RequestParam("teamManagerLastName") String teamManagerLastName, @RequestParam("teamManagerCountry") String teamManagerCountry,  @RequestParam("teamManagerClub") String teamManagerClub) {
        TeamManager teamManager = fakeDataStore.getTeamManager(teamManagerId);
        if (teamManager == null){
            return new ResponseEntity("Please provide a valid team manager id.",HttpStatus.BAD_REQUEST);
        }

        teamManager.setFirstName(teamManagerFirstName);
        teamManager.setLastName(teamManagerLastName);
        teamManager.setCountry(teamManagerCountry);
        teamManager.setClub(teamManagerClub);

        return ResponseEntity.noContent().build();
    }

}
