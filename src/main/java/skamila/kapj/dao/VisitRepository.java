package skamila.kapj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.domain.Visit;

@Transactional
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
