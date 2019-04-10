package coupon.system;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.framework.CouponSystem;

@SpringBootApplication
public class CreateCoupon {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CreateCoupon.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "aaa";
		String password = "aaa";
		String clientType = "CompanyFacade";
		Coupon coupon = new Coupon();
		coupon.setTitle("AAA");
		coupon.setStartDate(new Date());
		coupon.setEndDate(new Date(System.currentTimeMillis()+60*60*24*20*1000));
		coupon.setAmount(5);
		coupon.setType(Coupon.Type.ATTRACTION);
		coupon.setMessage("aaaaa");
		coupon.setPrice(64.3);
		coupon.setImage("aaaa");
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((CompanyFacade) ccf).createCoupon(coupon);
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
