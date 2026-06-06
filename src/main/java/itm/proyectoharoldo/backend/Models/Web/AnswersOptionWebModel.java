package itm.proyectoharoldo.backend.Models.Web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
According to Johann's model:
export interface QuestionOption {
  id: string; ???????
  text: string;
  keywords?: string[];
}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswersOptionWebModel {

    private Long id;
    private String text;

}
