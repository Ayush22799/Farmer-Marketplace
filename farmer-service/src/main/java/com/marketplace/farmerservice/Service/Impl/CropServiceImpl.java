package com.marketplace.farmerservice.Service.Impl;


import com.marketplace.farmerservice.DTO.AuthenticationRequest;
import com.marketplace.farmerservice.DTO.UserInfo;
import com.marketplace.farmerservice.Entity.CropEntity;
import com.marketplace.farmerservice.Exception.DetailsNotFoundException;
import com.marketplace.farmerservice.Exception.InvalidValueProvidedException;
import com.marketplace.farmerservice.Feign.UserServiceFeign;
import com.marketplace.farmerservice.Repository.CropRepository;
import com.marketplace.farmerservice.Service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    UserServiceFeign userServiceFeign;
    @Autowired
    CropRepository repository;
    @Override
    public CropEntity saveCropDetails(CropEntity crop) {
        ResponseEntity<UserInfo> user;
        try {
           user = userServiceFeign.getUserById(crop.getFarmerId());

       }
        catch (Exception e){
            throw new InvalidValueProvidedException("User Doesn't exisits");
        }
        if(!user.getBody().getUserType().equalsIgnoreCase("farmer")){
            throw new RuntimeException("Provided user is not a farmer");
        }
        return repository.save(crop);
    }

    @Override
    public List<CropEntity> getAllCrops() {
        List<CropEntity> allCrops = repository.findAll();
        if(allCrops.isEmpty()){
            throw new DetailsNotFoundException("No Crops available");
        }
        return allCrops;
    }

    @Override
    public CropEntity getCropByFarmerIdAndCropName(int userId, String cropName) {
       Optional<CropEntity> crop = repository.findAllCropByFarmerId(userId, cropName);
        if (crop.isEmpty()){
            throw new DetailsNotFoundException("No Crop Available for provided user");
        }
        return crop.get();
    }

    @Override
    public CropEntity getCropByCropId(int cropId) {
        Optional<CropEntity> crop = repository.findById(cropId);
        if (crop.isEmpty()){
            throw new DetailsNotFoundException("No Crop available for provided cropId");
        }
        return crop.get();
    }

    private String fetchToken(int userId){
        ResponseEntity<UserInfo> farmerDetails = userServiceFeign.getUserById(userId);

        AuthenticationRequest request = new AuthenticationRequest(farmerDetails.getBody().getUserName(),farmerDetails.getBody().getPassword());

        ResponseEntity<String> token = userServiceFeign.getToken(request);

        return token.getBody();
    }

}
