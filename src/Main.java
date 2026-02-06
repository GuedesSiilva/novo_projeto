import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

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
        System.out.println(" == Alunos Listados == ");
        if(listaAlunos.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
        }else{
            for (Aluno A : listaAlunos) {
                System.out.println(A);
            }
        }
        Alunos();
    }
    public static void add_aluno (){
        String Nome = Leitura.dados("Digite o nome do aluno:");
        String data = Leitura.dados("Digite a data de nascimento do aluno:");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(data, formatter);

        Aluno aluno = new Aluno(Nome,dataNascimento);
        System.out.println("Aluno Criado com sucesso");
    }
    public static void edit_aluno (){

    }
    public static void excluir_aluno (){

    }
    public static void listar_turmas (){
        System.out.println(" == Alunos Listados == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(T);
            }
        }
    }
    public static void add_turma (){
        String Curso = Leitura.dados("Digite o nome do curso:");
        String Sigla = Leitura.dados("Digite a sigla referente ao curso:");
        String Periodo = Leitura.dados("""
                Escolha qual é o periodo da turma:
                - MATUTINO
                - VESPERTINO
                - NOTURNO
                - INTEGRAL
                """);


        System.out.println("Turma Criada com sucesso");
    }
    public static void edit_turma (){

    }
    public static void excluir_turma (){

    }
}