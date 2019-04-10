package coupon.system.framework;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.AdminFacade;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.facade.CustomerFacade;
import coupon.system.thread.DailyCouponExpirationTask;

@Component
public class CouponSystem {

	@Autowired
	private AdminFacade fcd1;
	@Autowired
	private CompanyFacade fcd2;
	@Autowired
	private CustomerFacade fcd3;
	@Autowired
	private DailyCouponExpirationTask t;	
	@Autowired
	private Thread task;
	
	@PostConstruct
	public void StartingThread() {
		task.start();
	}
	
	public CouponClientFacade login(String name, String password, String clientType) throws CouponSystemException {
		boolean exist = false;
		switch (clientType) {
		case "AdminFacade":
			exist = fcd1.login(name, password);
			if (exist) {
				return fcd1;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		case "CompanyFacade":
			exist = fcd2.login(name, password);
			if (exist) {
				return fcd2;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		case "CustomerFacade":
			exist = fcd3.login(name, password);
			if (exist) {
				return fcd3;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		default:
			break;
		}
		return null;
	}

	public synchronized void shutDown() throws CouponSystemException {
		t.stopTask();
		task.interrupt();
		try {
			task.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
