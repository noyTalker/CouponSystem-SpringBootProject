package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class RemoveCoupon {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RemoveCoupon.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CompanyFacade";
		String title = "aaa";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((CompanyFacade) ccf).removeCoupon(title);
			System.out.println("Coupon Removed");
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
