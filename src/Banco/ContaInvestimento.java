package Banco;

public class ContaInvestimento extends Conta {
	final double TAXA_RENDIMENTO = 0.05;
	public ContaInvestimento(int agencia, int numero, Pessoa titular) {
		super(agencia, numero, titular);
	}
	

	@Override
	public void deposita(double valor) {
        super.saldo += valor;
    }
	
	
	
}
