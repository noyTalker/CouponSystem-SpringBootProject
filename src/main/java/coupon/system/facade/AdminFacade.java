package coupon.system.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.entities.Company;
import coupon.system.entities.Coupon;
import coupon.system.entities.Customer;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.income.Income;
import coupon.system.income.IncomeUse;
import coupon.system.repositories.CompanyRepository;
import coupon.system.repositories.CouponRepository;
import coupon.system.repositories.CustomerRepository;

@Component
public class AdminFacade implements CouponClientFacade{

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private IncomeUse incomeService;
	
	private String name = "admin";
	private String password = "123321";
	
	@Transactional
	public void createCompany(Company company) throws CouponSystemException {
		List<Company> list = companyRepository.findByCompName(company.getCompName());
		if (list.isEmpty()) {
			companyRepository.save(company);
		} else {
			throw new CouponSystemException("The company name = '" + company.getCompName() + "' already exists");
		}
	}
	
	@Transactional
	public Company getCompany(String name) throws CouponSystemException {
		List<Company> list = companyRepository.findByCompName(name);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			throw new CouponSystemException("The company is not exist");
		}
	}
	
	@Transactional
	public void removeCompany(String name) throws CouponSystemException {
		List<Coupon> list = couponRepository.findCouponsByCompany(getCompany(name));
		for (Coupon coupon : list) {
			couponRepository.deleteById(coupon.getId());
		}
		companyRepository.deleteById(getCompany(name).getId());
	}
	
	@Transactional
	public void updateCompany(Company company) throws CouponSystemException {
		Company comp = getCompany(company.getCompName());
		comp.setPassword(company.getPassword());
		comp.seteMail(company.geteMail());
		companyRepository.save(comp);
	}
	
	@Transactional
	public List<Company> getAllCompanies() throws CouponSystemException {
		return companyRepository.findAll();
	}
	
	@Transactional
	public void createCustomer(Customer customer) throws CouponSystemException {
		List<Customer> list = customerRepository.findByCustName(customer.getCustName());
		if (list.isEmpty()) {
			customerRepository.save(customer);
		} else {
			throw new CouponSystemException("The customer name = '" + customer.getCustName() + "' already exists");
		}
	}
	
	@Transactional
	public Customer getCustomer(String name) throws CouponSystemException {
		List<Customer> list = customerRepository.findByCustName(name);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			throw new CouponSystemException("The customer is not exist");
		}
	}
	
	@Transactional
	public void removeCustomer(String name) throws CouponSystemException {
		customerRepository.deleteById(getCustomer(name).getId());
	}
	
	@Transactional
	public void updateCustomer(Customer customer) throws CouponSystemException {
		Customer cust = getCustomer(customer.getCustName());
		cust.setPassword(customer.getPassword());
		customerRepository.save(cust);
	}
	
	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customerRepository.findAll();
	}

	public boolean login(String name,String password) {
		if(this.name.equals(name)&&this.password.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	public List<Income> getAllIncomes() throws CouponSystemException{
		List<Income> incomes = incomeService.viewAllIncome();
		return new ArrayList<>(incomes);
	}
	
	@Transactional
	public List<Income> getAllCompanyIncomes(int companyId) throws CouponSystemException{
		List<Income> incomes = incomeService.viewCreatedIncomesByCompany(companyId);
		List<Income> incomes2 = incomeService.viewUpdatedIncomesByCompany(companyId);
		for (Income income : incomes2) {
			incomes.add(income);
		}
		return new ArrayList<>(incomes);
	}
	
	@Transactional
	public List<Income> getAllCustomerIncomes(int customerId) throws CouponSystemException{
		List<Income> incomes = incomeService.viewPurchaseIncomesByCustomer(customerId);
		return new ArrayList<>(incomes);
	}
}
