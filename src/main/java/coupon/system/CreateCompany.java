package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Company;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class CreateCompany {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CreateCompany.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		Company company = new Company("yyy", "aaa", "aaa");
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((AdminFacade) ccf).createCompany(company);
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
