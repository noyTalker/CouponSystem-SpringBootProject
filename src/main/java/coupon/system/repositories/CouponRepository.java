package coupon.system.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import coupon.system.entities.Company;
import coupon.system.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	List<Coupon> findByTitle( String title );
	
	@Query("select coup from Company comp join comp.coupons coup where coup.type like ?1 and comp.id like ?2")
	List<Coupon> findAllByType(Coupon.Type type,int id);
	
	@Query("select coup from Company comp join comp.coupons coup where coup.price < ?1 and comp.id like ?2")
	List<Coupon> findAllUntilPrice(double price,int id);

	@Query("select coup from Company comp join comp.coupons coup where coup.endDate < ?1 and comp.id like ?2")
	List<Coupon> findAllUntilDate(Date date,int id);
	
	@Query("select coup from Customer cust join cust.coupons coup where coup.type like ?1 and cust.id like ?2")
	List<Coupon> findAllPurchaseByType(Coupon.Type type,int id);
	
	@Query("select coup from Customer cust join cust.coupons coup where coup.price < ?1 and cust.id like ?2")
	List<Coupon> findAllPurchaseUntilPrice(double price,int id);

	@Query("select c from Coupon c where c.endDate < ?1")
	List<Coupon> findAllExpired(Date date);
	
	@Query("select c from Coupon c where c.company = ?1")
	List<Coupon> findCouponsByCompany(Company company);

}