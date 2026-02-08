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
            Alunos();
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
        System.out.println(" == Turmas Disponiveis == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            return;
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(" - " + T);
            }
        }
        String sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
        Turma turma = null;
        for (Turma T : listaTurmas) {
            if (T.getSigla().equalsIgnoreCase(sigla)){
            turma = T;
            break;
            }
        }
        if (turma == null){
            System.out.println("Turma não encontrada. Aluno não cadastrado");
            Alunos();
        }
        LocalDate dataNascimento = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(data, formatter);
        }catch (Exception e){
            System.out.println("Data Inválida! Use o formato dd/mm/yyyy");
            return;
        }
        Aluno aluno = new Aluno(Nome,dataNascimento,turma);
        System.out.println("Aluno Criado com sucesso");
        listaAlunos.add(aluno);
        Alunos();
    }
    public static void edit_aluno (){

    }
    public static void excluir_aluno (){
        System.out.println(" == Alunos cadastrados no sistema == ");
        if(listaAlunos.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Alunos();
        }else{
            for (Aluno A : listaAlunos) {
                System.out.println(" - " +A);
            }
        }
        String id = Leitura.dados("Digite o id do aluno a ser removido:");
        int idSelecionado = Integer.parseInt(id);
        Aluno aluno = null ;
        for (Aluno A : listaAlunos) {
            if (A.getId() == idSelecionado){
                aluno = A;
                break;
            }
        }
        if (aluno == null){
            System.out.println("Aluno não encontrado.");
        }
        else {
            listaTurmas.remove(aluno);
            System.out.println("Aluno Removido com sucesso!");
        }
        Alunos();
    }
    public static void listar_turmas (){
        System.out.println(" == Turmas Disponiveis == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Turmas();
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(T);
            }
        }
        Turmas();
    }
    public static void add_turma (){
        String Curso = Leitura.dados("Digite o nome do curso:");
        String Sigla = Leitura.dados("Digite a sigla referente ao curso:");
        String escolha = Leitura.dados("""
                Escolha qual é o periodo da turma:
                1 - MATUTINO
                2 - VESPERTINO
                3 - NOTURNO
                4 - INTEGRAL
                """);
        int opcao = Integer.parseInt(escolha);
        Periodo[] periodos = Periodo.values();
        Periodo periodoEscolhido = periodos[opcao-1];

        Turma turma = new Turma(Curso,Sigla,periodoEscolhido);
        System.out.println("Turma criada com sucesso!!");
        listaTurmas.add(turma);
        Turmas();
    }
    public static void edit_turma (){

    }
    public static void excluir_turma (){
        System.out.println(" == Turmas Disponiveis == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Alunos();
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(" - " +T);
            }
        }
        String sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
        Turma turma = null;
        for (Turma T : listaTurmas) {
            if (T.getSigla().equalsIgnoreCase(sigla)){
                turma = T;
                break;
            }
        }
        if (turma == null){
            System.out.println("Turma não encontrada.");
        }
        else {
            listaTurmas.remove(turma);
            System.out.println("Turma Removida com sucesso!");
        }
    }
}