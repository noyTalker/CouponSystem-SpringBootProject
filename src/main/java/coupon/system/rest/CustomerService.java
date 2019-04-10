package coupon.system.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.CustomerFacade;
import coupon.system.income.Income;

@RestController
@RequestMapping("/customer")
public class CustomerService {

	@Autowired
	CustomerFacade customerFacade;

	@RequestMapping(path = "/{title}", method = RequestMethod.POST)
	public ResponseEntity<?> purchaseCoupon(@RequestBody String title) {
		try {
			customerFacade.purchaseCoupon(title);
			return new ResponseEntity<String>(title, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping("/coupons")
	public ResponseEntity<?> getAllCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(customerFacade.getAllPurchaseCoupons(),HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/{type}")
	public ResponseEntity<?> getPurchaseCouponsByType(@PathVariable Coupon.Type type) {
		try {
			return new ResponseEntity<List<Coupon>>(customerFacade.getPurchaseCouponsByType(type), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/{price}")
	public ResponseEntity<?> getPurchaseCouponsUntilPrice(@PathVariable double price) {
		try {
			return new ResponseEntity<List<Coupon>>(customerFacade.getPurchaseCouponsUntilPrice(price), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/incomes")
	public ResponseEntity<?> getAllIncomes() {
		try {
			return new ResponseEntity<List<Income>>(customerFacade.getAllIncomes(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
