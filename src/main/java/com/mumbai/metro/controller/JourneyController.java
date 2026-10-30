package com.mumbai.metro.controller;
import com.mumbai.metro.entity.Journey;
import com.mumbai.metro.entity.BusRouteStop;
import com.mumbai.metro.entity.MetroLineStation;
import com.mumbai.metro.repository.JourneyRepository;
import com.mumbai.metro.repository.BusRouteStopRepository;
import com.mumbai.metro.repository.MetroLineStationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {
    private final JourneyRepository journeyRepository;
    private final BusRouteStopRepository busStops;
    private final MetroLineStationRepository metroStations;
    public JourneyController(JourneyRepository journeyRepository, BusRouteStopRepository busStops, MetroLineStationRepository metroStations) {
        this.journeyRepository = journeyRepository; this.busStops = busStops; this.metroStations = metroStations;
    }
    @PostMapping public ResponseEntity<Journey> create(@RequestBody Journey journey) {
        journey.setJourneyId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(journeyRepository.save(journey));
    }
    @GetMapping public List<Journey> getAll() { return journeyRepository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Journey> getById(@PathVariable Long id) {
        return journeyRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Journey> update(@PathVariable Long id, @RequestBody Journey item) {
        return journeyRepository.findById(id).map(old -> {
            old.setSource(item.getSource()); old.setDestination(item.getDestination()); old.setTransportMode(item.getTransportMode());
            old.setEstimatedMinutes(item.getEstimatedMinutes()); old.setEstimatedFare(item.getEstimatedFare());
            return ResponseEntity.ok(journeyRepository.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!journeyRepository.existsById(id)) return ResponseEntity.notFound().build();
        journeyRepository.deleteById(id); return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public List<Map<String,Object>> searchBus(@RequestParam String source, @RequestParam String destination) {
        List<Map<String,Object>> result = new ArrayList<>();
        List<BusRouteStop> sources = busStops.findByBusStopStopNameIgnoreCase(source);
        List<BusRouteStop> destinations = busStops.findByBusStopStopNameIgnoreCase(destination);
        for (BusRouteStop a : sources) for (BusRouteStop b : destinations) {
            if (a.getBusRoute().getRouteId().equals(b.getBusRoute().getRouteId()) && a.getStopSequence() < b.getStopSequence()) {
                List<String> stops = new ArrayList<>();
                for (BusRouteStop s : busStops.findByBusRouteRouteIdOrderByStopSequence(a.getBusRoute().getRouteId()))
                    if (s.getStopSequence() >= a.getStopSequence() && s.getStopSequence() <= b.getStopSequence()) stops.add(s.getBusStop().getStopName());
                int diff = b.getStopSequence() - a.getStopSequence();
                Map<String,Object> row = new LinkedHashMap<>();
                row.put("source", source); row.put("destination", destination); row.put("transportMode", "BUS");
                row.put("routeNumber", a.getBusRoute().getRouteNumber()); row.put("routeName", a.getBusRoute().getRouteName());
                row.put("stops", stops); row.put("estimatedMinutes", diff * 5); row.put("estimatedFare", 10.0 + diff * 5.0); result.add(row);
            }
        }
        return result;
    }
    @GetMapping("/search/metro")
    public List<Map<String,Object>> searchMetro(@RequestParam String source, @RequestParam String destination) {
        List<Map<String,Object>> result = new ArrayList<>();
        List<MetroLineStation> sources = metroStations.findByStationStationNameIgnoreCase(source);
        List<MetroLineStation> destinations = metroStations.findByStationStationNameIgnoreCase(destination);
        for (MetroLineStation a : sources) for (MetroLineStation b : destinations) {
            if (a.getMetroLine().getLineId().equals(b.getMetroLine().getLineId()) && a.getStationSequence() < b.getStationSequence()) {
                List<String> stops = new ArrayList<>();
                for (MetroLineStation s : metroStations.findByMetroLineLineIdOrderByStationSequence(a.getMetroLine().getLineId()))
                    if (s.getStationSequence() >= a.getStationSequence() && s.getStationSequence() <= b.getStationSequence()) stops.add(s.getStation().getStationName());
                Map<String,Object> row = new LinkedHashMap<>();
                row.put("source", source); row.put("destination", destination); row.put("transportMode", "METRO");
                row.put("lineCode", a.getMetroLine().getLineCode()); row.put("lineName", a.getMetroLine().getLineName());
                row.put("stations", stops); row.put("stationCount", stops.size()); result.add(row);
            }
        }
        return result;
    }
}
