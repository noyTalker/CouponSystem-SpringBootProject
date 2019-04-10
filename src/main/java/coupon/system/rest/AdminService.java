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

import coupon.system.entities.Company;
import coupon.system.entities.Customer;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.AdminFacade;
import coupon.system.income.Income;

@RestController
@RequestMapping("/admin")
public class AdminService {

	@Autowired
	AdminFacade adminFacade;

	@RequestMapping(path = "/company", method = RequestMethod.POST)
	public ResponseEntity<?> createCompany(@RequestBody Company company) {
		try {
			adminFacade.createCompany(company);
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/company/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompany(@PathVariable String name) {
		try {
			adminFacade.removeCompany(name);
			return new ResponseEntity<String>(name, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/company", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		try {
			adminFacade.updateCompany(company);
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/company/{name}")
	public ResponseEntity<?> getCompany(@PathVariable String name) {
		try {
			return new ResponseEntity<Company>(adminFacade.getCompany(name), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/companies")
	public ResponseEntity<Object> getCompanies() {
		try {
			return new ResponseEntity<>(adminFacade.getAllCompanies(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/customer", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		try {
			adminFacade.createCustomer(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/customer/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable String name) {
		try {
			adminFacade.removeCustomer(name);
			return new ResponseEntity<String>(name, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/customer", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		try {
			adminFacade.updateCustomer(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/customer/{name}")
	public ResponseEntity<?> getCustomer(@PathVariable String name) {
		try {
			return new ResponseEntity<Customer>(adminFacade.getCustomer(name), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/customers")
	public ResponseEntity<?> getAllCustomers() {
		try {
			return new ResponseEntity<List<Customer>>(adminFacade.getAllCustomers(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/incomes")
	public ResponseEntity<?> getAllIncomes() {
		try {
			return new ResponseEntity<List<Income>>(adminFacade.getAllIncomes(), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/{companyId}")
	public ResponseEntity<?> getAllCompanyIncomes(@PathVariable int companyId) {
		try {
			return new ResponseEntity<List<Income>>(adminFacade.getAllCompanyIncomes(companyId), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/{customerId}")
	public ResponseEntity<?> getAllIncomes(@PathVariable int customerId) {
		try {
			return new ResponseEntity<List<Income>>(adminFacade.getAllCustomerIncomes(customerId), HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
