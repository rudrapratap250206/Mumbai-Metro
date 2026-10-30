package com.mumbai.metro.controller;
import com.mumbai.metro.entity.BusStop;
import com.mumbai.metro.repository.BusStopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus-stops")
public class BusStopController {
    private final BusStopRepository repository;
    public BusStopController(BusStopRepository repository) { this.repository = repository; }
    @GetMapping public List<BusStop> getAll() { return repository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<BusStop> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<BusStop> create(@RequestBody BusStop item) {
        item.setStopId(null); return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }
    @PutMapping("/{id}") public ResponseEntity<BusStop> update(@PathVariable Long id, @RequestBody BusStop item) {
        return repository.findById(id).map(old -> {
            old.setStopCode(item.getStopCode()); old.setStopName(item.getStopName()); old.setCity(item.getCity());
            old.setLatitude(item.getLatitude()); old.setLongitude(item.getLongitude()); old.setActive(item.getActive());
            return ResponseEntity.ok(repository.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
