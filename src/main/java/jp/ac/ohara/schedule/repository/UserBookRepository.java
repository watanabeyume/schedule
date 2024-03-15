package jp.ac.ohara.schedule.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.schedule.model.UserBook;
 
@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
	UserBook findByUsernameEquals(String username);
}