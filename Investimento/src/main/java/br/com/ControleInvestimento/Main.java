package br.com.ControleInvestimento;

import br.com.ControleInvestimento.modal.controller.InvestimentoController;
import br.com.ControleInvestimento.modal.entity.TipoInvestimento;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        InvestimentoController controller = new InvestimentoController();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n===== CONTROLE DE INVESTIMENTOS =====");
            System.out.println("1 - Adicionar Investimento");
            System.out.println("2 - Editar Investimento");
            System.out.println("3 - Exibir Investimentos");
            System.out.println("4 - Retirar Investimento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();

                    System.out.println("Tipo de Investimento:");
                    for (TipoInvestimento tipo : TipoInvestimento.values()) {
                        System.out.println(tipo.ordinal() + " - " + tipo.name());
                    }

                    System.out.print("Escolha o tipo: ");
                    int tipoEscolhido = sc.nextInt();
                    sc.nextLine();

                    TipoInvestimento tipo = TipoInvestimento.values()[tipoEscolhido];

                    System.out.print("Valor: ");
                    String valorDigitado = sc.nextLine().replace(",", ".");

                    double valor = Double.parseDouble(valorDigitado);

                    System.out.print("É internacional? (true/false): ");
                    boolean internacional = sc.nextBoolean();

                    String resultado = controller.adicionarInvestimento(
                            descricao,
                            tipo,
                            valor,
                            internacional
                    );

                    System.out.println(resultado);
                    break;

                case 2:
                    System.out.print("Digite o ID do investimento: ");
                    long idEditar = sc.nextLong();

                    System.out.print("Novo valor: ");
                    double novoValor = sc.nextDouble();

                    System.out.println(controller.editarInvestimento(idEditar, novoValor));
                    break;

                case 3:
                    controller.exibirInvestimentos();
                    break;

                case 4:
                    controller.retirarInvestimento();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}