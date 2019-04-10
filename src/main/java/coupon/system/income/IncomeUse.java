package coupon.system.income;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.entities.Company;
import coupon.system.entities.Customer;
import coupon.system.repositories.CompanyRepository;
import coupon.system.repositories.CustomerRepository;

@Component
public class IncomeUse {

	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public void storeIncome(Income income) {
		Date date = new Date();
		switch (income.getIncomeType()) {
		case CUSTOMER_PURCHASE:
			break;
		case COMPANY_NEW_COUPON:
			income.setAnount(100);
			break;
		case COMPANY_UPDATED_COUPON:
			income.setAnount(10);
			break;
		default:
			break;
		}
		income.setDate(date);
		incomeRepository.save(income);
		System.err.println(income.getIncomeType().getDescription());
	}
	
	public List<Income> viewAllIncome(){
		return incomeRepository.findAll();
	}
	
	public List<Income> viewPurchaseIncomesByCustomer(int customerId){
		Customer customer = customerRepository.findById(customerId).get();
		return incomeRepository.findByName(customer.getCustName(),IncomeType.CUSTOMER_PURCHASE);
	}
	
	public List<Income> viewCreatedIncomesByCompany(int companyId){
		Company company = companyRepository.findById(companyId).get();
		return incomeRepository.findByName(company.getCompName(),IncomeType.COMPANY_NEW_COUPON);
	}
	
	public List<Income> viewUpdatedIncomesByCompany(int companyId){
		Company company = companyRepository.findById(companyId).get();
		return incomeRepository.findByName(company.getCompName(),IncomeType.COMPANY_UPDATED_COUPON);
	}
}
