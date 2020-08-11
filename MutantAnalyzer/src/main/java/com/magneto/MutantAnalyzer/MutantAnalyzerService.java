package com.magneto.MutantAnalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MutantAnalyzerService {
	
	private final MutantAnalyzerProcess mutantAnalyzeProcess;
    private final DnaTestStats dnaTestStats;	

    @Autowired
    MutantAnalyzerService(final MutantAnalyzerProcess mutantAnalyzeProcess, final DnaTestStats dnaTestStats) {
        this.mutantAnalyzeProcess = mutantAnalyzeProcess;
        this.dnaTestStats = dnaTestStats;
    }

	
	public boolean isMutant(DnaTest dnaTest) {
		
		boolean isMutant = mutantAnalyzeProcess.isMutant(dnaTest);
		dnaTestStats.insertDnaTestResult(dnaTest.flattenedMatrix(), isMutant);
		
		return isMutant;
	}
	
	@Cacheable(value = "statCache", key = "#root.methodName")
	public MutantStat getMutantsRatio() {
        return dnaTestStats.getDnaTestRatio();
    }
	

}
