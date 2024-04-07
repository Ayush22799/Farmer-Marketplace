package com.marketplace.farmerservice.Controller;

import com.marketplace.farmerservice.Entity.CropEntity;
import com.marketplace.farmerservice.Service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmer")
@CrossOrigin
public class Controller {
    @Autowired
    CropService cropService;

    @PostMapping("/saveCrop")
    public ResponseEntity<CropEntity> SaveCropDetails(@RequestBody CropEntity crop){
        return ResponseEntity.ok(cropService.saveCropDetails(crop));
    }

    @GetMapping("/getAllCrop")
    public ResponseEntity<List<CropEntity>> getAllCrop(){
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @GetMapping("/getCropDetails/farmer/{userId}/cropName/{cropName}")
    public ResponseEntity<CropEntity> getCropByFarmerIdAndCropName(@PathVariable("userId") int userId, @PathVariable("cropName") String cropName){
        return ResponseEntity.ok(cropService.getCropByFarmerIdAndCropName(userId,cropName));
    }

    @GetMapping("/getCrop/{cropId}")
    public ResponseEntity<CropEntity> getCropByCropId(@PathVariable("cropId") int cropId){
        return ResponseEntity.ok(cropService.getCropByCropId(cropId));
    }

}
