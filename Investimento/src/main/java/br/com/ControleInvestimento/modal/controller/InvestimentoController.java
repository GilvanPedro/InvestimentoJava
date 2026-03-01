package br.com.ControleInvestimento.modal.controller;

import br.com.ControleInvestimento.modal.entity.Investimento;
import br.com.ControleInvestimento.modal.entity.TipoInvestimento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvestimentoController {
    List<Investimento> listaInvestimento = new ArrayList<>();
    Investimento investimento;
    File file;
    FileWriter escrita;

    private String ARQUIVO = "Arquivos/investimentos.json";

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // SALVAR O ARQUIVO
    private void salvarArquivo(){
        try{
            file = new File(ARQUIVO);

            file.getParentFile().mkdirs();

            escrita = new FileWriter(file);
            gson.toJson(listaInvestimento, escrita);
            escrita.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // LER O ARQUIVO
    public void lerArquivo(){
        try{
            file = new  File(ARQUIVO);

            if(!file.exists()){
                listaInvestimento = new ArrayList<>();
                return;
            }

            FileReader leitura = new FileReader(file);
            Type tipoLista = new TypeToken<List<Investimento>>(){}.getType();
            listaInvestimento = gson.fromJson(leitura, tipoLista);

            if(listaInvestimento == null){
                listaInvestimento = new ArrayList<>();
            }

            leitura.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // GERAR O ID
    private long gerarId(){
        long maior = 0;
        for(Investimento i : listaInvestimento){
            if(i.getId()>maior){
                maior = i.getId();
            }
        }

        return maior + 1;
    }

    // ADICIONAR UM NOVO INVESTIMENTO
    // ADICIONAR UM NOVO INVESTIMENTO
    public String adicionarInvestimento(String descricao, TipoInvestimento tipoInvestimento, double valor, boolean internacional){

        lerArquivo();

        if(valor <= 0){
            return "O valor precisa ser maior que 0!";
        }

        if(descricao == null || descricao.isBlank()){
            return "A descrição não pode estar vazia!";
        }

        long id = gerarId();

        Investimento novoInvestimento = new Investimento(
                id,
                descricao,
                tipoInvestimento,
                valor,
                internacional
        );

        listaInvestimento.add(novoInvestimento);

        salvarArquivo();

        return "Investimento adicionado com sucesso!";
    }

    // EDITAR UM INVESTIMENTO DE ACORDO COM O ID
    public String editarInvestimento(long id, double valor){
        lerArquivo();

        for(int i = 0; i < listaInvestimento.size(); i++){
            Investimento in = listaInvestimento.get(i);

            if(in.getId() == id){
                in.setValor(valor);

                salvarArquivo();
                return "Investimento Editado com sucesso!";
            }
        }
        return "Investimento não encontrado";
    }

    // EXIBIR INVESTIMENTOS
    public void exibirInvestimentos(){
        lerArquivo();

        if(listaInvestimento.isEmpty()){
            System.out.println("Nenhum investimento cadastrado!");
            return;
        }

        for(Investimento i : listaInvestimento){
            System.out.println(i);
        }
        System.out.printf("\n------------------------------\nInvestimento Total: R$ %.2f \n------------------------------", investimentoTotal());
    }

    // EXCLUIR UM INVESTIMENTO
    public void deletarInvestimento(long id){
        lerArquivo();

        boolean removido = false;

        for(int i = 0; i < listaInvestimento.size(); i++){
            if(listaInvestimento.get(i).getId() == id){
                listaInvestimento.remove(i);
                removido = true;
                break;
            }
        }

        if(removido){
            salvarArquivo();
        } else{
            System.out.println("Investimento não encontrado!");
        }
    }

    // GERA O VALOR TOTAL DE INVESTIMENTOS
    public double investimentoTotal(){
        double total = 0;
        lerArquivo();

        for(Investimento i : listaInvestimento){
            total = total + i.getValor();
        }

        return total;
    }

    // RETIRAR UM INVESTIMENTO OU REMOVER UM VALOR DELE
    public void retirarInvestimento(){
        lerArquivo();

        Scanner sc = new Scanner(System.in);

        System.out.println("O que deseja fazer:\n1- Retirar um valor\n2- Retirar tudo\n3- Sair");
        int retirada = sc.nextInt();

        if(retirada == 3){
            System.out.println("Saindo...");
            return;
        }

        System.out.print("Digite o id do investimento que deseja retirar: ");
        long idInves = sc.nextLong();

        Investimento invest = getInvestimento(idInves);

        if(invest == null){
            System.out.println("Investimento não encontrado!");
            return;
        }

        if(retirada == 1){

            System.out.print("Digite o valor que quer retirar: ");
            double valor = sc.nextDouble();

            if(valor <= 0){
                System.out.println("O valor precisa ser maior que 0!");
                return;
            }

            if(valor > invest.getValor()){
                System.out.println("Você não pode retirar um valor maior que o investido!");
                return;
            }

            double valorEditado = invest.getValor() - valor;

            if(valorEditado == 0){
                deletarInvestimento(idInves);
                System.out.println("Investimento removido completamente!");
            } else{
                invest.setValor(valorEditado);
                salvarArquivo();
                System.out.println("Valor retirado com sucesso!");
            }

        } else if(retirada == 2){

            deletarInvestimento(idInves);
            System.out.println("Investimento removido com sucesso!");

        } else{
            System.out.println("Opção inválida!");
        }
    }

    // DEVOLVER O INVESTIMENTO
    public Investimento getInvestimento(long id){
        for(Investimento i : listaInvestimento){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }
}
