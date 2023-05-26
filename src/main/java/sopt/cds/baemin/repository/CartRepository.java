package sopt.cds.baemin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import sopt.cds.baemin.domain.Cart;
import sopt.cds.baemin.domain.Client;
import sopt.cds.baemin.domain.Food;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteByFood(Food food);

    List<Cart> findByClient(Client client);

}
