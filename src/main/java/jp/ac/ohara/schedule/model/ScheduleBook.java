package jp.ac.ohara.schedule.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "schedules")
public class ScheduleBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 50, nullable = true)
	private String name;
	@Column(nullable = true)
	private Date day;
	@Column(length = 100, nullable = true)
	private String title;
	@Column(length = 500, nullable = true)
	private String detail;
}