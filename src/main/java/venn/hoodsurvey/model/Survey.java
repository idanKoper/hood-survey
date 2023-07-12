package venn.hoodsurvey.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Survey {
    String Id;
    List<Question> questions;
    String type;
    String hoodId;
}
