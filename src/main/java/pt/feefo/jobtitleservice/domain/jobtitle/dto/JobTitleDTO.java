package pt.feefo.jobtitleservice.domain.jobtitle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.feefo.jobtitleservice.domain.jobtitle.entities.JobTitle;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobTitleDTO {

    private Long id;
    private String description;
    private Double similarityScore;

    public static JobTitleDTO ofEntity(final JobTitle jobTitle) {
        return JobTitleDTO.builder()
                .id(jobTitle.getId())
                .description(jobTitle.getDescription())
                .build();
    }
}
