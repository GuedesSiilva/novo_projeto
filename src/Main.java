import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        System.out.println(" == Secretaria == ");
        System.out.println("""
                1 - Alunos
                2 - Turmas
                3 - Sair
                """);
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao){
            case "1":
                opcao="1";
                Alunos();
                break;
            case "2":
                opcao="2";
                Turmas();
                break;
            case "3":
                opcao="3";
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Digite uma opção VÁLIDA");
        }
    }
    public static void Alunos() {
        System.out.println(" == Alunos == ");
        System.out.println("""
                1 - Listar alunos
                2 - Cadastrar alunos
                3 - Editar alunos
                4 - Excluir alunos
                5 - Voltar ao menu principal
                """);
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao){
            case "1":
                listar_alunos();
                break;
            case "2":
                add_aluno();
                break;
            case "3":
                edit_aluno();
                break;
            case "4":
                excluir_aluno();
                break;
            case "5":
                menu();
                break;
            default:
                System.out.println("Digite uma opção VÁLIDA");
        }
    }
    public static void Turmas() {
        System.out.println(" == Turmas == ");
        System.out.println("""
                1 - Listar turmas
                2 - Cadastrar turmas
                3 - Editar turmas
                4 - Excluir turmas
                5 - Voltar ao menu principal
                """);
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao){
            case "1":
                listar_turmas();
                break;
            case "2":
                add_turma();
                break;
            case "3":
                edit_turma();
                break;
            case "4":
                excluir_turma();
                break;
            case "5":
                menu();
                break;
            default:
                System.out.println("Digite uma opção VÁLIDA");
        }
    }
    public static void listar_alunos (){
        for(Aluno a : listaAlunos){
            System.out.println(a);
        }
    }
    public static void add_aluno (){

    }
    public static void edit_aluno (){

    }
    public static void excluir_aluno (){

    }
    public static void listar_turmas (){
        for(Turma t : listaTurmas){
            System.out.println(t);
        }
    }
    public static void add_turma (){

    }
    public static void edit_turma (){

    }
    public static void excluir_turma (){

    }
}