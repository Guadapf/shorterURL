package com.su.shorterUrl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.su.shorterUrl.model.dto.UrlRequest;
import com.su.shorterUrl.model.entity.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UrlControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IUrlService urlService;

    @InjectMocks
    private UrlController urlController;

    private ObjectMapper objectMapper;
    private String originalUrl;
    private String shortCode;
    private Url newUrl;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(urlController).build();
        objectMapper = new ObjectMapper();

        originalUrl = "https://www.youtube.com/watch?v=klV9hheS86o&t=4393s";
        shortCode = "test12";

        newUrl = new Url();
        newUrl.setIdUrl(1);
        newUrl.setUrl(originalUrl);
        newUrl.setShortCode(shortCode);
        newUrl.setCreatedAt(LocalDateTime.now());
        newUrl.setUpdatedAt(LocalDateTime.now());
    }
    @Test
    public void queSeAgregueUnUrl() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl(originalUrl);

        when(urlService.addUrl(originalUrl)).thenReturn(newUrl);

        mockMvc.perform(post("/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUrl").value(1))
                .andExpect(jsonPath("$.url").value(originalUrl))
                .andExpect(jsonPath("$.shortCode").value(shortCode));

        verify(urlService, times(1)).addUrl(originalUrl);
    }

    @Test
    public void queAlIngresarUnaUrlNulaLanceException() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl(null);
        when(urlService.addUrl(null)).thenThrow(new Exception("La url ingresada es inválida."));

        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(urlRequest)))
                .andExpect(status().isBadRequest());

        verify(urlService, times(1)).addUrl(null);
    }

    @Test
    public void queSePuedaObtenerUnUrl() throws Exception{
        when(urlService.getUrl(shortCode)).thenReturn(newUrl);

        mockMvc.perform(get("/shorten/{shortCode}", shortCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUrl").value(1))
                .andExpect(jsonPath("$.url").value(originalUrl))
                .andExpect(jsonPath("$.shortCode").value(shortCode));

        verify(urlService, times(1)).getUrl(shortCode);
    }

    @Test
    public void queAlIntentarObtenerUnUrlConSHortCodeInvalidoLanceException() throws Exception {
        String invalidShortCode = "invalidShortCode";
        when(urlService.getUrl(invalidShortCode)).thenThrow(new Exception("El url al que intentas acceder no existe."));

        mockMvc.perform(get("/shorten/{shortCode}", invalidShortCode))
                .andExpect(status().isNotFound());

        verify(urlService, times(1)).getUrl(invalidShortCode);
    }

    @Test
    public void queSePuedaActualizarUnaUrl() throws Exception{
        String urlUpdate = "www./newurl.com/";
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl(urlUpdate);
        newUrl.setUrl(urlUpdate);

        when(urlService.updateUrl(shortCode, urlUpdate)).thenReturn(newUrl);

        mockMvc.perform(put("/shorten/{shortCode}", shortCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(urlRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUrl").value(1))
                .andExpect(jsonPath("$.url").value(urlUpdate))
                .andExpect(jsonPath("$.shortCode").value(shortCode));

        verify(urlService, times(1)).updateUrl(shortCode, urlUpdate);
    }

    @Test
    public void queSePuedaEliminarUnaUrlExistente() throws Exception {
        when(urlService.getUrl(shortCode)).thenReturn(newUrl);
        doNothing().when(urlService).delete(newUrl);

        mockMvc.perform(delete("/shorten/{shortCode}", shortCode))
                .andExpect(status().isNoContent());

        verify(urlService, times(1)).delete(newUrl);
    }

    @Test
    public void queAlEliminarUnUrlConShortCodeInvalidoLanceException() throws Exception {
        String invalidShortCode = "invalidShortCode";
        when(urlService.getUrl(invalidShortCode)).thenThrow(new Exception("El url al que intentas acceder no existe."));

        mockMvc.perform(delete("/shorten/{shortCode}", invalidShortCode))
                .andExpect(status().isNotFound());

        verify(urlService, times(1)).getUrl(invalidShortCode);
    }
}
