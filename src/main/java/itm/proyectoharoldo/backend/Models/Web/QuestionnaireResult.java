package itm.proyectoharoldo.backend.Models.Web;

/*
export interface QuestionnaireResult {
  metadata: QuestionnaireMetadata;
  answers: QuestionnaireAnswer[];
}
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireResult {

    QuestionnaireMetadata metadata;
    List<QuestionnaireAnswer> answers;

}
