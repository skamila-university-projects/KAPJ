package skamila.kapj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findByLastName(String lastName);

    AppUser findById(long id);

    AppUser findByLogin(String login);

    List<AppUser> findByAppUserRole(AppUserRole appUserRole);
}