package coupon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.entities.Company;
import coupon.system.exceptions.CouponSystemException;

@SpringBootApplication
public class UpdateCompany {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UpdateCompany.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		Company company = new Company("aaa", "bbb", "bbb");
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((AdminFacade) ccf).updateCompany(company);
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
