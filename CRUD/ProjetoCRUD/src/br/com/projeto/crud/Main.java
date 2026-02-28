package br.com.projeto.crud;

import java.util.Scanner;
import java.sql.SQLException; 

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO();
        int opcao = 0;

        System.out.println("=== SISTEMA CRUD POSTGRESQL (MODO COMPATIBILIDADE) ===");

        try {
            while (opcao != 5) {
                System.out.println("\n1-Cadastrar | 2-Listar | 3-Atualizar | 4-Excluir | 5-Sair");
                System.out.print("Escolha uma opção: ");
                
                if (!sc.hasNextInt()) {
                    sc.next();
                    continue;
                }
                
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.next();
                        System.out.print("Preço: ");
                        double preco = sc.nextDouble();
                        dao.salvar(new Produto(0, nome, preco));
                        System.out.println("Sucesso!");
                    }
                    case 2 -> {
                        System.out.println("\n--- LISTAGEM ---");
                        dao.listar().forEach(p -> 
                            System.out.println(p.getId() + " | " + p.getNome() + " | R$ " + p.getPreco()));
                    }
                    case 3 -> {
                        System.out.print("ID para atualizar: ");
                        int id = sc.nextInt();
                        System.out.print("Novo Nome: ");
                        String nNome = sc.next();
                        System.out.print("Novo Preço: ");
                        double nPreco = sc.nextDouble();
                        dao.atualizar(new Produto(id, nNome, nPreco));
                    }
                    case 4 -> {
                        System.out.print("ID para excluir: ");
                        dao.excluir(sc.nextInt());
                    }
                    case 5 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro no Banco de Dados: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro Inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}