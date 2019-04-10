package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.exceptions.CouponSystemException;

@SpringBootApplication
public class GetCustomer {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GetCustomer.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		String name1 = "bbb";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			System.out.println(((AdminFacade) ccf).getCustomer(name1));
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
