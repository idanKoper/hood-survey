package venn.hoodsurvey.service;

import org.springframework.http.ResponseEntity;
import venn.hoodsurvey.model.SurveyAnswer;

public interface SurveyService {

    ResponseEntity<String> getSurvey (String hoodId, String surveyType);

    ResponseEntity<String> setSurveyAnswer(SurveyAnswer surveyAnswer);
}
