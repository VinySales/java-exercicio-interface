package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do contrato");
		System.out.print("Número: ");
		Integer numero = scan.nextInt();
		
		System.out.print("Data (dd/mm/yyyy): ");
		LocalDate date = LocalDate.parse(scan.next(), fmt);
		
		System.out.print("Valor do contrato: ");
		Double totalValue = scan.nextDouble();
		
		Contract contract = new Contract(numero, date, totalValue);
		
		System.out.print("Entre com o número de parcelas: ");
		int n = scan.nextInt();
		
		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(contract, n);
		
		System.out.println("Parcelas");
		
		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment.getDueDate() + " - " + installment.getAmount());
		}
		
		scan.close();

	}

}
