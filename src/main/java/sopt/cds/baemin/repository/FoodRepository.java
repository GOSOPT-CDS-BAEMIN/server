package sopt.cds.baemin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findById(Long foodId);
}
