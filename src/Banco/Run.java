package Banco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
	public static Scanner scanner = new Scanner(System.in);
	public  static void addPessoa(Conta conta, List<Conta> cadastroPessoas) {
		cadastroPessoas.add(conta);
	}
	
	public static Pessoa cadastrarPessoa() {
		 
		  int tipoPessoa = 0;
			
			System.out.println("SEJA BEM-VINDO AO BANCO JÚPITER");
	       
	        
	        while (true) {
	            try {
	                System.out.println("Que tipo de pessoa você é? ");
	                System.out.println("Digite 1 para Pessoa Física");
	                System.out.println("Digite 2 para Pessoa Jurídica");
	                
	                tipoPessoa = Integer.parseInt(scanner.nextLine());
	                switch (tipoPessoa) {
	                    case 1:
	                    	tipoPessoa = 1;
	                   
	                        break;
	                    case 2:
	                    	tipoPessoa = 2;
	                    	
	                        break;
	                    default:
	                        System.out.println("Selecione um tipo de pessoa válida.");
	                        break;
	                }
	                if(tipoPessoa == 1 || tipoPessoa == 2){
	                    break;
	                }
	            }
	            catch (Exception ex){
	            	
	                System.out.println("Selecione um tipo de pessoa válida.");
	               // scanner.next();
	            }
	        }
	        System.out.println("Informe seu nome");
       	    String nome = scanner.nextLine();
       	   
	        if(tipoPessoa == 1) {
	        	 
	        	 System.out.println("Informe seu cpf");
	        	 String cpf = scanner.nextLine();
	        
	        	 
	        	 return new PessoaFisica(nome, cpf);
	        }else {

	        	 System.out.println("Informe seu cnpj");
	        	 String cnpj = scanner.nextLine();
	        	
	        	 return new PessoaJuridica(nome, cnpj);
	        }
	       
		 
		
	}

	public static Conta abrirConta(Pessoa titular) throws Exception {
		//  Scanner scanner = new Scanner(System.in);
		  
		  int tipoConta = 0;
		  Conta conta = null;
		  int numConta;
	
	        while (true) {
	            try {
	                System.out.println("Que tipo o tipo de conta ");
	                System.out.println("Digite 1 para conta corrente");
	                System.out.println("Digite 2 para conta juridica");
	                System.out.println("Digite 3 para conta investimento");
	                
	            	
	                tipoConta = Integer.parseInt(scanner.nextLine());
	                switch (tipoConta) { 
	                    case 1:
	                    	System.out.println("Informe o numero da conta:");
	                    	numConta = Integer.parseInt(scanner.nextLine());
	                    	conta = new ContaCorrente(Integer.parseInt(titular.getCFPorCNPJ()), numConta, titular);
	                    	System.out.println("******************CONTA CORRENTE CRIADA COM SUCESSO!******************");
	                        break;
	                    case 2:
	                    	tipoConta = 2;
	                    	
	                    	if(titular.getClass() == PessoaJuridica.class){
	                    		System.out.println("Não é possível criar conta do tipo poupança para pessoa jurídica");
	                            throw new Exception("Não é possível criar conta do tipo poupança para pessoa jurídica");
	                        }else {
	                        	System.out.println("Informe o numero da conta:");
	                        	numConta = Integer.parseInt(scanner.nextLine());
	                        	conta = new ContaPoupanca(Integer.parseInt(titular.getCFPorCNPJ()), numConta, titular);
	                        }
	                    	System.out.println("******************CONTA POUPANÇA CRIADA COM SUCESSO!******************");
	                        break;
	                    case 3:
	                    	tipoConta = 3;
	                    	System.out.println("Informe o numero da conta:");
	                    	numConta = Integer.parseInt(scanner.nextLine());
	                    	conta = new ContaInvestimento (Integer.parseInt(titular.getCFPorCNPJ()), numConta, titular);
	                    	System.out.println("******************CONTA INVESTIMENTO CRIADA COM SUCESSO!******************");
	                        break;
	                    default:
	                        System.out.println("Selecione um tipo de pessoa válida.");
	                        break;
	                }
	                if(tipoConta == 1 || tipoConta == 2 || tipoConta == 3){
	                    break;
	                }
	            }
	            catch (Exception ex){
	            	
	                System.out.println("Selecione um tipo de pessoa válida.");
	               // scanner.next();
	            }
	        }
	        
	       // scanner.close();   
		 return conta;
		
	}
	

	public static Conta consultarConta(List<Conta> contas, int num) {
		
		Conta c = contas.stream().filter(conta -> num == (conta.getNumero())).findAny().orElse(null);
		
		return c;
	}
	

	
	public static void consultarSaldo(Conta c) {
		
		System.out.println("SEU SALDO É: ");
		System.out.println(c.getSaldo());
		System.out.println();
		
		//esperarEnter();
	}
	
	
	public static void depositar(Conta c, double valor) {
		c.deposita(valor);
		
	}
	
	public static boolean sacar(Conta c, double valor) {
		if(c.saca(valor + c.getTitular().getTaxaTributo_in(valor))) {
			//System.out.println();
			return true;
		}else return false;
		
	}
	 public static void listarContas(List<Conta> contas) throws IOException {
	        if(contas.stream().count() == 0){
	            System.out.println("Você não possui nenhum conta.");
	            return;
	        }
	        System.out.println("As suas Contas são as seguintes:");
	        //Conta conta;
			for (Conta conta : contas) {
	            System.out.println("AGENCIA: "+conta.getAgencia());
	            System.out.println("NUMERO: "+conta.getNumero());
	            System.out.println("------------------------");
	        }
			//esperarEnter();
	    }
	 
	 public static void transferir(List<Conta> contas, int origem, int destino, double valor) {
		
		 if(sacar(consultarConta(contas,origem),valor)) {
			 depositar(consultarConta(contas,destino),valor);
		 }else System.out.println("Erro ao realizar transferência");
		
		 //esperarEnter();
		
	}
	 
	public static void simularRendimentoPoupanca(List<Conta> contas, int numConta,int totAno) {
		//o rendimento foi adotado apenas o rendimento anual com juros simples
		
		double rendimento = 0.0;
		Conta c = consultarConta(contas,numConta);
		for(int i = 0; i < totAno; i++) {
			rendimento += c.getTitular().getTaxaPoupanca(c.getSaldo());
		}
	
		
		depositar(c,rendimento);
		
		System.out.println("***************** RENDIMENTO REALIZADO EM POUPANÇA. FAVOR CONSULTAR SEU SALDO NOVAMENTE *****************");
		consultarSaldo(c);
		//esperarEnter();
	}
	
	public static void simularInvestimento(List<Conta> contas, int numConta, int totAno) {
		
		double rendimento = 0.0;
		Conta c = consultarConta(contas,numConta);
		for(int i = 0; i < totAno; i++) {
			rendimento += c.getTitular().getTaxaInvestimento(c.getSaldo());
		}
		
		
		depositar(c,rendimento);
		
		System.out.println("***************** RENDIMENTO REALIZADO EM INVESTIMENTO. FAVOR CONSULTAR SEU SALDO NOVAMENTE *****************");
		consultarSaldo(c);
		//esperarEnter();
		
	}
	
	public static void esperarEnter1() {
		System.out.println("TECLE ENTER PARA CONTINUAR");
		//String n = scanner.nextLine();
		 try{
	            System.in.read();
	        }  
	     catch(Exception e){}  
	}

	public static void main(String[] args) throws Exception {
	  
	    
		List<Conta> contas = new ArrayList<>();
		Pessoa p = cadastrarPessoa();
		
	//	System.out.println("************CADASTRO REALIZADO COM SUCESSO*************\n");
    	//esperarEnter();
			
		System.out.println("************** SEJA BEM-VINDO " + p.getNomeCliente() + " AO BANCO JÚPITER **************");
		
		int opcao = 0, numConta =0;
		double valor;
		Scanner scanner = new Scanner(System.in);
		while (true)
        {
            try {
               
                System.out.println("O que você deseja fazer?");
                System.out.println("1 Abrir Conta");
                System.out.println("2 Realizar Saque");
                System.out.println("3 Realizar Deposito");
                System.out.println("4 Realizar transferência");
                System.out.println("5 Calcular Rendimento em conta POUPANÇA");
                System.out.println("6 Consultar Saldo");
                System.out.println("7 Calcular Rendimento em conta INVESTIMENTO");
                System.out.println("8 Listar Todas as suas Contas");
               
                System.out.println("0 Para Sair");
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao){
                	
                    case 1:
                    	System.out.println("************** VOCÊ SELECIONOU ABRIR UMA NOVA CONTA **************");
                    
                    	addPessoa(abrirConta(p), contas);
                    	
                    	//esperarEnter();
                        break;
                        
                    case 2:
                    	System.out.println("Informe o numero da conta a sacar ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe o valor para sacar");
                    	valor = Double.parseDouble(scanner.nextLine());
                    	if(sacar(consultarConta(contas,numConta),valor)) {
                    		//System.out.println("Saque realizado com sucesso!\n");
                    		System.out.println("************SAQUE REALIZADO COM SUCESSO*************");
                    	}else System.out.println("Erro ao realizar saque");
                    	//esperarEnter();
                        break;
                        
                    case 3:
                    	System.out.println("Informe o numero da conta para depositar ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe o valor para deposicar ");
                    	valor = Double.parseDouble(scanner.nextLine());
                    	depositar(consultarConta(contas,numConta),valor);
                    	System.out.println("************DEPOSITO REALIZADO COM SUCESSO*************");
                    	//esperarEnter();
                        break;
                        
                    case 4:
                    	System.out.println("Informe a conta de origem ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe a conta de destino ");
                    	int numdestino = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe o valor para transferir ");
                    	valor = Double.parseDouble(scanner.nextLine());
                    	transferir(contas,numConta,numdestino,valor);
                    	//esperarEnter();
                        break;
                        
                    case 5:
                    	System.out.println("Informe a conta para simular ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe Total de ano ");
                    	int ano = Integer.parseInt(scanner.nextLine());
                    	simularRendimentoPoupanca(contas, numConta,ano);
                    	System.out.println("************RENDIMENTO POUPANÇA REALIZADO COM SUCESSO*************");
                    //	esperarEnter();
                        break;
                        
                    case 6:
                    	System.out.println("Informe o numero da conta para ver saldo ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	consultarSaldo(consultarConta(contas,numConta));
                    	//esperarEnter();
                        break;
                        
                    case 7:
                    	System.out.println("Informe a conta para simular ");
                    	numConta = Integer.parseInt(scanner.nextLine());
                    	System.out.println("Informe Total de ano ");
                    	int ano1 = Integer.parseInt(scanner.nextLine());
                    	simularInvestimento(contas, numConta,ano1);
                    	System.out.println("************INVESTIMENTO REALIZADO COM SUCESSO*************");
                    	//esperarEnter();
                        break;
                        
                    case 8:
                    	listarContas(contas);
                    	//esperarEnter();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Selecione uma opção válida");
                        break;
                }
                if(opcao == 0)
                {
                    System.out.println("Volte Sempre ao Banco JÚPITER!");
                    break;
                }
            }
            catch (Exception ex){
                System.out.println("Selecione uma opção válida");
                scanner.next();
            }
        }
		 scanner.close();
		
	}
}
