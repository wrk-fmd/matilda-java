package at.wrk.controller;

import at.wrk.web.MaterialController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialControllerTest {
	@Autowired
	private MaterialController materialController;

	//Simple test whether MaterialController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(materialController).isNotNull();
	}
}
