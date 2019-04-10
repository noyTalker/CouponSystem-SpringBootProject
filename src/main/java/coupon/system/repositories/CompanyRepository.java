package coupon.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coupon.system.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> findByCompName( String name );
}