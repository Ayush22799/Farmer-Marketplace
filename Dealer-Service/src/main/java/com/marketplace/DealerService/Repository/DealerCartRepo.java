package com.marketplace.DealerService.Repository;

import com.marketplace.DealerService.Entity.DealerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerCartRepo extends JpaRepository<DealerCart, Integer> {
}
