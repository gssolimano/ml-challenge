package com.magneto.MutantAnalyzer.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.magneto.MutantAnalyzer.BadDnaTestException;
import com.magneto.MutantAnalyzer.DnaTest;
import com.magneto.MutantAnalyzer.MutantAnalyzerService;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {

    @Mock
    private MutantAnalyzerService mutantAnalyzerService;

    @InjectMocks
    private MutantController mutantController;

    @Test
    public void whenIsMutantServiceReturnsTrueThenReturnsOk() throws BadDnaTestException {

        DnaTest input = DnaTest.Builder.newBuilder().withDna(new String[]{"A"}).build();
        when(mutantAnalyzerService.isMutant(input)).thenReturn(true);

        ResponseEntity<?> result = mutantController.isMutant(input);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void whenIsmutantAnalyzerServiceReturnsFalseThenReturnsForbidden() throws BadDnaTestException {

        DnaTest input = DnaTest.Builder.newBuilder().withDna(new String[]{"A"}).build();
        when(mutantAnalyzerService.isMutant(input)).thenReturn(false);

        ResponseEntity<?> result = mutantController.isMutant(input);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
