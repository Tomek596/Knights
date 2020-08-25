package com.tomq.kursspring.config;

import com.tomq.kursspring.domain.repository.DBKnightRepository;
import com.tomq.kursspring.domain.repository.InMemoryRepository;
import com.tomq.kursspring.domain.repository.KnightRepository;
import org.springframework.context.annotation.*;

@Configuration
public class MainConfig {


    @Bean(name="inMemoryKnighRepository")
    @Profile("dev")
    public KnightRepository createInMemoryRepo() {
        KnightRepository repo = new InMemoryRepository();
        return repo;
    }

    @Bean(name="DBKnightRepository")
    @Profile("prod")
    public KnightRepository createDBRepo() {
        KnightRepository repo = new DBKnightRepository();
        return repo;
    }

}
