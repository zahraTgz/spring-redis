package com.example.springredis.repository;


import com.example.springredis.model.Account;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author z.Taghizadeh
 */
@Repository
public class RedisAccountRepository {

    private static final String KEY = "ACCOUNT";

    private HashOperations hashOperations;

    private RedisTemplate<String, Object> redisTemplate;

    public RedisAccountRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Account account) {
        hashOperations.put(KEY, account.getId().toString(), account);
    }

    public void update(Account account) {
        save(account);
    }

    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    public Account findById(String id) {
        return (Account) hashOperations.get(KEY, id);
    }

    public List findAll() {
        return hashOperations.values(KEY);
    }

    public Map<String, List<Account>> multiGetAccount(List<String> accountIds) {

        Map<String, List<Account>> accountMap = new HashMap<>();
        List<Object> accounts = hashOperations.multiGet(KEY, accountIds);

        for (int i = 0; i < accountIds.size(); i++) {
            accountMap.put(accountIds.get(i), (List<Account>) accounts.get(i));
        }
        return accountMap;
    }

}
