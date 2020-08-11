package com.magneto.MutantAnalyzer.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.magneto.MutantAnalyzer.MutantStat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class DnaTestStastDBTest {

	private static final String INSERT = "INSERT INTO magneto.dna_test_results (DNA_TEST, IS_MUTANT) VALUES (?,?)";
	private static final String SELECT = "SELECT mutant.count_mutant_dna, human.count_humna_dna, round(mutant.count_mutant_dna / (mutant.count_mutant_dna + human.count_humna_dna),2) as ratio\r\n" + 
										 "FROM (SELECT 1 AS id, COUNT(*) AS count_mutant_dna FROM magneto.dna_test_results WHERE is_mutant = 1) AS mutant,\r\n" + 
										 "	   (SELECT 1 AS id, COUNT(*) AS count_humna_dna FROM magneto.dna_test_results WHERE is_mutant = 0) AS human\r\n" + 
										 "WHERE mutant.id = human.id;";
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DnaTestStastDB dnaTestStatsDB;

    @Test
    public void insertDnaTestResult() {
        dnaTestStatsDB.insertDnaTestResult("SAMPLE", true);

        verify(jdbcTemplate).update(eq(INSERT), eq("SAMPLE"), eq(true));
    }

    @Test
    public void getDnaTestRatio() {
        MutantStat result = dnaTestStatsDB.getDnaTestRatio();

        verify(jdbcTemplate).query(eq(SELECT), any(RowCallbackHandler.class));
        assertThat(result.getCount_mutant_dna()).isEqualTo(0);
        assertThat(result.getCount_human_dna()).isEqualTo(0);
        assertThat(result.getRatio()).isEqualTo(new BigDecimal(0));
    }
}
