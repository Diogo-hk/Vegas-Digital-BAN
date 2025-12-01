package Controller;

import Conexao.FuncionarioModel;
import Dados.Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class FuncionarioController {
    public void createFuncionarioController(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo Funcionário: ");

        System.out.print("idFunc: ");
        int idFunc = input.nextInt();

        System.out.print("idSessao: ");
        int idSessao = input.nextInt();

        System.out.print("idCassino: ");
        int idCassino = input.nextInt();
        input.nextLine(); // Consumir quebra de linha

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("CPF: ");
        String cpf = input.nextLine();

        Funcionario func = new Funcionario(idFunc, idSessao, idCassino, nome, cpf);
        FuncionarioModel.create(func, con);
        System.out.println("Funcionário cadastrado com sucesso!!");
    }

    public void listarFuncionarios(Connection con) throws SQLException {
        HashSet all = FuncionarioModel.listAll(con);
        Iterator<Funcionario> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}