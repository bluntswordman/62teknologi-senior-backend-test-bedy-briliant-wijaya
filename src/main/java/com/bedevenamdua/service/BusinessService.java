package com.bedevenamdua.service;

import com.bedevenamdua.entity.Business;
import com.bedevenamdua.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;

    public Map<String, Object> getAllBusinesses() {
        Iterable<Business> businesses = businessRepository.findAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("businesses", businesses);
        data.put("total", businesses.spliterator().getExactSizeIfKnown());
        return data;
    }

    public Business getBusinessById(UUID businessId) {
        Optional<Business> business = businessRepository.findById(businessId);
        return business.orElse(null);
    }

    public Business addNewBusiness(Business business) {
        return businessRepository.save(business);
    }

    public Business updateBusiness(UUID businessId, Business updatedBusiness) {
        Business existingBusiness = getBusinessById(businessId);
        if (existingBusiness == null) {
            return null;
        }
        updatedBusiness.setId(businessId);
        return businessRepository.save(updatedBusiness);
    }

    public void deleteBusiness(UUID businessId) {
        businessRepository.deleteById(businessId);
    }

    public Map<String, Object> searchBusinesses(double latitude, double longitude, int price, String name, String orderBy, String direction, Pageable pageable) {
        Sort sort = null;
        if (orderBy != null && !orderBy.isEmpty()) {
            if (direction != null && direction.equalsIgnoreCase("desc")) {
                sort = Sort.by(orderBy).descending();
            } else {
                sort = Sort.by(orderBy).ascending();
            }
        }
        if (sort != null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        Iterable<Business> businesses = businessRepository.searchBusiness(latitude, longitude, price, name, orderBy, pageable);
        HashMap<String, Object> data = new HashMap<>();
        data.put("businesses", businesses);
        data.put("total", businesses.spliterator().getExactSizeIfKnown());
        return data;
    }
}
