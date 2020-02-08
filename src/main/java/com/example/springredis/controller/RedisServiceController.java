package com.example.springredis.controller;


import com.example.springredis.model.Account;
import com.example.springredis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author z.Taghizadeh
 */
@RestController
@RequestMapping(path = "/redis")
public class RedisServiceController {

    @Autowired
    private CacheService service;


    @GetMapping(value = "/getAllAccount",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List getAllAccount() {

        return service.findAll();
    }

    @GetMapping(value = "/getById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Account findById(@PathVariable String id) {

        return service.getAccountById(id);
    }

    @PostMapping(value = "/saveOrUpdate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Account account) {

        service.saveOrUpdate(account);
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable String id) {

        service.deleteById(id);
    }

}
