package com.marketplace.DealerService.Service;

import com.marketplace.DealerService.Entity.DealerCart;

import java.util.List;

public interface CartService {
    DealerCart saveOrder(DealerCart cart);
    List<DealerCart> getAllOrder();
}
