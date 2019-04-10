package coupon.system.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.entities.Coupon;
import coupon.system.entities.Customer;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.income.Income;
import coupon.system.income.IncomeUse;
import coupon.system.income.IncomeType;
import coupon.system.repositories.CouponRepository;
import coupon.system.repositories.CustomerRepository;

@Component
public class CustomerFacade implements CouponClientFacade {

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private IncomeUse incomeService;

	private int customerId;

	@Transactional
	public void purchaseCoupon(String title) throws CouponSystemException {
		Customer customer = customerRepository.findById(customerId).get();
		Income income = new Income();
		income.setName(customer.getCustName());
		income.setIncomeType(IncomeType.CUSTOMER_PURCHASE);
		List<Coupon> list = couponRepository.findByTitle(title);
		if (!list.isEmpty()) {
			List<Coupon> list3 = customer.getCoupons();
			List<Coupon> list2 = new ArrayList<>(list3);
			if (list2.isEmpty()) {
				list.get(0).addCustomers(customer);
				list.get(0).setAmount(list.get(0).getAmount() - 1);
				income.setAnount(list.get(0).getPrice());
				incomeService.storeIncome(income);
			} else {
				for (Coupon coupon : list2) {
					if (!coupon.getTitle().equals(title)) {
						if (list.get(0).getAmount() > 0) {
							list.get(0).addCustomers(customer);
							list.get(0).setAmount(list.get(0).getAmount() - 1);
							income.setAnount(list.get(0).getPrice());
							incomeService.storeIncome(income);
						} else {
							throw new CouponSystemException("The coupon with title = '" + title + "' is out of stock");
						}
					} else {
						throw new CouponSystemException("The coupon with title = '" + title + "' alredy used");
					}
				}
			}
		} else {
			throw new CouponSystemException("The coupon with title = '" + title + "' is not exists");
		}
	}

	@Transactional
	public List<Coupon> getAllPurchaseCoupons() throws CouponSystemException {
		Customer customer = customerRepository.findById(customerId).get();
		List<Coupon> coupons = customer.getCoupons();
		return new ArrayList<>(coupons);
	}

	@Transactional
	public List<Coupon> getPurchaseCouponsByType(Coupon.Type type) throws CouponSystemException {
		Customer customer = customerRepository.findById(customerId).get();
		List<Coupon> coupons = couponRepository.findAllPurchaseByType(type, customer.getId());
		return new ArrayList<>(coupons);
	}

	@Transactional
	public List<Coupon> getPurchaseCouponsUntilPrice(double price) throws CouponSystemException {
		Customer customer = customerRepository.findById(customerId).get();
		List<Coupon> coupons = couponRepository.findAllPurchaseUntilPrice(price, customer.getId());
		return new ArrayList<>(coupons);
	}

	public boolean login(String name, String password) throws CouponSystemException {
		List<Customer> list = customerRepository.findByCustName(name);
		if (!list.isEmpty()) {
			if (list.get(0).getPassword().equals(password)) {
				this.customerId = list.get(0).getId();
				return true;
			}
		}
		return false;
	}

	@Transactional
	public List<Income> getAllIncomes() throws CouponSystemException{
		List<Income> incomes = incomeService.viewPurchaseIncomesByCustomer(customerId);
		return new ArrayList<>(incomes);
	}
}
