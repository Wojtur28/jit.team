package internship.java.java_internship.visit;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<List<VisitEntity>> getVisits() {
        return visitService.getVisits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitEntity> getVisitsById(@PathVariable Long id) {
        return visitService.getVisit(id);
    }

    @PostMapping
    public ResponseEntity<VisitEntity> createVisit(@RequestBody VisitEntity visitEntity) {
        return visitService.createVisit(visitEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitEntity> updateVisit(@PathVariable Long id, @RequestBody VisitEntity visitEntity) {
        return visitService.updateVisit(id, visitEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        return visitService.deleteVisit(id);
    }
}
