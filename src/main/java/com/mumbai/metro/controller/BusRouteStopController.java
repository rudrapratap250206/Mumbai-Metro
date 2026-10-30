package com.mumbai.metro.controller;
import com.mumbai.metro.entity.BusRouteStop;
import com.mumbai.metro.repository.BusRouteStopRepository;
import com.mumbai.metro.repository.BusRouteRepository;
import com.mumbai.metro.repository.BusStopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus-route-stops")
public class BusRouteStopController {
    private final BusRouteStopRepository linkRepository;
    private final BusRouteRepository routeRepository;
    private final BusStopRepository stopRepository;
    public BusRouteStopController(BusRouteStopRepository linkRepository, BusRouteRepository routeRepository, BusStopRepository stopRepository) {
        this.linkRepository = linkRepository; this.routeRepository = routeRepository; this.stopRepository = stopRepository;
    }
    @PostMapping("/route/{routeId}/stop/{stopId}")
    public ResponseEntity<?> add(@PathVariable Long routeId, @PathVariable Long stopId, @RequestParam Integer stopSequence) {
        var route = routeRepository.findById(routeId);
        var stop = stopRepository.findById(stopId);
        if (route.isEmpty()) return ResponseEntity.badRequest().body("Bus route not found: " + routeId);
        if (stop.isEmpty()) return ResponseEntity.badRequest().body("Bus stop not found: " + stopId);
        BusRouteStop link = new BusRouteStop(route.get(), stop.get(), stopSequence);
        return ResponseEntity.ok(linkRepository.save(link));
    }
    @GetMapping("/route/{routeId}") public List<BusRouteStop> getByRoute(@PathVariable Long routeId) {
        return linkRepository.findByBusRouteRouteIdOrderByStopSequence(routeId);
    }
    @GetMapping("/stop/{stopId}") public List<BusRouteStop> getByStop(@PathVariable Long stopId) {
        return linkRepository.findByBusStopStopId(stopId);
    }
    @GetMapping public List<BusRouteStop> getAll() { return linkRepository.findAll(); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!linkRepository.existsById(id)) return ResponseEntity.notFound().build();
        linkRepository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
