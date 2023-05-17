package sopt.cds.baemin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Review;
import sopt.cds.baemin.domain.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStore(Store store);
}
