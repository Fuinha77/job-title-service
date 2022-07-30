package pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces;

import pt.feefo.jobtitleservice.domain.jobtitle.dto.JobTitleDTO;

import java.util.List;

public interface IJobTitleService {

    List<JobTitleDTO> findAllJobTitles();

    JobTitleDTO findNormalizedJob(final String jobDescription);
}
