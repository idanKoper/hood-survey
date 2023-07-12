package venn.hoodsurvey.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venn.hoodsurvey.model.Question;
import venn.hoodsurvey.model.QuestionType;
import venn.hoodsurvey.model.Survey;
import venn.hoodsurvey.service.SurveyServiceImpl;

import java.util.List;
import java.util.UUID;

@Component
public class Bootstrap implements CommandLineRunner {

    private final SurveyServiceImpl surveyService;

    public Bootstrap(SurveyServiceImpl surveyService) {
        this.surveyService = surveyService;
    }

    @Override
    public void run(String... args) {
        loadSurveys();
    }

    private void loadSurveys() {
        Question firstQuestion = Question.builder()
                .question("How would you rate your overall residential experience?")
                .type(QuestionType.RATE)
                .build();

        Question secondQuestion = Question.builder()
                .question("Rate your connection to the neighborhood and local events?")
                .type(QuestionType.RATE)
                .build();

        Question thirdQuestion = Question.builder()
                .question("How would you rate your connection with your neighbors?")
                .type(QuestionType.RATE)
                .build();

        Question fourQuestion = Question.builder()
                .question("Please share any additional details or suggestions that can help us enhance your overall experience.")
                .type(QuestionType.FREE_TEXT)
                .build();

        Survey survey = Survey.builder()
                .Id(UUID.randomUUID().toString())
                .questions(List.of(firstQuestion, secondQuestion, thirdQuestion, fourQuestion))
                .type("social")
                .hoodId("f1ecb077-b326-4b90-84a0-26aa109bd2b9")
                .build();

        surveyService.enrichSurveys(survey);
    }
}
