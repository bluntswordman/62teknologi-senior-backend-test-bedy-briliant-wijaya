package com.bedevenamdua.repository;

import com.bedevenamdua.entity.Business;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Business, UUID> {
    @Query("SELECT b FROM Business b WHERE (:latitude IS NULL OR b.coordinate.latitude = :latitude) OR (:longitude IS NULL OR b.coordinate.longitude = :longitude) OR (:price IS NULL OR b.price = :price) OR (:name IS NULL OR b.name = :name) ORDER BY :orderBy")
    List<Business> searchBusiness(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("price") Integer price, @Param("name") String name, @Param("orderBy") String orderBy, Pageable pageable);
}
