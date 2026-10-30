package com.mumbai.metro.controller;
import com.mumbai.metro.entity.MetroLineStation;
import com.mumbai.metro.entity.MetroLine;
import com.mumbai.metro.entity.Station;
import com.mumbai.metro.repository.MetroLineStationRepository;
import com.mumbai.metro.repository.MetroLineRepository;
import com.mumbai.metro.repository.StationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/metro-line-stations")
public class MetroLineStationController {
    private final MetroLineStationRepository linkRepository;
    private final MetroLineRepository lineRepository;
    private final StationRepository stationRepository;
    public MetroLineStationController(MetroLineStationRepository linkRepository, MetroLineRepository lineRepository, StationRepository stationRepository) {
        this.linkRepository = linkRepository; this.lineRepository = lineRepository; this.stationRepository = stationRepository;
    }
    @PostMapping("/line/{lineId}/station/{stationId}")
    public ResponseEntity<?> add(@PathVariable Long lineId, @PathVariable Long stationId, @RequestParam Integer stationSequence) {
        var line = lineRepository.findById(lineId);
        var station = stationRepository.findById(stationId);
        if (line.isEmpty()) return ResponseEntity.badRequest().body("Metro line not found: " + lineId);
        if (station.isEmpty()) return ResponseEntity.badRequest().body("Station not found: " + stationId);
        MetroLineStation link = new MetroLineStation(line.get(), station.get(), stationSequence);
        return ResponseEntity.ok(linkRepository.save(link));
    }
    @GetMapping("/line/{lineId}") public List<MetroLineStation> getByLine(@PathVariable Long lineId) {
        return linkRepository.findByMetroLineLineIdOrderByStationSequence(lineId);
    }
    @GetMapping("/station/{stationId}") public List<MetroLineStation> getByStation(@PathVariable Long stationId) {
        return linkRepository.findByStationStationId(stationId);
    }
    @GetMapping public List<MetroLineStation> getAll() { return linkRepository.findAll(); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!linkRepository.existsById(id)) return ResponseEntity.notFound().build();
        linkRepository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
