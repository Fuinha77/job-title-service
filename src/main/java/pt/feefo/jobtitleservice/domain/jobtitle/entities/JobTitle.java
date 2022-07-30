package pt.feefo.jobtitleservice.domain.jobtitle.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import pt.feefo.jobtitleservice.config.commons.BasicEntity;
import pt.feefo.jobtitleservice.domain.jobtitle.dto.JobTitleDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_job_title")
@Where(clause = "deleted = false")
@EqualsAndHashCode(callSuper = false)
public class JobTitle extends BasicEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    public static JobTitle ofId(final Long id) {
        return JobTitle.builder()
                .id(id)
                .build();
    }

    public static JobTitle ofDTO(final JobTitleDTO jobTitleDTO) {
        return JobTitle.builder()
                .description(jobTitleDTO.getDescription())
                .build();
    }
}
