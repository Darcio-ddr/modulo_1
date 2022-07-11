package Banco;

public class PessoaFisica extends Pessoa {
	
	private String cpf;
	private final double TAXA_TRANSACAO = 0.0;
	private final double TAXA_POUPANCA = 0.005;
	private final double TAXA_INVESTIMENTO = 0.01;
	
    public PessoaFisica(String nome, String cpf) {
    	super.setNomeCliente(nome);
    	this.cpf = cpf;
    	
    }

	public String getCpf() {
		return cpf;
	}
 
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", Nome=" + getNomeCliente() +  "]";
	}

	@Override
	public String getCFPorCNPJ() {
		// TODO Auto-generated method stub
		return this.cpf;
	}
	
	@Override
	public double getTaxaTributo_in(double taxa) {
		
		double aux = taxa * TAXA_TRANSACAO;
		return super.getTaxaTributo_in(aux);
	}

	@Override
	public double getTaxaPoupanca(double valor) {
		// TODO Auto-generated method stub
		double aux = valor * TAXA_POUPANCA;
		return super.getTaxaPoupanca(aux);
	}

	@Override
	public double getTaxaInvestimento(double valor) {
		// TODO Auto-generated method stub
		double aux = valor * TAXA_INVESTIMENTO;
		return super.getTaxaInvestimento(aux);
	}
	

	


}
