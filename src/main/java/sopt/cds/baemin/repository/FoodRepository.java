package sopt.cds.baemin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findById(Long foodId);
}
