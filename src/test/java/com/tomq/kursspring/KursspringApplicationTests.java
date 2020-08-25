package com.tomq.kursspring;

import com.tomq.kursspring.domain.repository.InMemoryRepository;
import com.tomq.kursspring.domain.Knight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KursspringApplicationTests {

	@Autowired
    Knight knight;

	@Autowired
    InMemoryRepository inMemoryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCastle() {
		String except = "Znajduje sie tu zamek o nazwie InMemoryRepository Black. Zamieszkaly przez rycerza Rycerz o imieniu Lancelot(29). Ma za zadanie: Uratuj ksiezniczke";
		assertEquals(except, inMemoryRepository.toString());
	}

}
