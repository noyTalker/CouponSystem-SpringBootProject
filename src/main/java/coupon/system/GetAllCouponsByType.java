package coupon.system;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class GetAllCouponsByType {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GetAllCouponsByType.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CompanyFacade";
		Coupon.Type type = Coupon.Type.FOOD;
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			List<Coupon> list = ((CompanyFacade) ccf).getAllCouponsByType(type);
			for (Coupon coupon : list) {
				System.out.println(coupon);
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
