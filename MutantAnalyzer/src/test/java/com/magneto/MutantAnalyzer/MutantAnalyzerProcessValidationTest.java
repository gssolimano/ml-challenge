package com.magneto.MutantAnalyzer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MutantAnalyzerProcessValidationTest {

	private final MutantAnalyzerProcess mutabilityAnalysisProcessor = new MutantAnalyzerProcess();

    @Test
    public void whenDnaIsEmptyThenBuilderThrowsBadDna() {
        assertThatThrowsBadDnaWhenBuildsWithDnas(new String[0]);
    }

    @Test
    public void whenDnaHasNotSquareFormThenBuilderThrowsBadDna() {
        String[] firstSample = new String[]{"GGG", "GGG"};
        String[] secSample = new String[]{"AAA", "AA"};
        String[] thirdSample = new String[]{"AAT", "TATT", "AAAA"};

        assertThatThrowsBadDnaWhenBuildsWithDnas(firstSample, secSample, thirdSample);
    }

    @Test
    public void whenDnaHasInvalidCharsWithSquareFormThenBuilderThrowsBadDna() {
        String[] firstSample = new String[]{"GGG", "GGG", "G-G"};
        String[] secSample = new String[]{"AAA", "AAA", "ggg"};
        String[] thirdSample = new String[]{"AAT", "TAB", "AAAA"};

        assertThatThrowsBadDnaWhenBuildsWithDnas(firstSample, secSample, thirdSample);
    }

    private void assertThatThrowsBadDnaWhenBuildsWithDnas(final String[]... dnaSamples) {
        for (String[] dna : dnaSamples) {
            assertThatThrownBy(() -> mutabilityAnalysisProcessor.isMutant(dna))
                    .isInstanceOf(BadDnaTestException.class);
        }
    }

}
