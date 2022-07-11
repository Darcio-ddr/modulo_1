package Banco;



public class ContaCorrente extends Conta {
	
	public ContaCorrente(int agencia, int numero, Pessoa titular) {
		super(agencia, numero,titular);
	}
	
	@Override
	public void deposita(double valor) {
        super.saldo += valor;
    }


	
	

	//@Override
	//public double getValorImposto() {	
	//	return super.saldo * 0.01;
	//}

}
