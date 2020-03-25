package com.wang.cloud.situation.es;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
class EsApplicationTests {
/*

    @Value("${author}")
    private String author;
*/

    @Value("${cityname}")
    private String cityName;

    @Test
    void contextLoads() {
        assertEquals("beijing",cityName);

    }

}
