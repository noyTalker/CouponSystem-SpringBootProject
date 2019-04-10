package coupon.system.rest;

import java.util.Date;
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
import coupon.system.facade.CompanyFacade;
import coupon.system.income.Income;

@RestController
@RequestMapping("/company")
public class CompanyService {

	@Autowired
	CompanyFacade companyFacade;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {
		try {
			companyFacade.createCoupon(coupon);
			return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCoupon(@PathVariable String name) {
		try {
			companyFacade.removeCoupon(name);
			return new ResponseEntity<String>(name, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
		try {
			companyFacade.updateCoupon(coupon);
			return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/{name}")
	public ResponseEntity<?> getCoupon(@PathVariable String name) {
		try {
			return new ResponseEntity<Coupon>(companyFacade.getCoupon(name), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/coupons")
	public ResponseEntity<?> getAllCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(companyFacade.getAllCoupons(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/{type}")
	public ResponseEntity<?> getAllCouponsByType(@PathVariable Coupon.Type type) {
		try {
			return new ResponseEntity<List<Coupon>>(companyFacade.getAllCouponsByType(type), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/{price}")
	public ResponseEntity<?> getAllCouponsUntilPrice(@PathVariable double price) {
		try {
			return new ResponseEntity<List<Coupon>>(companyFacade.getAllCouponsUntilPrice(price), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/{date}")
	public ResponseEntity<?> getAllCouponsUntilDate(@PathVariable Date date) {
		try {
			return new ResponseEntity<List<Coupon>>(companyFacade.getAllCouponsUntilDate(date), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/incomes")
	public ResponseEntity<?> getAllIncomes() {
		try {
			return new ResponseEntity<List<Income>>(companyFacade.getAllIncomes(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
