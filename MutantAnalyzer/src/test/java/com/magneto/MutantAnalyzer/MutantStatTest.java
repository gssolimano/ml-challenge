package com.magneto.MutantAnalyzer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MutantStatTest {

	@Test
	public void newMutantStat() {
		
		MutantStat mutantStat = new MutantStat(1,2,new BigDecimal(0.33));
		
		assertThat(mutantStat.getCount_human_dna()).isEqualTo(2);
        assertThat(mutantStat.getCount_mutant_dna()).isEqualTo(1);
        assertThat(mutantStat.getRatio()).isEqualTo(0.33);
	}
	
	@Test
	public void newMutantStatBySet() {
		
		MutantStat mutantStat = new MutantStat(0,0,new BigDecimal(0.0));
		mutantStat.setCount_human_dna(2);
		mutantStat.setCount_mutant_dna(1);
		mutantStat.setRatio(new BigDecimal(0.3));
		
		assertThat(mutantStat.getCount_human_dna()).isEqualTo(2);
        assertThat(mutantStat.getCount_mutant_dna()).isEqualTo(1);
        assertThat(mutantStat.getRatio()).isEqualTo(0.33);
	}

}
