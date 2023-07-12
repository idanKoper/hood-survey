package venn.hoodsurvey.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    String question;
    QuestionType type;
}
