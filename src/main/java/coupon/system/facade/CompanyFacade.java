package coupon.system.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.entities.Company;
import coupon.system.entities.Coupon;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.income.Income;
import coupon.system.income.IncomeUse;
import coupon.system.income.IncomeType;
import coupon.system.repositories.CompanyRepository;
import coupon.system.repositories.CouponRepository;

@Component
public class CompanyFacade implements CouponClientFacade{

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private IncomeUse incomeService;

	private int companyId;
	
	@Transactional
	public void createCoupon(Coupon coupon) throws CouponSystemException {
		Company company = companyRepository.findById(companyId).get();
		coupon.setCompany(company);
		List<Coupon> list = couponRepository.findByTitle(coupon.getTitle());
		if (list.isEmpty()) {
			couponRepository.save(coupon);
			Income income = new Income();
			income.setName(company.getCompName());
			income.setIncomeType(IncomeType.COMPANY_NEW_COUPON);
			incomeService.storeIncome(income);
		} else {
			throw new CouponSystemException("The coupon title = '" + coupon.getTitle() + "' already exists");
		}
	}
	
	@Transactional
	public Coupon getCoupon(String title) throws CouponSystemException {
		List<Coupon> list = couponRepository.findByTitle(title);
		if (!list.isEmpty()) {
			return list.get(0);
		}else {
			throw new CouponSystemException("The coupon is not exist");
		}
	}
	
	@Transactional
	public void removeCoupon(String title) throws CouponSystemException {
		couponRepository.deleteById(getCoupon(title).getId());
	}
	
	@Transactional
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon c = getCoupon(coupon.getTitle());
		Company company = companyRepository.findById(companyId).get();
		c.setEndDate(coupon.getEndDate());
		c.setPrice(coupon.getPrice());
		couponRepository.save(c);
		Income income = new Income();
		income.setName(company.getCompName());
		income.setIncomeType(IncomeType.COMPANY_UPDATED_COUPON);
		incomeService.storeIncome(income);
	}
	
	@Transactional
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		Company company = companyRepository.findById(companyId).get();
		List<Coupon> coupons = company.getCoupons();
		return new ArrayList<>(coupons);
	}
	
	@Transactional
	public List<Coupon> getAllCouponsByType(Coupon.Type type) throws CouponSystemException {
		Company company = companyRepository.findById(companyId).get();
		List<Coupon> coupons = couponRepository.findAllByType(type,company.getId());
		return new ArrayList<>(coupons);
	}
	
	@Transactional
	public List<Coupon> getAllCouponsUntilPrice(double price) throws CouponSystemException {
		Company company = companyRepository.findById(companyId).get();
		List<Coupon> coupons = couponRepository.findAllUntilPrice(price,company.getId());
		return new ArrayList<>(coupons);
	}
	
	@Transactional
	public List<Coupon> getAllCouponsUntilDate(Date date) throws CouponSystemException {
		Company company = companyRepository.findById(companyId).get();
		List<Coupon> coupons = couponRepository.findAllUntilDate(date, company.getId());
		return new ArrayList<>(coupons);
	}
	
	@Transactional
	public boolean login(String name, String password) throws CouponSystemException {
		List<Company> list = companyRepository.findByCompName(name);
		if(!list.isEmpty()) {
			if(list.get(0).getPassword().equals(password)){
				this.companyId = list.get(0).getId();
				return true;
			}
		}
		return false;
	}
	
	@Transactional
	public List<Income> getAllIncomes() throws CouponSystemException{
		Company company = companyRepository.findById(companyId).get();
		List<Income> incomes = incomeService.viewCreatedIncomesByCompany(company.getId());
		List<Income> incomes2 = incomeService.viewUpdatedIncomesByCompany(company.getId());
		for (Income income : incomes2) {
			incomes.add(income);
		}
		return new ArrayList<>(incomes);
	}
}
