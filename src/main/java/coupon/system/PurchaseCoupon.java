package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CouponClientFacade;
import coupon.system.facade.CustomerFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class PurchaseCoupon {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PurchaseCoupon.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CustomerFacade";
		String title = "ddd";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((CustomerFacade) ccf).purchaseCoupon(title);
			System.out.println("Coupon purchased");
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
