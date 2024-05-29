package internship.java.java_internship.visit;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public ResponseEntity<List<VisitEntity>> getVisits() {
        return ResponseEntity.ok(visitRepository.findAll());
    }

    public ResponseEntity<VisitEntity> getVisit(Long visitId) {
        return visitRepository.findById(visitId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<VisitEntity> createVisit(VisitEntity visitEntity) {
        if (visitEntity.getVisitDate().getHour() > 16 || visitEntity.getVisitDate().getHour() < 8)
            throw new IllegalArgumentException("Poza godzinami pracy weterynarza");

        if (isWeekend(visitEntity.getVisitDate()))
            throw new IllegalArgumentException("Weterynarz nie pracuje w weekendy");

        visitEntity.setVisitDate(visitEntity.getVisitDate().withMinute(0));
        visitRepository.save(visitEntity);
        return ResponseEntity.ok(visitEntity);
    }

    public ResponseEntity<VisitEntity> updateVisit(Long visitId, VisitEntity visitEntity) {
        return visitRepository.findById(visitId)
                .map(visit -> {
                    visit.setVisitDate(visitEntity.getVisitDate());
                    visit.setPersonName(visitEntity.getPersonName());
                    visit.setCatColor(visitEntity.getCatColor());
                    visit.setCatName(visitEntity.getCatName());
                    visit.setCatAge(visitEntity.getCatAge());
                    return ResponseEntity.ok(visitRepository.save(visitEntity));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteVisit(Long visitId) {
        return visitRepository.findById(visitId)
                .map(visit -> {
                    visitRepository.delete(visit);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean isWeekend(LocalDateTime localDateTime) {
        return switch (localDateTime.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };
    }

}
