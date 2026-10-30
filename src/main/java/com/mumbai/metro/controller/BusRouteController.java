package com.mumbai.metro.controller;
import com.mumbai.metro.entity.BusRoute;
import com.mumbai.metro.repository.BusRouteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus-routes")
public class BusRouteController {
    private final BusRouteRepository repository;
    public BusRouteController(BusRouteRepository repository) { this.repository = repository; }
    @GetMapping public List<BusRoute> getAll() { return repository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<BusRoute> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<BusRoute> create(@RequestBody BusRoute item) {
        item.setRouteId(null); return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }
    @PutMapping("/{id}") public ResponseEntity<BusRoute> update(@PathVariable Long id, @RequestBody BusRoute item) {
        return repository.findById(id).map(old -> {
            old.setRouteNumber(item.getRouteNumber()); old.setRouteName(item.getRouteName()); old.setOperator(item.getOperator());
            old.setStartPoint(item.getStartPoint()); old.setEndPoint(item.getEndPoint()); old.setActive(item.getActive());
            return ResponseEntity.ok(repository.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
