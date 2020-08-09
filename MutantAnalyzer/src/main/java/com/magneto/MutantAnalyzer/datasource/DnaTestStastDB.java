package com.magneto.MutantAnalyzer.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.magneto.MutantAnalyzer.DnaTestStats;
import com.magneto.MutantAnalyzer.MutantStat;

@Repository
public class DnaTestStastDB implements DnaTestStats {
	
	private static final String INSERT_DNA_TEST = "INSERT INTO magneto.dna_test_results (DNA_TEST, IS_MUTANT) VALUES (?,?)";
	private static final String GET_STATS = "SELECT mutant.mutant_count_dna, human.human_count_dna, round(mutant.mutant_count_dna / (mutant.mutant_count_dna + human.human_count_dna),2) as ratio\r\n" + 
											"FROM (SELECT 1 AS id, COUNT(*) AS mutant_count_dna FROM magneto.dna_test_results WHERE is_mutant = 1) AS mutant,\r\n" + 
											"	 (SELECT 1 AS id, COUNT(*) AS human_count_dna FROM magneto.dna_test_results WHERE is_mutant = 0) AS human\r\n" + 
											"WHERE mutant.id = human.id;";
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	DnaTestStastDB(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	@Async 
	public void insertDnaTestResult(final String dnaTest, final boolean isMutant) {
		jdbcTemplate.update(INSERT_DNA_TEST, dnaTest, isMutant);
	}
	
	@Override 
	public MutantStat getDnaTestRatio() {
		final int count_mutant_dna = 2;
		final int count_human_dna = 1;
		final double ratio = 0.66;
		
		return new MutantStat(count_mutant_dna, count_human_dna, ratio);        
        
	}
}
