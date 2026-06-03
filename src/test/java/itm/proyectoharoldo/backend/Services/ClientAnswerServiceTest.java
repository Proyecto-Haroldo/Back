package itm.proyectoharoldo.backend.Services;

import itm.proyectoharoldo.backend.Models.Web.QuestionnaireMetadata;
import itm.proyectoharoldo.backend.Models.Web.QuestionnaireResult;
import itm.proyectoharoldo.backend.Repositories.QuestionRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class ClientAnswerServiceTest {

    @Mock
    private AIService aiService;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ClientAnswerService clientAnswerService;

    @Test
    void saveQuestionnaireResult_shouldThrowResourceAccessException_whenAIResponseIsNull(){

        QuestionnaireResult questionnaireResult = new QuestionnaireResult();
        questionnaireResult.setAnswers(List.of());
        QuestionnaireMetadata questionnaireMetadata = new QuestionnaireMetadata();
        questionnaireResult.setMetadata(questionnaireMetadata);
        Long clientId = -1L;

        lenient().when(questionRepository.findAllById(List.of(0L, -1L))).thenReturn(List.of());
        lenient().when(aiService.getAiRecommendation("")).thenReturn(null);

        assertThrows(ResourceAccessException.class, () ->  clientAnswerService.saveQuestionnaireResult(questionnaireResult, clientId));

    }

}