package com.endockin.patron.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class FleetCache {
    
    private final Map<String,List<String>> cache = new ConcurrentHashMap<>();
    
    public synchronized List<String> getAllFleetIdsForUser(String userId){
        return cache.get(userId);
    }
    
    public synchronized void cacheFleetForUser(String userId, String fleetId){
        List<String> fleetListForUser = cache.get(userId);
        
        if (fleetListForUser == null){
            fleetListForUser = new ArrayList<>();
            cache.put(userId, fleetListForUser);
        }
        
        fleetListForUser.add(fleetId);
    }
}
