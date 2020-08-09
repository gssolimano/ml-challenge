package com.magneto.MutantAnalyzer.datasource;

import java.math.BigDecimal;
import java.util.Map;

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
	private static final String GET_STATS = "SELECT mutant.count_mutant_dna, human.count_humna_dna, round(mutant.count_mutant_dna / (mutant.count_mutant_dna + human.count_humna_dna),2) as ratio\r\n" + 
											"FROM (SELECT 1 AS id, COUNT(*) AS count_mutant_dna FROM magneto.dna_test_results WHERE is_mutant = 1) AS mutant,\r\n" + 
											"	 (SELECT 1 AS id, COUNT(*) AS count_humna_dna FROM magneto.dna_test_results WHERE is_mutant = 0) AS human\r\n" + 
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
		long count_mutant_dna;
		long count_human_dna;
		BigDecimal ratio;
		
		Map<String, Object> result = (Map<String, Object>) jdbcTemplate.queryForMap(GET_STATS);
		count_mutant_dna = (long) result.get("count_mutant_dna");
		count_human_dna = (long) result.get("count_humna_dna");
		ratio = (BigDecimal) result.get("ratio");
		
		return new MutantStat(count_mutant_dna, count_human_dna, ratio);                
	}
}
