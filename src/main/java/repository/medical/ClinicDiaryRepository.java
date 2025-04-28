package repository.medical;

import entity.medical.ClinicDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicDiaryRepository extends JpaRepository<ClinicDiary, Long> {
}
