package coupon.system.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import coupon.system.entities.Coupon;
import coupon.system.repositories.CouponRepository;

@Component
//@Scope("prototype")
public class DailyCouponExpirationTask implements Runnable {

	@Autowired
	private CouponRepository couponRepository;
	private boolean quit = false;

	public DailyCouponExpirationTask() {
		super();
	}

	@Override
	public void run() {
		while (!quit) {
			Date date = new Date();
			List<Coupon> list = couponRepository.findAllExpired(date);
			List<Coupon> list2 = new ArrayList<>(list);
			for (Coupon coupon : list2) {
				couponRepository.delete(coupon);
			}
			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public void stopTask() {
		quit = true;
	}
	
	@Bean
	public Thread applicationThread() {
		return new Thread(this);
	}
}
