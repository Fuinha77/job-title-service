package pt.feefo.jobtitleservice.domain.jobtitle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.feefo.jobtitleservice.config.commons.BasicResponseDTO;
import pt.feefo.jobtitleservice.domain.jobtitle.dto.JobTitleDTO;
import pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces.IJobTitleService;

@RestController
@RequestMapping("/api/job-title")
@Api(value = "Job Title", tags = "Job Title")
public class JobTitleController {

    private final IJobTitleService jobTitleService;

    public JobTitleController(@Qualifier("jobTitleService") final IJobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping(value = "/normalize/{jobDescription}")
    @ApiOperation(value = "Get the normalized version of the user input")
    public BasicResponseDTO<JobTitleDTO> getNormalizedInput(@PathVariable("jobDescription") final String jobDescription) {
        final var jobTitleDTO = this.jobTitleService.findNormalizedJob(jobDescription);
        return BasicResponseDTO.withData(jobTitleDTO);
    }

}
