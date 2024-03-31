package com.marketplace.DealerService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_detail")
public class DealerCart {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name = "order_id")
        private int orderId;

        @Column(name = "dealer_id")
        private int dealerId;

        @Column(name = "farmer_id")
        private int farmerId;

        @Column(name = "crop_name")
        private String cropName;

        @Column(name = "crop_type")
        private String cropType;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "amount")
        private double totalAmount;


        public DealerCart(String cropName, String cropType, int quantity, double totalAmount){
                this.cropName = cropName;
                this.cropType = cropType;
                this.quantity =quantity;
                this.totalAmount = totalAmount;
        }
    }
