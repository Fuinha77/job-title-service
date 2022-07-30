package pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces;

public interface IValidationService {

    double findSimilarity(final String existentJob, final String userJobInput);

}
