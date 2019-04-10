package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Customer;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class CreateCustomer {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CreateCustomer.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		Customer customer = new Customer("bbb", "bbb");
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((AdminFacade) ccf).createCustomer(customer);
			System.out.println("Created");
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
