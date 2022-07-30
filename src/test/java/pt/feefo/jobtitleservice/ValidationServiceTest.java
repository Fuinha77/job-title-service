package pt.feefo.jobtitleservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pt.feefo.jobtitleservice.domain.jobtitle.service.ValidationService;
import pt.feefo.jobtitleservice.exception.JobStringNullException;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    private static String RANDOM_STRING;

    @InjectMocks
    private ValidationService service;

    @Before
    public void init() {
        RANDOM_STRING = "randomString";
    }

    @Test
    public void givenSameExistentJobStringAndAUserJobInputStringWhenFindSimilarityThenAssertEquals() {
        final var expected = 1.0;
        final var response = this.service.findSimilarity(RANDOM_STRING, RANDOM_STRING);
        assertEquals(String.valueOf(expected), String.valueOf(response));
    }

    @Test
    public void givenTotallyDifferentExistentJobStringAndAUserJobInputStringWhenFindSimilarityThenAssertEquals() {
        final var expected = 0.0;
        final var stringMock = "zzzzzzzzzzzzzzz";
        final var response = this.service.findSimilarity(RANDOM_STRING, stringMock);
        assertEquals(String.valueOf(expected), String.valueOf(response));
    }

    @Test(expected = JobStringNullException.class)
    public void givenAnExistentJobStringAndAUserJobInputStringwhenFindSimilarityThenThrowJobStringNullException() {
        this.service.findSimilarity(RANDOM_STRING, null);
    }

}
