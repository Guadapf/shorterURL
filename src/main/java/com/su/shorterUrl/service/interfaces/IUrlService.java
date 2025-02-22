package com.su.shorterUrl.service.interfaces;

import com.su.shorterUrl.model.entity.Url;

public interface IUrlService {

    Url addUrl(Url url);
    Url getUrl(String shortCode);
    Url updateUrl(String shortCode, String url);
    void delete(String shortCode);

}
