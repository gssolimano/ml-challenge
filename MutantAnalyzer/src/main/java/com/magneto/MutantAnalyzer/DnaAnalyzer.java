package com.magneto.MutantAnalyzer;


public class DnaAnalyzer {
	
	private static final int SEQ_SIZE = 4;
    private static final int MIN_SEQ_QTY_TO_BE_MUTANT = 2;

    private final DnaTest dna;

    private int sequencesFound = 0;

    DnaAnalyzer(final DnaTest dna) {
        this.dna = dna;
    }
	
	boolean isMutant() {
		System.out.println("Longitud cadena: " + dna.chainSize());
		
		for (int i = 0; i < dna.chainSize() && !validateMinSequences(); i++) {
			validateRow(i);
			validateColumn(i);
		}
		
		if (!validateMinSequences()) {
			validateDiagonal();
		}
		
		return validateMinSequences();
	}

	private boolean validateMinSequences() {
        return sequencesFound >= MIN_SEQ_QTY_TO_BE_MUTANT;
    }
	
	private void validateRow(int x) {
		char currentChar = dna.valueAt(x, 0);
		char nextChar; 
		int  counter = 1;
		int  j = 0; 
		
		while (counter < 4 && j < dna.chainSize()) {
			nextChar = dna.valueAt(x,j); 
			if (nextChar == currentChar) {
				counter++;
			} else {
				counter = 1;
				currentChar = nextChar;
			}
			
			j++;
		} 
		
		if (counter == 4) {
			sequencesFound++;
		}
	}
	
	private void validateColumn(int y) {
		char currentChar = dna.valueAt(0, y);
		char nextChar; 
		int  counter = 1;
		int  i = 0; 
		
		while (counter < 4 && i < dna.chainSize()) {
			nextChar = dna.valueAt(i,y); 
			if (nextChar == currentChar) {
				counter++;
			} else {
				counter = 1;
				currentChar = nextChar;
			}
			
			i++;
		} 
		
		if (counter == 4) {
			sequencesFound++;
		}
	} 
	
	private void validateDiagonal() {
		char currentChar = dna.valueAt(0, 0);
		char nextChar; 
		int  counter = 1;
		int  i = 0 , j = 0; 
		
		while (counter < 4 && i < dna.chainSize() && j < dna.chainSize()) {
			nextChar = dna.valueAt(i,j); 
			if (nextChar == currentChar) {
				counter++;
			} else {
				counter = 1;
				currentChar = nextChar;
			}
			
			i++;
			j++;
		} 
		
		if (counter == 4) {
			sequencesFound++;
		}
	} 
	
	
	
}
