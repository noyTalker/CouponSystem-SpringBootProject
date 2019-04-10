package coupon.system;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class UpdateCoupon {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UpdateCoupon.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CompanyFacade";
		Coupon coupon = new Coupon();
		String title = "aaa";
		Date endDate = new Date(System.currentTimeMillis()+60*60*24*10*1000);
		coupon.setTitle(title);
		coupon.setEndDate(endDate);
		coupon.setPrice(61.2);
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((CompanyFacade) ccf).updateCoupon(coupon);
			System.out.println("Updated");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}finally {
			try {
				system.shutDown();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		}
	}
}
