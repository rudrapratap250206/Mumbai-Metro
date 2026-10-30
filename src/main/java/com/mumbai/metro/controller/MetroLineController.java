package com.mumbai.metro.controller;
import com.mumbai.metro.entity.MetroLine;
import com.mumbai.metro.repository.MetroLineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/metro-lines")
public class MetroLineController {
    private final MetroLineRepository repository;
    public MetroLineController(MetroLineRepository repository) { this.repository = repository; }
    @GetMapping public List<MetroLine> getAll() { return repository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<MetroLine> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<MetroLine> create(@RequestBody MetroLine item) {
        item.setLineId(null); return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }
    @PutMapping("/{id}") public ResponseEntity<MetroLine> update(@PathVariable Long id, @RequestBody MetroLine item) {
        return repository.findById(id).map(old -> {
            old.setLineCode(item.getLineCode()); old.setLineName(item.getLineName()); old.setColor(item.getColor());
            old.setOperator(item.getOperator()); old.setActive(item.getActive());
            return ResponseEntity.ok(repository.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
