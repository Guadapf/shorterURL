package com.su.shorterUrl.controller;

import com.su.shorterUrl.model.entity.Url;
import com.su.shorterUrl.service.interfaces.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    @Autowired
    private IUrlService urlService;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<?> addUrl(@RequestBody String url){
        try{
            Url urlObtained = urlService.addUrl(url);
            return new ResponseEntity<>(urlObtained, HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> updateUrl(@PathVariable String shortCode, @RequestBody String url){
        try{
            Url response = urlService.updateUrl(shortCode, url);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e){
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
}
