package model.services;

public class PaypalService implements OnlinePaymentService{
	
	private static final Double TAX_INTEREST = 0.01; //Juros ao mês
	private static final Double TAX_PAYMENT_FREE = 0.02; //Taxa de pagamento
	
	@Override
	public Double paymentFree(Double amount) {
		return amount * TAX_PAYMENT_FREE;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		return amount * months * TAX_INTEREST;
	}

}
