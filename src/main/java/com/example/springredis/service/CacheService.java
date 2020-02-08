package com.example.springredis.service;


import com.example.springredis.model.Account;
import com.example.springredis.repository.RedisAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author z.Taghizadeh
 */
@Service
public class CacheService {

    @Autowired
    private RedisAccountRepository redisAccountRepository;


    @Cacheable(value = "AccountCache", key = "#id")
    public Account getAccountById(String id) {

        System.out.println("In getAccountById cache Component..");
        return redisAccountRepository.findById(id);
    }


    @Cacheable(value = "AccountCache", unless = "#result.size() == 0")
    public List findAll() {

        System.out.println("In findAllAccount cache Component..");
        return redisAccountRepository.findAll();
    }


    @Caching(evict = {
            @CacheEvict(value = "AccountCache", allEntries = true)})
    public void saveOrUpdate(Account account) {

        System.out.println("In addAccount cache component..");
        redisAccountRepository.save(account);
    }


    @CacheEvict(value = "AccountCache", allEntries = true)
    public void deleteById(String id) {

        System.out.println("In deleteAccount cache Component..");
        redisAccountRepository.delete(id);
    }

}
