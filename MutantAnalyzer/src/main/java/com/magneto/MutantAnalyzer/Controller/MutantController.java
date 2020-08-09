package com.magneto.MutantAnalyzer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magneto.MutantAnalyzer.DnaTest;
import com.magneto.MutantAnalyzer.MutantAnalyzerService;

@SpringBootApplication
@RequestMapping("/mutant")
public class MutantController {
	
    private final MutantAnalyzerService mutantAnalyzerService;

    @Autowired
    MutantController(final MutantAnalyzerService mutantAnalyzerService) {
        this.mutantAnalyzerService = mutantAnalyzerService;
    }
	
	@PostMapping
	ResponseEntity<?> isMutant(@RequestBody DnaTest request) {
		System.out.println(request);
		
		boolean isMutant = mutantAnalyzerService.isMutant(request);
		
		if (isMutant) {
			System.out.println("Es mutante");
			return new ResponseEntity(HttpStatus.OK);
		} else {
			System.out.println("Es humano");
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		
		
	}

}
