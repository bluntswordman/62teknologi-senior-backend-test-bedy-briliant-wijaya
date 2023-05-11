package com.bedevenamdua.controllers;

import com.bedevenamdua.entity.Business;
import com.bedevenamdua.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/businesses")
@AllArgsConstructor
public class BusinessController {
    private BusinessService businessService;

    @GetMapping
    public ResponseEntity<Object> getAllBusinesses() {
        HashMap<String, Object> data = (HashMap<String, Object>) businessService.getAllBusinesses();
        if (data.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyMap());
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "{businessId}")
    public ResponseEntity<Object> getBusinessById(@PathVariable UUID businessId) {
        Business business = businessService.getBusinessById(businessId);
        if (business == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Business not found"));
        }
        return ResponseEntity.ok(business);
    }

    @PostMapping
    public ResponseEntity<Business> addNewBusiness(@Validated @RequestBody Business business) {
        return ResponseEntity.ok(businessService.addNewBusiness(business));
    }

    @PutMapping(path = "{businessId}")
    public ResponseEntity<Object> updateBusiness(@PathVariable UUID businessId, @Validated @RequestBody Business business) {
        Business businessIsExist = businessService.getBusinessById(businessId);
        if (businessIsExist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Business not found"));
        }
        return ResponseEntity.ok(businessService.updateBusiness(businessId, business));
    }

    @DeleteMapping(path = "{businessId}")
    public ResponseEntity<Object> deleteBusiness(@PathVariable UUID businessId) {
        Business businessIsExist = businessService.getBusinessById(businessId);
        if (businessIsExist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Business not found"));
        }
        businessService.deleteBusiness(businessId);
        return ResponseEntity.ok(Map.of("message", "Business deleted"));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Object> searchBusiness(
            @RequestParam(value = "latitude", required = false, defaultValue = "0.0") Double latitude,
            @RequestParam(value = "longitude", required = false, defaultValue = "0.0") Double longitude,
            @RequestParam(value = "price", required = false, defaultValue = "0") Integer price,
            @RequestParam(value = "name", required = false, defaultValue = "No") String name,
            @RequestParam(value = "order_by", required = false, defaultValue = "distance") String orderBy,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "limit", defaultValue = "10") int size
    ) {
        if (latitude == null && longitude == null && price == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Please provide at least one parameter"));
        }
        Pageable pageable = PageRequest.of(0, size);
        HashMap<String, Object> data = (HashMap<String, Object>) businessService.searchBusinesses(latitude, longitude, price, name, orderBy, direction, pageable);
        if (data.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyMap());
        }
        return ResponseEntity.ok(data);
    }
}
