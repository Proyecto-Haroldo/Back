package itm.proyectoharoldo.backend.Models.Web;

import java.util.List;

import itm.proyectoharoldo.backend.Models.Enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireAnswer {

    int questionId;
    String questionTitle;
    List<String> answer;
    QuestionType questionType;

}
