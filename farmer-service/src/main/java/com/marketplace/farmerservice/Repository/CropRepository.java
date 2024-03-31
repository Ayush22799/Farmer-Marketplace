package com.marketplace.farmerservice.Repository;

import com.marketplace.farmerservice.Entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<CropEntity, Integer> {


    @Query(value = "select * from crop_details c where c.farmer_id = ?1 and c.crop_name = ?2", nativeQuery=true)
    Optional<CropEntity> findAllCropByFarmerId(int userId, String cropName);
}
