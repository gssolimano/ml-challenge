package com.magneto.MutantAnalyzer;

import org.springframework.stereotype.Component;

@Component
class MutantAnalyzerProcess {
	
	boolean isMutant(final DnaTest dnaTest) {
		return new DnaAnalyzer(dnaTest).isMutant();
	}
	
	boolean isMutant(final String[] dnaArray) throws BadDnaTestException {
		
		DnaTest dnaTest = DnaTest.Builder.newBuilder().withDna(dnaArray).build();
		
		return isMutant(dnaTest);
	}

}
