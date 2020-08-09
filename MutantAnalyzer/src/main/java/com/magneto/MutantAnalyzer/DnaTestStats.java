package com.magneto.MutantAnalyzer;

public interface DnaTestStats {

	void insertDnaTestResult(String dnaTest, boolean isMutant);
	
	MutantStat getDnaTestRatio();

}
