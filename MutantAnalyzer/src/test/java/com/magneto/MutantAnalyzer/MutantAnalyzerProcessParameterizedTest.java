package com.magneto.MutantAnalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(Parameterized.class)
public class MutantAnalyzerProcessParameterizedTest {

	private final String[] dna;
    private final boolean expectedResult;
    private final MutantAnalyzerProcess mutantAnalyzerProcess = new MutantAnalyzerProcess();

    public MutantAnalyzerProcessParameterizedTest(final String[] dna, final boolean expectedResult) {
        this.dna = dna;
        this.expectedResult = expectedResult;
    }

    @Test
    public void isMutantReturnsExpectedResult() throws BadDnaTestException {
        assertThat(mutantAnalyzerProcess.isMutant(dna)).isEqualTo(expectedResult);
    }

    @Parameterized.Parameters
    public static Collection dnaWithExpectedResult() {
        return Arrays.asList(new Object[][]{{
                // 1x1
                new String[]{
                        "A"}, false
        }, {
                // 2x2
                new String[]{
                        "AA",
                        "AA"}, false
        }, {
                // 3x3
                new String[]{
                        "AAA",
                        "AAA",
                        "AAA"}, false
        }, {
                // 4x4 - all directions
                new String[]{
                        "TTTT",
                        "TTTT",
                        "TTTT",
                        "TTTT"}, true
        }, {
                // 4x4 - 4 horizontal
                new String[]{
                        "AAAA",
                        "TTTT",
                        "CCCC",
                        "GGGG"}, true
        }, {
                // 4x4 - 1 horizontal, 1 vertical
                new String[]{
                        "AAAA",
                        "TATT",
                        "CACC",
                        "GAGG"}, true
        }, {
                // 4x4 - 1 horizontal
                new String[]{
                        "AAAA",
                        "TCGG",
                        "ATCC",
                        "ATTC"}, false
        }, {
                // 5x5 - 1 horizontal, 1 vertical
                new String[]{
                        "AAAAA",
                        "TATTC",
                        "CACCA",
                        "CACCA",
                        "GAGGC"}, true
        }, {
                // 5x5 - 1 horizontal
                new String[]{
                        "AAAAA",
                        "TATTC",
                        "CTCCA",
                        "CACCA",
                        "GAGGC"}, false
        }, {
                // 5x5 - 1 horizontal, 1 diagonal
                new String[]{
                        "AAAAA",
                        "TAATC",
                        "CTCAA",
                        "CACCA",
                        "GAGGC"}, true
        }, {
                // 6x6 - EXERCISE EXAMPLE 1 horizontal, 1 diagonal, 1 vertical
                new String[]{
                        "ATGCGA",
                        "CAGTGC",
                        "TTATGT",
                        "AGAAGG",
                        "CCCCTA",
                        "TCACTG"}, true
        }, {
                // 8x8 - 1 horizontal
                new String[]{
                        "AAAAATAA",
                        "CTCTCTCT",
                        "ATATATAA",
                        "AACGGGAG",
                        "CCGGAATT",
                        "TTTGGGAA",
                        "AAGAATAA",
                        "GGGTGGTG"}, false
        }, {
                // 8x8 - 2 horizontal
                new String[]{
                        "AAAAAAAA",
                        "CTCTCTCT",
                        "ATATATAA",
                        "AACGGGAG",
                        "CCGGAATT",
                        "TTTGGGAA",
                        "AAGAATAA",
                        "GGGTGGTG"}, true
        }, {
                // 8x8 - 1 horizontal 1 diagonal asc
                new String[]{
                        "AAAAATAA",
                        "CTCTCTCT",
                        "ATATATAA",
                        "AACGAGAG",
                        "CCGAAATT",
                        "TTAGGGAA",
                        "AAGAATAT",
                        "GGGTGGTG"}, true
        }, {
                // 8x8 - 1 horizontal
                new String[]{
                        "AAAAATAA",
                        "CTCTCTCT",
                        "ATATATAA",
                        "AACGAGAG",
                        "CCGAAATT",
                        "TTTGGGAA",
                        "AAGAATAT",
                        "GGGTGGTG"}, false
        }});
    }
}
