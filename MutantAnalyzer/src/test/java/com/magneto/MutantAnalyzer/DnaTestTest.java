package com.magneto.MutantAnalyzer;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DnaTestTest {

	@Test
    public void whenDnaIsNullThenBuilderThrowsBadDna() {
        assertThatThrownBy(() -> DnaTest.Builder.newBuilder().withDna(null).build())
                .isInstanceOf(BadDnaTestException.class);
    }

    @Test
    public void flattenedMatrix() throws BadDnaTestException {
        DnaTest dnaTest = DnaTest.Builder.newBuilder()
                .withDna(new String[]{"AA", "AA"}).build();

        assertThat(dnaTest.flattenedMatrix()).isEqualTo("[A, A],[A, A]");
    }
}
