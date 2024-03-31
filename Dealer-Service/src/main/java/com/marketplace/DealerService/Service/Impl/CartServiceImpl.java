package com.marketplace.DealerService.Service.Impl;


import com.marketplace.DealerService.Entity.CropEntity;
import com.marketplace.DealerService.Entity.DealerCart;
import com.marketplace.DealerService.Entity.UserInfo;
import com.marketplace.DealerService.Exception.InvalidValueProvidedException;
import com.marketplace.DealerService.FeignClients.FarmerServiceFeign;
import com.marketplace.DealerService.FeignClients.LoginServiceClient;
import com.marketplace.DealerService.Repository.DealerCartRepo;
import com.marketplace.DealerService.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    DealerCartRepo repo;
    @Autowired
    LoginServiceClient loginServiceClient;
    @Autowired
    FarmerServiceFeign farmerServiceFeign;
    @Override
    public DealerCart saveOrder(DealerCart cart) {

        ResponseEntity<UserInfo> user = loginServiceClient.getUserById(cart.getDealerId());
        if(!user.getBody().getUserType().equalsIgnoreCase("dealer")){
            throw new InvalidValueProvidedException("User is not a dealer");
        }
        ResponseEntity<CropEntity> crop = farmerServiceFeign.getCropByFarmerIdAndCropName(cart.getFarmerId(), cart.getCropName());
        if(crop.getStatusCode().is4xxClientError()){
            throw new InvalidValueProvidedException("Selected Crop/user is invalid");
        }
        if(!crop.getBody().isAvailability()){
            throw new InvalidValueProvidedException("Item is Out Of Stock!");
        }
        if(cart.getQuantity() > crop.getBody().getQuantity()){
            throw new InvalidValueProvidedException("Selected Quantity is greater than available quantity");
        }
        cart.setTotalAmount(crop.getBody().getCropPrice() * cart.getQuantity());
        return repo.save(cart);
    }

    @Override
    public List<DealerCart> getAllOrder() {
        return repo.findAll();
    }


}
