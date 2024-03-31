package com.marketplace.DealerService.Controller;

import com.marketplace.DealerService.Entity.DealerCart;
import com.marketplace.DealerService.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Dealer")
@CrossOrigin
public class DealerController {
    @Autowired
    CartService cartService;

    @PostMapping("/saveOrder")
    public ResponseEntity<DealerCart> saveOrder(@RequestBody DealerCart dealerCart){
        return ResponseEntity.ok(cartService.saveOrder(dealerCart));
    }
    @GetMapping("/allOrder")
    public ResponseEntity<List<DealerCart>> getAllOrder(){
        return ResponseEntity.ok(cartService.getAllOrder());
    }
}
