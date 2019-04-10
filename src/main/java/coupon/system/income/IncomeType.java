package coupon.system.income;

public enum IncomeType {
	CUSTOMER_PURCHASE("Customer have been charged for the coupon price"),
	COMPANY_NEW_COUPON("Company have been charged for 100 NIS"),
	COMPANY_UPDATED_COUPON("Company have been charged for 10 NIS");
	String description;
	
	private IncomeType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
