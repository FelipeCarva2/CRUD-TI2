package com.ti2cc;
import java.util.Scanner; 

public class Principal {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int op=0;
		
		DAO dao = new DAO();
		dao.conectar();

		Cachorro cachorro = new Cachorro();
		Cachorro[] cachorros = dao.getCachorros();
		String nome;
		String raca;
		int codigo;
		char sexo;
		
		
		while(op!=5) {
			
			System.out.println("\n\n==== -MENU- ====");
			System.out.println("1-Listar");
			System.out.println("2-Inserir");
			System.out.println("3-Excluir");
			System.out.println("4-Atualizar");
			System.out.println("5-Sair");
			System.out.println("\nDigite uma opção: ");
		    op = sc.nextInt();
		    sc.nextLine();
			switch (op) {
		    case 1:
		        System.out.println("\t\tLISTANDO\n");
		        
		        System.out.println("==== Mostrar Cachorros=== ");
				for(int i = 0; i < cachorros.length; i++) {
					System.out.println(cachorros[i].toString());
				}
		        
		        break;
		    case 2:
		    	System.out.println("\t\tINSERINDO\n");
		    	
		      	System.out.println("Digite o nome: ");
		      	nome=sc.nextLine();
		      	cachorro.setNome(nome);
		      	System.out.println("Digite a raca: ");
		      	raca =sc.nextLine();
		      	cachorro.setRaca(raca);
		      	System.out.println("Digite o sexo: ");
		      	sexo=sc.next().charAt(0);
		      	cachorro.setSexo(sexo);
		      	System.out.println("Digite o codigo: ");
		      	codigo=sc.nextInt();
		      	cachorro.setCodigo(codigo);
		      	
		    	//dao.inserirCachorro(cachorro); 
		    	if(dao.inserirCachorro(cachorro) == true) {
					System.out.println("Inserção com sucesso -> " + cachorro.toString());
				}
		    	
		    	
		        break;
		    case 3:
		    	System.out.println("\t\tEXCLUINDO\n");
		    	System.out.println("Digite o codigo: ");
		      	codigo=sc.nextInt();
		      	cachorro.setCodigo(codigo);
		    	dao.excluirCachorro(cachorro.getCodigo());
		        break;
		    case 4:
		    	System.out.println("\t\tATUALIZANDO\n");
		    	System.out.println("Digite o nome: ");
		      	nome=sc.nextLine();
		      	cachorro.setNome(nome);
		      	System.out.println("Digite a raca: ");
		      	raca =sc.nextLine();
		      	cachorro.setRaca(raca);
		      	System.out.println("Digite o sexo: ");
		      	sexo=sc.next().charAt(0);
		      	cachorro.setSexo(sexo);
		      	System.out.println("Digite o codigo: ");
		      	codigo=sc.nextInt();
		      	cachorro.setCodigo(codigo);
		    	
				dao.atualizarCachorro(cachorro);
		    	break;
		    case 5:
		    	System.out.println("\t\tSAINDO\n");
		    	
		    	break;
		    default:
		    	System.out.println("Digite uma opção válida: ");
		        break;
		}

			cachorros = dao.getCachorros();
		}
		
		dao.close();
	}
}