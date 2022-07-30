package pt.feefo.jobtitleservice.domain.jobtitle.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pt.feefo.jobtitleservice.config.commons.baseservice.BaseService;
import pt.feefo.jobtitleservice.domain.jobtitle.dto.JobTitleDTO;
import pt.feefo.jobtitleservice.domain.jobtitle.entities.JobTitle;
import pt.feefo.jobtitleservice.domain.jobtitle.repository.IJobTitleRepository;
import pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces.IJobTitleService;
import pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces.IValidationService;
import pt.feefo.jobtitleservice.exception.JobTitleNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service(value = "jobTitleService")
public class JobTitleService extends BaseService<JobTitle, Long, IJobTitleRepository> implements IJobTitleService {

    private final IValidationService validationService;

    public JobTitleService(@Qualifier("jobTitleRepository") final IJobTitleRepository repository,
                           @Qualifier("validationService") final IValidationService validationService) {
        super(repository);
        this.validationService = validationService;
    }

    //main method to be called to find the normalized Job String
    @Override
    public JobTitleDTO findNormalizedJob(final String jobDescription) {
        final var jobTitleDTOList = this.findAllJobTitles();
        return this.findMostSimilarString(jobTitleDTOList, jobDescription);
    }

    @Override
    public List<JobTitleDTO> findAllJobTitles() {
        // find all JobTitles and maps them to a JobTitleDTO List
        return this.repository.findAll().stream()
                .map(JobTitleDTO::ofEntity)
                .collect(Collectors.toList());
    }

    private JobTitleDTO findMostSimilarString(final List<JobTitleDTO> jobTitleDTOList, final String jobDescription) {
        // create a new JobTitleDTO and initialized it empty
        final var newJobTitleDTO = new JobTitleDTO();
        // iterate through every jobTitle in the database to check which one is the most similar.
        jobTitleDTOList.forEach(jobTitle -> {
            // check the similarity between the word in the database and the word inputted by the user.
            final var similarityScore = this.validationService.findSimilarity(jobTitle.getDescription(), jobDescription);
            // check to see if the newJobTitleDTO is not null
            if (Objects.nonNull(newJobTitleDTO.getSimilarityScore())) {
                // checks if the existing new jobTitle similarity is bigger/equal than 0 and smaller/equal than 1.
                if (similarityScore >= 0 && similarityScore <= 1) {
                    // checks if the current job similarity is lower than the new one.
                    if (newJobTitleDTO.getSimilarityScore() <= similarityScore) {
                        // updates the current newJobTitleDTO because the new word found was more similar
                        this.updateJobTitleDTO(newJobTitleDTO, jobTitle, similarityScore);
                    }
                }
            } else {
                // newJobTitleDTO is still null, so it will check if the similarityScore is bigger than 0 or
                // smaller/equal to 1
                if (similarityScore > 0 && similarityScore <= 1) {
                    // Sets up the JobTitleDTO with info from the job title found in the database.
                    this.updateJobTitleDTO(newJobTitleDTO, jobTitle, similarityScore);
                }
            }
        });
        // if the object is null here, it means that the similarity of the jobDescription that the user wrote, had a
        // similarityScore of 0.
        if (!Objects.nonNull(newJobTitleDTO.getSimilarityScore())) {
            // throws a JobTitleNotFoundException because there wasn't any similarity between the word written by the
            // user and of the words on the database.
            throw new JobTitleNotFoundException(jobDescription);
        }
        // returns a JobTitleDTO with the most similarity with the word inputted by the user
        return newJobTitleDTO;
    }

    //private method to update the newJobTitleDTO by reference, to avoid code to be repeated unnecessarily
    private void updateJobTitleDTO(final JobTitleDTO newJobTitleDTO, final JobTitleDTO jobTitleDTO, final Double similarityScore) {
        newJobTitleDTO.setId(jobTitleDTO.getId());
        newJobTitleDTO.setDescription(jobTitleDTO.getDescription());
        newJobTitleDTO.setSimilarityScore(similarityScore);
    }


}
