package sopt.cds.baemin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Food;
import sopt.cds.baemin.domain.Store;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findById(Long foodId);

    List<Food> findAllByStore(Store s);
}
