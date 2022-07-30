package pt.feefo.jobtitleservice.domain.jobtitle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.feefo.jobtitleservice.domain.jobtitle.entities.JobTitle;

@Repository(value = "jobTitleRepository")
public interface IJobTitleRepository extends JpaRepository<JobTitle, Long> {

}
