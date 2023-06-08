package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.util.Cores;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;



public class Menu {

		public static void main(String[] args) {
			
			ContaController contas = new ContaController();
			
			
		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;
				
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "José", 1000, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 126, 2, "Juliana", 8000f, 15);
		contas.cadastrar(cp2);
		
		
		Scanner leia = new Scanner(System.in);		
		
		while (true) {
		
		System.out.println(Cores.TEXT_PURPLE_BOLD_BRIGHT);	
		System.out.println("************************************************************");	
		System.out.println("                                                            ");	
		System.out.println("                    BANCO GENERATION                        ");	
		System.out.println("                                                            ");		
		System.out.println("************************************************************");	
		System.out.println("                                                            ");	
		System.out.println("                 1 - Criar Conta                            ");	
		System.out.println("                 2 - Listar todas as Contas                 ");	
		System.out.println("                 3 - Buscar conta por Número                ");	
		System.out.println("                 4 - Atualizar Dados da Conta               ");	
		System.out.println("                 5 - Apagar Conta                           ");   	
		System.out.println("                 6 - Sacar                                  ");	
		System.out.println("                 7 - Depositar                              ");	
		System.out.println("                 8 - Transferir valores entres Contas       ");	
		System.out.println("                 9 - Sair                                   ");	
		System.out.println("                                                            ");	
		System.out.println("************************************************************");
		System.out.println("                 Entre com a opção desejada:                ");	
		System.out.println("                                                            ");	
			
		try {
			opcao = leia.nextInt();		
		} catch (InputMismatchException e) {
			System.out.println("\nDigite valores inteiros!");
			leia.nextLine();
			opcao=0;
		}
				
			if (opcao == 9) {
		System.out.println(Cores.TEXT_YELLOW_BOLD +"\n\t   Banco Generation - Sempre com você!");
		leia.close();
		System.exit(0);	
	}
		switch (opcao) {
		
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Criar Conta \n\n");
				
				System.out.println("Digite o Número da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				}while (tipo < 1 && tipo > 2);
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
					
				case 1 -> { 
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente (contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
			}
				
			keyPress();
			break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Listar todas as Contas \n\n");
				contas.listarTodas();
				keyPress();
			break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Consultar dados da Conta - por número \n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
			break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero= leia.nextInt();
				
				if (contas.buscarNaCollection(numero) != null) {
					
					System.out.println("Digite o Numero da Agência: ");
					agencia=leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular=leia.nextLine();

					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();

					tipo = contas.retornaTipo(numero);
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}
					
				} else 
					System.out.println("\nConta não encontrada!");
				keyPress();
			break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Apagar a conta \n\n");
				
			System.out.println("Digite o numero da conta: ");
			numero = leia.nextInt();
			contas.deletar(numero);
			
				keyPress();
			break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Saque \n\n");
				keyPress();
			break;
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Depósito \n\n");
				keyPress();
			break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD +"Transferência entre Contas \n\n");
				keyPress();
			break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD +"\nOpção Inválida!\n");
				keyPress();
				break;
			}
		
			}
		
		}
		
		public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione" + Cores.TEXT_GREEN_BOLD + " Enter " + Cores.TEXT_RESET + "para Continuar...");
			System.in.read();
			
		} catch ( IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}

	}
}
