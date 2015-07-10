package com.endockin.patron.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class FleetCache {

  private final Map<String, List<String>> cache = new ConcurrentHashMap<>();

  public synchronized List<String> getAllFleetIdsForUser(String userEmail) {
    List<String> fleetIds = cache.get(userEmail);
    return fleetIds != null ? fleetIds : new ArrayList<>();
  }

  public synchronized void cacheFleetForUser(String userEmail, String fleetId) {
    List<String> fleetListForUser = cache.get(userEmail);

    if (fleetListForUser == null) {
      fleetListForUser = new ArrayList<>();
      cache.put(userEmail, fleetListForUser);
    }

    fleetListForUser.add(fleetId);
  }
}
