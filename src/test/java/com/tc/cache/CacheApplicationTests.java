package com.tc.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CacheApplicationTests {
    @Autowired
    protected MockMvc mockMvc;
    @Test
    void contextLoads() {
    }

    @Test
    void cachePut() throws Exception {
        mockMvc.perform(put("/cache/")
            .contentType("application/json")
            .content("{\"id\":1,\"data\":\"test\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void cacheIdGet() throws Exception {
        mockMvc.perform(get("/cache/1"))
            .andExpect(content().string("test"))
            .andExpect(status().isOk());
    }


}
