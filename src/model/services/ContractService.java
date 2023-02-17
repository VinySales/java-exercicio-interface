package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService ops;
	
	public ContractService() {
	}
	
	public ContractService(OnlinePaymentService ops) {
		this.ops = ops;
	}

	public OnlinePaymentService getOps() {
		return ops;
	}

	public void setOps(OnlinePaymentService ops) {
		this.ops = ops;
	}

	public void processContract(Contract contract, Integer months) {
		LocalDate date = contract.getDate();
		
		for (int i = 1; i <= months; i++) {
			date = date.plusMonths(1);
			Double amount = contract.getTotalValue() / months;
			amount += getOps().paymentFree(amount); //2
			amount += getOps().interest(amount, i);
			
			Installment installment = new Installment(date, amount);
			
			contract.addInstallment(installment);
		}
	}
	
}
