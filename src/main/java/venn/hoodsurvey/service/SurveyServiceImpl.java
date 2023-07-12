package venn.hoodsurvey.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import venn.hoodsurvey.model.Survey;
import venn.hoodsurvey.model.SurveyAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    //hoodIdToSurveyTypeAndSurvey {key: hoodId, value: {key: surveyType, value: survey}}
    HashMap<String, HashMap<String, Survey>> surveys = new HashMap<>();

    //hoodIdToSurveyTypeAndListSurveysAnswers {key: hoodId, value: {key: surveyType, value: List<SurveyAnswer>}}
    HashMap<String, HashMap<String, List<SurveyAnswer>>> surveysAnswers = new HashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public ResponseEntity<String> getSurvey(String hoodId, String surveyType) {
        HashMap<String, Survey> surveysByHoodId = surveys.get(hoodId);
        if (surveysByHoodId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found");
        } else {
            Survey surveyByHoodIdAndType = surveysByHoodId.get(surveyType);
            isSurveyExist(surveyByHoodIdAndType);

            try {
                return new ResponseEntity<>(mapper.writeValueAsString(surveyByHoodIdAndType), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot convert Survey object to Json");
            }
        }
    }

    @Override
    public ResponseEntity<String> setSurveyAnswer(SurveyAnswer surveyAnswer) {
        HashMap<String, List<SurveyAnswer>> surveyHoodIdToSurveyAnswers = surveysAnswers.get(surveyAnswer.getHoodId());
        if (surveyHoodIdToSurveyAnswers == null) {
            addSurveyAnswer(surveyAnswer);
        } else {
            surveyHoodIdToSurveyAnswers.computeIfAbsent(surveyAnswer.getSurveyType(), k -> new ArrayList<>()).add(surveyAnswer);
        }

        return ResponseEntity.ok().build();
    }

    private void addSurveyAnswer(SurveyAnswer surveyAnswer) {
        surveysAnswers.put(surveyAnswer.getHoodId(), new HashMap<>() {
            {
                put(surveyAnswer.getSurveyType(), new ArrayList<>(List.of(surveyAnswer)));
            }
        });
    }

    public void enrichSurveys(Survey survey) {
        surveys.put(survey.getHoodId(), new HashMap<>() {
            {
                put(survey.getType(), survey);
            }
        });
    }

    private static void isSurveyExist(Survey surveyByHoodIdAndType) {
        if (surveyByHoodIdAndType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found");
        }
    }
}
