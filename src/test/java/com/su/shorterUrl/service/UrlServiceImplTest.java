package com.su.shorterUrl.service;

import com.su.shorterUrl.model.entity.Url;
import com.su.shorterUrl.model.repository.interfaces.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;
    @InjectMocks
    private UrlServiceImpl urlService;

    private Url newUrl;
    private String url;
    private String shortCode;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        url = "https://github.com/Guadapf/";
        shortCode = "Abc123";
        newUrl = new Url();
        newUrl.setUrl(url);
        newUrl.setShortCode(shortCode);
        newUrl.setCreatedAt(LocalDateTime.now());
        newUrl.setUpdatedAt(LocalDateTime.now());
    }
    @Test
    public void queSePuedaAgregarUnUrlSatisfactoriamente() throws Exception {

        when(urlRepository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRepository.save(any(Url.class))).thenReturn(newUrl);

        Url result = urlService.addUrl(url);

        assertNotNull(result);
        assertEquals(url, result.getUrl());
        assertEquals(shortCode.length(), result.getShortCode().length());
        verify(urlRepository, times(1)).existsByShortCode(anyString());
        verify(urlRepository, times(1)).save(any(Url.class));
    }

    @Test
    public void queSeObtengaLaUrlMedianteElShortCode() throws Exception {

        when(urlRepository.findByShortCode(shortCode)).thenReturn(newUrl);

        Url result = urlService.getUrl(shortCode);

        assertNotNull(result);
        assertEquals(url, result.getUrl());
        assertEquals(newUrl, result);
    }

    @Test
    public void queAlObtenerUnUrlYElShortCodeNoExistaTiraException() throws Exception{
        assertThrows(Exception.class, () -> {
            urlService.getUrl("unvalidShortCode");
        });
    }

    @Test
    public void queSePuedaActualizarElUrl() throws Exception {
        String urlUpdate = "www.newUrl.com/";

        when(urlRepository.findByShortCode(shortCode)).thenReturn(newUrl);
        when(urlRepository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRepository.save(any(Url.class))).thenReturn(newUrl);

        Url result = urlService.updateUrl(shortCode, urlUpdate);

        assertEquals(urlUpdate, result.getUrl());
        assertEquals(result.getUpdatedAt(), newUrl.getUpdatedAt());
    }

    @Test
    public void queAlQuererActualizarConUnShortCodeNullLanceException() throws Exception{
        assertThrows(Exception.class, () -> {
            urlService.updateUrl(null, "www.example.com/");
        });
    }

    @Test
    public void queSeElimineUnUrl(){
        urlService.delete(newUrl);
        verify(urlRepository, times(1)).delete(newUrl);
    }
}
