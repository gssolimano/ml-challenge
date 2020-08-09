package com.magneto.MutantAnalyzer;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DnaTest.Builder.class)
public class DnaTest {
	
	private static final String DNA_BASE_CHARS = "ATCG"; 
	private char[][] dnaChain;
	
	private DnaTest() {
	}
	
	char valueAt(int x, int y) {
		return dnaChain[x][y];
	}
	
	int chainSize() {
		return dnaChain.length;
	}
	
	String flattenedMatrix() {
		return Arrays.stream(dnaChain).map(Arrays::toString).collect(Collectors.joining(","));
	}
	
	@JsonPOJOBuilder
    public static final class Builder {
		private String dnaArray[];
		
		private Builder() {
			
		}
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withDna(final String[] aDnaChain) {
			this.dnaArray = aDnaChain;
			return this;
		}
		
		public DnaTest build() throws BadDnaTestException {
			validateEmptyArray();
			DnaTest dnaTest = new DnaTest();
			dnaTest.dnaChain = dnaFlatToMatrixForm();
			
			System.out.println(dnaTest.flattenedMatrix());
			return dnaTest;
			
		}
		
		private void validateEmptyArray() throws BadDnaTestException {
            if (dnaArray == null || dnaArray.length == 0) {
                throw new BadDnaTestException("The DNA to Test is empty or null");
            }
        }
		
		private char[][] dnaFlatToMatrixForm() throws BadDnaTestException {
            int n = dnaArray.length;
            char[][] matrix = new char[n][n];
            for (int i = 0; i < n; i++) {

                validateRowLength(i);

                for (int j = 0; j < n; j++) {
                    char character = dnaArray[i].charAt(j);

                    validateDnaBaseChars(character);
                    matrix[i][j] = character;
                }
            }
            return matrix;
        }
		
		private void validateRowLength(final int rowIndex) throws BadDnaTestException {
            if (dnaArray[rowIndex].length() != dnaArray.length) {
                throw new BadDnaTestException("The DNA Test must have the NxN form");
            }
        }
		
        private void validateDnaBaseChars(char character) throws BadDnaTestException {
            if (DNA_BASE_CHARS.indexOf(character) < 0) {
                throw new BadDnaTestException(
                        String.format("The character %s is not a representation of a DNA base", character));
            }
        }
	}
	
}
