package com.magneto.MutantAnalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantAnalyzerService {
	
	private final MutantAnalyzerProcess mutantAnalyzeProcess;
    //private final StatsRepository statsRepository;

    @Autowired
    //MutantService(final MutabilityAnalysisProcessor mutabilityAnalysisProcessor, StatsRepository statsRepository) {
    MutantAnalyzerService(final MutantAnalyzerProcess mutantAnalyzeProcess) {
        this.mutantAnalyzeProcess = mutantAnalyzeProcess;
        //this.statsRepository = statsRepository;
    }

	
	public boolean isMutant(DnaTest dnaTest) {
		boolean isMutant = mutantAnalyzeProcess.isMutant(dnaTest);
		
		return isMutant;
	}
	

}
