package Banco;

public class ContaPoupanca extends Conta {
	final double TAXA_RENDIMENTO = 0.05;
	
	public ContaPoupanca(int agencia, int numero, Pessoa titular) {
		super(agencia, numero, titular);
	}

	@Override
	public void deposita(double valor) {
		super.saldo += valor;	
	}

}
