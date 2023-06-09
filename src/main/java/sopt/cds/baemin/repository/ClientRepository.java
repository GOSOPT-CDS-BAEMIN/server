package sopt.cds.baemin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long clientId);
}
