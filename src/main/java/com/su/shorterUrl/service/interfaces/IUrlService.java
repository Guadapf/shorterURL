package com.su.shorterUrl.service.interfaces;

import com.su.shorterUrl.model.entity.Url;

public interface IUrlService {

    Url addUrl(String url) throws Exception;
    Url getUrl(String shortCode) throws Exception;
    Url updateUrl(String shortCode, String url) throws Exception;
    void delete(Url urlObtained);

}