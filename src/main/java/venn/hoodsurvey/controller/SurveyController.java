package venn.hoodsurvey.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import venn.hoodsurvey.model.SurveyAnswer;
import venn.hoodsurvey.service.SurveyService;

@RestController
@RequestMapping("/venn/v1/")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(path = "/getSurvey", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSurvey(@RequestParam String hoodId, @RequestParam String surveyType) {
        return surveyService.getSurvey(hoodId, surveyType);
    }

    @RequestMapping(path = "/setSurvey", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setSurveyAnswer(@RequestBody SurveyAnswer surveyAnswer) {
        return surveyService.setSurveyAnswer(surveyAnswer);
    }
}
