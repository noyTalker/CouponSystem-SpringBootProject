package coupon.system;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class GetAllCouponsUntilDate {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GetAllCouponsUntilDate.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CompanyFacade";
		Date date = new Date(System.currentTimeMillis()+60*60*24*8*1000);
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			List<Coupon> list = ((CompanyFacade) ccf).getAllCouponsUntilDate(date);
			for (Coupon coupon : list) {
				System.out.println(coupon);
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		} finally {
			try {
				system.shutDown();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		}
	}

}
