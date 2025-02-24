package com.su.shorterUrl.service;

import com.su.shorterUrl.model.entity.Url;
import com.su.shorterUrl.model.repository.interfaces.UrlRepository;
import com.su.shorterUrl.service.interfaces.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UrlServiceImpl implements IUrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Transactional
    @Override
    public Url addUrl(String url) throws Exception {
        if(url == null || url == ""){
            throw new Exception("La url ingresada es inválida.");
        }
        Url newUrl = new Url();
        String shortCode;

        do{
            shortCode = generateRandomCode(6);
        }while(urlRepository.existsByShortCode(shortCode));

        newUrl.setUrl(url);
        newUrl.setShortCode(shortCode);
        newUrl.setCreatedAt(LocalDateTime.now());
        newUrl.setUpdatedAt(LocalDateTime.now());
        return urlRepository.save(newUrl);
    }

    private String generateRandomCode(int length){
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder chain = new StringBuilder();

        for(int i=0;i<length;i++){
            int randomIndex = ThreadLocalRandom.current().nextInt(0, banco.length());
            char randomChar = banco.charAt(randomIndex);
            chain.append(randomChar);
        }
        return chain.toString();
    }

    @Transactional(readOnly = true)
    @Override
    public Url getUrl(String shortCode) throws Exception {
        Url urlObtained = urlRepository.findByShortCode(shortCode);
        if(urlObtained == null){
            throw new Exception("El url al que intentas acceder no existe.");
        }
        return urlObtained;
    }

    @Transactional
    @Override
    public Url updateUrl(String shortCode, String url) throws Exception {
        Url urlObtained = urlRepository.findByShortCode(shortCode);
        if(urlObtained == null){
            throw new Exception("El url que intentas actualizar no existe.");
        }
        urlObtained.setUrl(url);
        urlObtained.setUpdatedAt(LocalDateTime.now());
        return urlRepository.save(urlObtained);
    }

    @Transactional
    @Override
    public void delete(Url urlObtained) {
        urlRepository.delete(urlObtained);
    }
}
