package Banco;

import java.util.Date;


public abstract class Pessoa {
	private String nomeCliente;

	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	

	public abstract String getCFPorCNPJ();
	
	
	public double getTaxaTributo_in(double taxa) {
		
		return taxa;
	}
	
	public double getTaxaPoupanca(double valor) {
		
		return valor;
		
	}
	
	public double getTaxaInvestimento(double valor) {
		return valor;
		
	}


	//public abstract double getTaxaTributo();
	
}
