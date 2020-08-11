package com.magneto.MutantAnalyzer.Controller;

import com.magneto.MutantAnalyzer.MutantAnalyzerService;
import com.magneto.MutantAnalyzer.MutantStat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatControllerTest {

    @Mock
    private MutantAnalyzerService mutantAnalyzerService;

    @InjectMocks
    private StatController statController;

    @Test
    public void stats() {
        MutantStat expectedBody = mock(MutantStat.class);
        when(mutantAnalyzerService.getMutantsRatio()).thenReturn(expectedBody);

        ResponseEntity<MutantStat> stats = statController.stats();

        assertThat(stats.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(stats.getBody()).isEqualTo(expectedBody);
    }
}
