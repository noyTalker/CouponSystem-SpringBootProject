package coupon.system;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.facade.AdminFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.entities.Company;
import coupon.system.exceptions.CouponSystemException;

@SpringBootApplication
public class GetAllCompanies {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GetAllCompanies.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			List<Company> list = ((AdminFacade) ccf).getAllCompanies();
			for (Company company : list) {
				System.out.println(company);
			}
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
