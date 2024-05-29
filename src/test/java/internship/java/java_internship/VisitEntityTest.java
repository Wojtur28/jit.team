package internship.java.java_internship;

import internship.java.java_internship.visit.VisitEntity;
import internship.java.java_internship.visit.VisitRepository;
import internship.java.java_internship.visit.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitEntityTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitService visitService;

    VisitEntity visitEntity1;
    VisitEntity visitEntity2;

    @BeforeEach
    public void setUp(){
        visitEntity1 = VisitEntity.builder()
                .id(1L)
                .visitDate(LocalDateTime.of(2024, 5, 28, 14, 30, 0))
                .personName("test1")
                .catName("test1")
                .catAge(3)
                .catColor("test1")
                .build();
        visitEntity2 = VisitEntity.builder()
                .visitDate(LocalDateTime.of(2024, 5, 26, 20, 30, 0))
                .personName("test2")
                .catName("test2")
                .catAge(4)
                .catColor("test2")
                .build();
    }

    @Test
    public void shouldReturnVisitList(){
        List<VisitEntity> visitEntities = Arrays.asList(visitEntity1, visitEntity2);

        when(visitRepository.findAll()).thenReturn(visitEntities);

        ResponseEntity<List<VisitEntity>> response = visitService.getVisits();

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        assertEquals(visitEntity1, response.getBody().get(0));
        assertEquals(visitEntity2, response.getBody().get(1));
    }

    @Test
    public void shouldReturnVisitById(){
        when(visitRepository.findById(visitEntity1.getId())).thenReturn(java.util.Optional.of(visitEntity1));

        ResponseEntity<VisitEntity> response = visitService.getVisit(visitEntity1.getId());

        assertNotNull(response);
        assertEquals(visitEntity1, response.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenVisitNotFound() {
        when(visitRepository.findById(visitEntity1.getId())).thenReturn(java.util.Optional.empty());

        ResponseEntity<VisitEntity> response = visitService.getVisit(visitEntity1.getId());

        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }

    @Test
    public void shouldReturnVisitWhenSaved(){
        when(visitRepository.save(visitEntity1)).thenReturn(visitEntity1);

        ResponseEntity<VisitEntity> response = visitService.createVisit(visitEntity1);

        assertNotNull(response);
        assertEquals(visitEntity1, response.getBody());
    }

    @Test
    @Disabled
    public void shouldThrowIllegalArgumentException(){
        when(visitRepository.save(visitEntity2)).thenReturn(visitEntity1);

        ResponseEntity<VisitEntity> response = visitService.createVisit(visitEntity2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "Poza godzinami pracy weterynarza";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //I don't know how to do that in JUNIT 5
    }

    @Test
    @Disabled
    public void shouldSaveVisitReturn404(){
        when(visitRepository.save(visitEntity2)).thenReturn(visitEntity2);

        ResponseEntity<VisitEntity> response = visitService.createVisit(visitEntity2);


    }

}
