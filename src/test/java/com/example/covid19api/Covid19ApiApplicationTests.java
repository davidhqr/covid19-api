package com.example.covid19api;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest
class Covid19ApiApplicationTests {

    @Mock
    private HelloContainer helloContainer;

	@Test
	public void getHello() {
	    when(helloContainer.index()).thenReturn("Greetings from Meenie");

	    String result = helloContainer.index();

	    assertThat(result, is("Greetings from Meenie"));
	}

}
