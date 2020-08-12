package com.magneto.MutantAnalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class MutantAnalyzerServiceTest {
    @Mock
    private MutantAnalyzerProcess mutantAnalyzerProcess;

    @Mock
    private DnaTestStats dnaTestStats;

    @InjectMocks
    private MutantAnalyzerService mutantAnalyzerService;


    @Test
    public void isMutant() throws BadDnaTestException {

        DnaTest dna = DnaTest.Builder.newBuilder()
                .withDna(new String[]{"A"}).build();
        when(mutantAnalyzerProcess.isMutant(dna)).thenReturn(false);

        boolean result = mutantAnalyzerService.isMutant(dna);

        verify(dnaTestStats).insertDnaTestResult(eq(dna.flattenedMatrix()), eq(false));
        assertThat(result).isFalse();
    }

    @Test
    public void getMutantsRatio() {
        MutantStat expectedResult = new MutantStat(4, 10, new BigDecimal(0.28));
        when(dnaTestStats.getDnaTestRatio()).thenReturn(expectedResult);

        MutantStat result = mutantAnalyzerService.getMutantsRatio();

        assertThat(result).isEqualTo(expectedResult);
    }
}
