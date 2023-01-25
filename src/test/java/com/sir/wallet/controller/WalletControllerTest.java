package com.sir.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    WalletServiceImpl walletService;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private String getRootUrl() {
        return "http://localhost:" + port + "/api/wallet";
    }
    private String tokenRequest;

    @Test
    void getWalletId() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenRequest);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Wallet> responseEntity = restTemplate.exchange(getRootUrl() + "/wallets/3", HttpMethod.GET,
                entity, Wallet.class);
        assertNotNull(responseEntity);
        // assertEquals(personne.getNom(), "Abdel");

    }




    private String asJsonString(Wallet boubs) {
        try {
            return new ObjectMapper().writeValueAsString(boubs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void deleteWallet() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v2/wallets/2")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        System.out.println(contentAsString);
        assertNotNull(contentAsString);
    }

    @Test
    void saveWallet() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenRequest);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Wallet> responseEntity = restTemplate.exchange(getRootUrl() + "/wallets/1", HttpMethod.GET,
                entity, Wallet.class);

        assertNotNull(responseEntity);
    }
    @Test
    void AllWallet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/wallets")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}