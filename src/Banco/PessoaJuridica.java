package Banco;

public class PessoaJuridica extends Pessoa {
	
	private String cnpj;
	private final double TAXA_TRANSACAO = 0.005;
	private final double TAXA_INVESTIMENTO = 0.03;
	
	 public PessoaJuridica(String nome, String cnpj) {
	    	super.setNomeCliente(nome);
	    	this.cnpj = cnpj;
	    	
	    }



	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public double getTaxaTributo_in(double taxa) {
		// TODO Auto-generated method stub
		double aux = taxa * TAXA_TRANSACAO;
		return super.getTaxaTributo_in(aux);
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", Nome=" + getNomeCliente() +  "]";
	}

	@Override
	public String getCFPorCNPJ() {
		// TODO Auto-generated method stub
		return this.cnpj;
	}
	
	@Override
	public double getTaxaInvestimento(double valor) {
		// TODO Auto-generated method stub
		double aux = valor * TAXA_INVESTIMENTO;
		return super.getTaxaInvestimento(aux);
	}
	

}
