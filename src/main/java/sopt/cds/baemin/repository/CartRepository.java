package sopt.cds.baemin.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sopt.cds.baemin.domain.Cart;
import sopt.cds.baemin.domain.Client;
import sopt.cds.baemin.domain.Food;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteByFood(Food food);

    List<Cart> findByClient(Client client);

    @Query("select c from Cart c where c.client=:client")
    List<Cart> findAllByClient(Client client);
}
