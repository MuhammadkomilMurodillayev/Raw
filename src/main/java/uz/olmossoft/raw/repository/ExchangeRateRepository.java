package uz.olmossoft.raw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.olmossoft.raw.domain.ExchangeRate;

import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, вс 00:50. 1/22/23
 */

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByCurrency(String currency);
}
