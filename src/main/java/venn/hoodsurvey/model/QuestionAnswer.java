package venn.hoodsurvey.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionAnswer extends Question{
    String answer;

    QuestionAnswer(String question, QuestionType type) {
        super(question, type);
    }
}