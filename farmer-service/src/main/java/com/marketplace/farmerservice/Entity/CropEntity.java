package com.marketplace.farmerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "crop_details")
public class CropEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private int cropId;
    @Column(name = "farmer_id")
    private int farmerId;
    @Column(name = "crop_name")
    private String cropName;
    @Column(name = "crop_type")
    private String cropType;
    @Column(name = "crop_price")
    private double cropPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "status")
    private boolean availability;
}

