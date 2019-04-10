package coupon.system;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CouponClientFacade;
import coupon.system.facade.CustomerFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class GetAllPurchasesCouponsUntilPrice {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(GetAllPurchasesCouponsUntilPrice.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "bbb";
		String password = "bbb";
		String clientType = "CustomerFacade";
		double price = 41;

		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			List<Coupon> list = ((CustomerFacade) ccf).getPurchaseCouponsUntilPrice(price);
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
