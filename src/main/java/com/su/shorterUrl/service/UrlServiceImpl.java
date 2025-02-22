package com.su.shorterUrl.service;

import com.su.shorterUrl.model.entity.Url;
import com.su.shorterUrl.model.repository.interfaces.UrlRepository;
import com.su.shorterUrl.service.interfaces.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements IUrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Transactional
    @Override
    public Url addUrl(Url url) {
        url.setCreatedAt(LocalDateTime.now());
        url.setUpdatedAt(LocalDateTime.now());
        return urlRepository.save(url);
    }

    @Transactional(readOnly = true)
    @Override
    public Url getUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode);
    }

    @Transactional
    @Override
    public Url updateUrl(String shortCode, String url) {
        Url urlObtained = urlRepository.findByShortCode(shortCode);
        urlObtained.setUrl(url);
        urlObtained.setUpdatedAt(LocalDateTime.now());
        return urlRepository.save(urlObtained);
    }

    @Transactional
    @Override
    public void delete(String shortCode) {
        Url urlObtained = urlRepository.findByShortCode(shortCode);
        urlRepository.delete(urlObtained);
    }
}
