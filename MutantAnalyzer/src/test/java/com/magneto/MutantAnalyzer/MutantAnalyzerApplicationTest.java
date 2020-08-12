package com.magneto.MutantAnalyzer;


import com.magneto.MutantAnalyzer.MutantAnalyzerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantAnalyzerApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void contextLoads() {
		assertThat(applicationContext.getBean("mutantAnalyzerService")).isInstanceOf(MutantAnalyzerService.class);
	}

}
