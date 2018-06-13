package home.blackharold.sweater.repository;

import home.blackharold.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
