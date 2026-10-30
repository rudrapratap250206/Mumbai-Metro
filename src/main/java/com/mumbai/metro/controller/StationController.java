
package com.mumbai.metro.controller;

import com.mumbai.metro.entity.Station;
import com.mumbai.metro.repository.StationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationRepository repository;

    public StationController(StationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Station not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Station createStation(@RequestBody Station station) {
        return repository.save(station);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Station not found");
        }
        repository.deleteById(id);
    }
}
