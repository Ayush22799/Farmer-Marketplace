package com.marketplace.farmerservice.Service;

import com.marketplace.farmerservice.Entity.CropEntity;

import java.util.List;

public interface CropService {
 CropEntity saveCropDetails(CropEntity crop);
 List<CropEntity> getAllCrops();

CropEntity getCropByFarmerIdAndCropName(int userId, String cropName);

    CropEntity getCropByCropId(int cropId);
}
