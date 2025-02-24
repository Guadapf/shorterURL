package com.su.shorterUrl.model.repository.interfaces;

import com.su.shorterUrl.model.entity.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
}
