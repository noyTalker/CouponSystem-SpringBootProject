package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.exceptions.CouponSystemException;

@SpringBootApplication
public class RemoveCustomer {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RemoveCustomer.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		String name1 = "aaa";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((AdminFacade) ccf).removeCustomer(name1);
			System.out.println("Removed");
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
