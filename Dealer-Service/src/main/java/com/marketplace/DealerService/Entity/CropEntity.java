package com.marketplace.DealerService.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropEntity {

    private int cropId;
    private int farmerId;
    private String cropName;
    private String cropType;
    private double cropPrice;
    private int quantity;
    private boolean availability;
}

