package com.magneto.MutantAnalyzer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadDnaTestException extends Exception {
	
	BadDnaTestException(final String message) {
        super(message);
    }
}
