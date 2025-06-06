package com.su.shorterUrl.controller;

import com.su.shorterUrl.model.dto.UrlRequest;
import com.su.shorterUrl.model.entity.Url;
import com.su.shorterUrl.service.interfaces.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    @Autowired
    private IUrlService urlService;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<?> addUrl(@RequestBody UrlRequest url){
        try{
            Url urlObtained = urlService.addUrl(url.getUrl());
            return new ResponseEntity<>(urlObtained, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shortCode}")
    @ResponseStatus
    public ResponseEntity<?> getUrl(@PathVariable String shortCode){
        try {
            Url urlObtained = urlService.getUrl(shortCode);
            return new ResponseEntity<>(urlObtained, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{shortCode}")
    @ResponseStatus
    public ResponseEntity<?> updateUrl(@PathVariable String shortCode, @RequestBody UrlRequest url){
        try{
            Url response = urlService.updateUrl(shortCode, url.getUrl());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<?> deleteUrl(@PathVariable String shortCode){
        try{
            Url urlObtained = urlService.getUrl(shortCode);
            urlService.delete(urlObtained);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String shortCode){
        try {
            Url urlObtained = urlService.getUrl(shortCode);
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(urlObtained.getUrl()))
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
