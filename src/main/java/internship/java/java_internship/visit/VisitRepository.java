package internship.java.java_internship.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
}
