package com.endockin.patron.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class FleetCache {

  private final Map<Long, List<String>> cache = new ConcurrentHashMap<>();

  public synchronized List<String> getAllFleetIdsForUser(Long userId) {
    List<String> fleetIds = cache.get(userId);
    return fleetIds != null ? fleetIds : new ArrayList<>();
  }

  public synchronized void cacheFleetForUser(Long userId, String fleetId) {
    List<String> fleetListForUser = cache.get(userId);

    if (fleetListForUser == null) {
      fleetListForUser = new ArrayList<>();
      cache.put(userId, fleetListForUser);
    }

    fleetListForUser.add(fleetId);
  }
}
