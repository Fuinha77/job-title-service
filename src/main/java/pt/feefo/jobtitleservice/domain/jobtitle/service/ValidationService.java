package pt.feefo.jobtitleservice.domain.jobtitle.service;

import liquibase.util.StringUtil;
import org.springframework.stereotype.Service;
import pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces.IValidationService;
import pt.feefo.jobtitleservice.exception.JobStringNullException;

@Service(value = "validationService")
public class ValidationService implements IValidationService {

    // checks if the Strings are Empty or null and then calls method to find the Levenshtein's Distance.
    @Override
    public double findSimilarity(final String existentJob, final String userJobInput) {
        if (StringUtil.isEmpty(existentJob) || StringUtil.isEmpty(userJobInput)) {
            throw new JobStringNullException();
        }
        double maxLength = Double.max(existentJob.length(), userJobInput.length());
        if (maxLength > 0) {
            return (maxLength - getLevenshteinDistance(existentJob, userJobInput)) / maxLength;
        }
        return 1.0;
    }

    // Finds Levenshtein's Distance between the two words that ranges from 0.0 to 1.0
    // 0.0 means that they aren't similar and 1.0 means that they are the same word.
    private int getLevenshteinDistance(final String existentJob, final String userJobInput) {
        final int existentJobLength = existentJob.length();
        final int userJobInputLength = userJobInput.length();

        final int[][] T = new int[existentJobLength + 1][userJobInputLength + 1];
        for (int i = 1; i <= existentJobLength; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= userJobInputLength; j++) {
            T[0][j] = j;
        }
        int cost;
        for (int i = 1; i <= existentJobLength; i++) {
            for (int j = 1; j <= userJobInputLength; j++) {
                cost = existentJob.charAt(i - 1) == userJobInput.charAt(j - 1) ? 0 : 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }
        return T[existentJobLength][userJobInputLength];
    }

}
