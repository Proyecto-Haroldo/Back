package itm.proyectoharoldo.backend.Services;

import itm.proyectoharoldo.backend.Models.DTO.Analysis.GradeRequest;
import itm.proyectoharoldo.backend.Repositories.AnalysisRepository;
import itm.proyectoharoldo.backend.Repositories.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class AnalysisServiceTest {

    @Mock
    private UserRepository  userRepository;

    @Mock
    private AnalysisRepository analysisRepository;

    @InjectMocks
    private AnalysisService analysisService;

    @Test
    void gradeAnalysis_shouldThrowNoSuchElementException_whenAdviserEmailDoesNotExist(){

        Long analysisId = -1L;
        GradeRequest gradeRequest = new GradeRequest();
        String adviserEmail = "advisernonexistant@testing.com";

        when(userRepository.findByEmail(adviserEmail)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> analysisService.gradeAnalysis(analysisId, gradeRequest, adviserEmail));

    }

    @Test
    void gradeAnalysis_shouldThrowNoSuchElementException_whenAnalysisDoesNotExist(){

        Long analysisId = -1L;
        GradeRequest gradeRequest = new GradeRequest();
        String adviserEmail = "advisernonexistant@testing.com";

        lenient().when(analysisRepository.findByIdWithDetails(analysisId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> analysisService.gradeAnalysis(analysisId, gradeRequest, adviserEmail));

    }

}