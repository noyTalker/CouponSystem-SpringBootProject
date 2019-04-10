package coupon.system;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CouponClientFacade;
import coupon.system.facade.CustomerFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.income.Income;

@SpringBootApplication
public class GetAllCustomerIncomes {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(GetAllCustomerIncomes.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CustomerFacade";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			List<Income> list = ((CustomerFacade) ccf).getAllIncomes();
			for (Income income : list) {
				System.out.println(income);
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
