package pt.feefo.jobtitleservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pt.feefo.jobtitleservice.domain.jobtitle.entities.JobTitle;
import pt.feefo.jobtitleservice.domain.jobtitle.repository.IJobTitleRepository;
import pt.feefo.jobtitleservice.domain.jobtitle.service.JobTitleService;
import pt.feefo.jobtitleservice.domain.jobtitle.service.interfaces.IValidationService;
import pt.feefo.jobtitleservice.exception.JobStringNullException;
import pt.feefo.jobtitleservice.exception.JobTitleNotFoundException;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobTitleServiceTest {

    private static Long RANDOM_LONG;
    private static String RANDOM_STRING;

    @InjectMocks
    private JobTitleService service;

    @Mock
    private IValidationService validationService;

    @Mock
    private IJobTitleRepository repository;

    @Before
    public void init() {
        RANDOM_LONG = 1L;
        RANDOM_STRING = "randomString";
    }

    @Test
    public void whenFindAllJobTitlesThenAssertTrue() {
        final var jobTitle = JobTitle.builder().id(RANDOM_LONG).build();
        when(this.repository.findAll()).thenReturn(List.of(jobTitle));
        final var jobTitleList = this.service.findAllJobTitles();
        verify(this.repository).findAll();
        assertEquals(jobTitle.getId(), jobTitleList.get(0).getId());
    }

    @Test
    public void givenJobDescriptionWhenFindNormalizedJobReturnsValueBetweenZeroAndOneThenAssertEquals() {
        final var randomDouble = 0.5;
        final var secondRandomDouble = 0.6;
        final var stringMock = "RandomStri";
        final var jobTitleMock = JobTitle.builder()
                .id(RANDOM_LONG)
                .description(stringMock)
                .build();
        final var jobTitleTwoMock = JobTitle.builder()
                .id(RANDOM_LONG)
                .description(RANDOM_STRING)
                .build();
        final var jobTitleList = List.of(jobTitleMock, jobTitleTwoMock);
        when(this.repository.findAll()).thenReturn(jobTitleList);
        when(this.validationService.findSimilarity(jobTitleTwoMock.getDescription(), RANDOM_STRING))
                .thenReturn(randomDouble, secondRandomDouble);
        final var response = this.service.findNormalizedJob(RANDOM_STRING);
        verify(this.repository).findAll();
        verify(this.validationService).findSimilarity(jobTitleMock.getDescription(), RANDOM_STRING);
        assertEquals(RANDOM_STRING, response.getDescription());
    }

    @Test(expected = JobTitleNotFoundException.class)
    public void givenJobDescriptionWhenFindNormalizedJobReturnsValueBetweenZeroAndOneThenThrowJobTitleNotFoundException() {
        final var randomDouble = 0.0;
        final var jobTitle = JobTitle.builder()
                .id(RANDOM_LONG)
                .description(RANDOM_STRING)
                .build();
        when(this.repository.findAll()).thenReturn(List.of(jobTitle));
        when(this.validationService.findSimilarity(jobTitle.getDescription(), RANDOM_STRING)).thenReturn(randomDouble);
        this.service.findNormalizedJob(RANDOM_STRING);
    }

    @Test(expected = JobStringNullException.class)
    public void givenJobDescriptionWhenFindNormalizedJobReturnsValueBetweenZeroAndOneThenThrowJobStringNullException() {
        final var jobTitle = JobTitle.builder()
                .id(RANDOM_LONG)
                .description(RANDOM_STRING)
                .build();
        when(this.repository.findAll()).thenReturn(List.of(jobTitle));
        when(this.validationService.findSimilarity(jobTitle.getDescription(), RANDOM_STRING)).thenThrow(new JobStringNullException());
        this.service.findNormalizedJob(RANDOM_STRING);
    }

}
