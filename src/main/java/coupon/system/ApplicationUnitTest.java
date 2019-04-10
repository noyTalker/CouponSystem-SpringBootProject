package coupon.system;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coupon.system.entities.Company;
import coupon.system.entities.Coupon;
import coupon.system.entities.Customer;
import coupon.system.exceptions.CouponSystemException;
import coupon.system.facade.AdminFacade;
import coupon.system.facade.CompanyFacade;
import coupon.system.facade.CouponClientFacade;
import coupon.system.facade.CustomerFacade;
import coupon.system.framework.CouponSystem;
import coupon.system.income.Income;

@SpringBootApplication
public class ApplicationUnitTest {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationUnitTest.class, args);
		CouponSystem system = (CouponSystem) context.getBean("couponSystem");
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacade";
		try {
			CouponClientFacade ccf = system.login(name, password, clientType);
			((AdminFacade) ccf).createCompany(new Company("aaa", "aaa", "aaa"));
			((AdminFacade) ccf).createCompany(new Company("bbb", "aaa", "aaa"));
			System.err.println("Create company works");
			List<Company> list = ((AdminFacade) ccf).getAllCompanies();
			for (Company company : list) {
				System.out.println(company);
			}
			System.err.println("Get all companies works");
			((AdminFacade) ccf).updateCompany(new Company("bbb", "bbb", "aaa"));
			System.err.println("Updated company works");
			System.out.println(((AdminFacade) ccf).getCompany("bbb"));
			System.err.println("Get company works");
			((AdminFacade) ccf).removeCompany("bbb");
			System.err.println("Remove company works");
			
			((AdminFacade) ccf).createCustomer(new Customer("aaa", "aaa"));
			((AdminFacade) ccf).createCustomer(new Customer("bbb", "aaa"));
			System.err.println("Create company works");
			List<Customer> list1 = ((AdminFacade) ccf).getAllCustomers();
			for (Customer customer : list1) {
				System.out.println(customer);
			}
			System.err.println("Get all customers works");
			((AdminFacade) ccf).updateCustomer(new Customer("bbb", "bbb"));
			System.err.println("Updated customer works");
			System.out.println(((AdminFacade) ccf).getCustomer("bbb"));
			System.err.println("Get customer works");
			((AdminFacade) ccf).removeCustomer("bbb");
			System.err.println("Remove customer works");
			
			name = "aaa";
			password = "aaa";
			clientType = "CompanyFacade";
			ccf = system.login(name, password, clientType);
			Coupon coupon = new Coupon();
			coupon.setTitle("aaa");
			coupon.setStartDate(new Date());
			coupon.setEndDate(new Date(System.currentTimeMillis()+60*60*24*20*1000));
			coupon.setAmount(5);
			coupon.setType(Coupon.Type.ATTRACTION);
			coupon.setMessage("aaaaa");
			coupon.setPrice(65.5);
			coupon.setImage("aaaa");
			((CompanyFacade) ccf).createCoupon(coupon);
			Coupon coupon3 = new Coupon();
			coupon3.setTitle("bbb");
			coupon3.setStartDate(new Date());
			coupon3.setEndDate(new Date(System.currentTimeMillis()+60*60*24*20*1000));
			coupon3.setAmount(6);
			coupon3.setType(Coupon.Type.ATTRACTION);
			coupon3.setMessage("aaaaa");
			coupon3.setPrice(20);
			coupon3.setImage("aaaa");
			((CompanyFacade) ccf).createCoupon(coupon3);
			System.err.println("Create coupon works");
			List<Coupon> list2 = ((CompanyFacade) ccf).getAllCoupons();
			for (Coupon coupon1 : list2) {
				System.out.println(coupon1);
			}
			System.err.println("Get all coupons works");
			coupon.setTitle("bbb");
			coupon.setEndDate(new Date(System.currentTimeMillis()+60*60*24*25*1000));
			coupon.setPrice(60);
			((CompanyFacade) ccf).updateCoupon(coupon);
			System.err.println("Update coupon works");
			System.out.println(((CompanyFacade) ccf).getCoupon("bbb"));
			System.err.println("Get coupon works");
			((CompanyFacade) ccf).removeCoupon("bbb");
			System.err.println("Remove coupon works");
			List<Income> list3 = ((CompanyFacade) ccf).getAllIncomes();
			for (Income income : list3) {
				System.out.println(income);
			}
			System.err.println("Get all company incomes works");
			
			name = "aaa";
			password = "aaa";
			clientType = "CustomerFacade";
			ccf = system.login(name, password, clientType);
			((CustomerFacade) ccf).purchaseCoupon("aaa");
			System.err.println("Purchase coupon works");
			List<Coupon> list4 = ((CustomerFacade) ccf).getAllPurchaseCoupons();
			for (Coupon coupon2 : list4) {
				System.out.println(coupon2);
			}
			System.err.println("Get all purchase coupons works");
			List<Income> list5 = ((CustomerFacade) ccf).getAllIncomes();
			for (Income income : list5) {
				System.out.println(income);
			}
			System.err.println("Get all customer incomes works");
			
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
