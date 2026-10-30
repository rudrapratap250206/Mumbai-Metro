package com.mumbai.metro.controller;

import com.mumbai.metro.entity.Schedule;
import com.mumbai.metro.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleRepository repository;

    public ScheduleController(ScheduleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Schedule> create(@RequestBody Schedule item) {
        item.setScheduleId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> update(@PathVariable Long id, @RequestBody Schedule item) {
        return repository.findById(id).map(old -> {
            old.setServiceType(item.getServiceType());
            old.setServiceName(item.getServiceName());
            old.setDepartureTime(item.getDepartureTime());
            old.setArrivalTime(item.getArrivalTime());
            old.setDayOfWeek(item.getDayOfWeek());
            old.setActive(item.isActive());
            return ResponseEntity.ok(repository.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
