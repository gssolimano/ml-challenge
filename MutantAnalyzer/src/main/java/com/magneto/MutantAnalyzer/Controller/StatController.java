package com.magneto.MutantAnalyzer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magneto.MutantAnalyzer.DnaTest;
import com.magneto.MutantAnalyzer.MutantAnalyzerService;
import com.magneto.MutantAnalyzer.MutantStat;

@SpringBootApplication
@RequestMapping("/stat")
public class StatController {
	
	private final MutantAnalyzerService mutantAnalyzerService;

    @Autowired
    StatController(final MutantAnalyzerService mutantAnalyzerService) {
        this.mutantAnalyzerService = mutantAnalyzerService;
    }
	
	@GetMapping
	ResponseEntity<MutantStat> stats() {
        return ResponseEntity.ok(mutantAnalyzerService.getMutantsRatio());
    }
}
