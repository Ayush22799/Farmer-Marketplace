package com.marketplace.DealerService.FeignClients;


import com.marketplace.DealerService.Entity.CropEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "farmer-service", url = "http://localhost:8093/farmer")
public interface FarmerServiceFeign {
    @GetMapping("/getCropDetails/farmer/{userId}/cropName/{cropName}")
    ResponseEntity<CropEntity> getCropByFarmerIdAndCropName(@PathVariable("userId") int userId, @PathVariable("cropName") String cropName);
}
