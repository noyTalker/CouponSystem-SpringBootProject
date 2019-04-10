package coupon.system.income;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
	@Query("select i from Income i where i.name = ?1 and i.description = ?2")
	List<Income> findByName( String name, IncomeType description );
}
