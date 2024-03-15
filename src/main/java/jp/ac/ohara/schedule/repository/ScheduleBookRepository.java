package jp.ac.ohara.schedule.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.schedule.model.ScheduleBook;
 
@Repository
public interface ScheduleBookRepository extends JpaRepository<ScheduleBook, Long> {

	public List<ScheduleBook> findAllByNameEquals(String username);
}