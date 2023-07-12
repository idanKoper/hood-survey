package venn.hoodsurvey.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class SurveyAnswer {
    @NotNull(message = "hoodId is mandatory")
    private String hoodId;

    @NotNull(message = "userId is mandatory")
    private String userId;

    @NotNull(message = "surveyId is mandatory")
    private String surveyId;

    @NotEmpty(message = "List cannot be null or empty")
    private List<QuestionAnswer> questionAnswers;

    @NotNull(message = "surveyId is mandatory")
    private String surveyType;
}
