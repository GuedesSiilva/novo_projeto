import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
                Alunos();
                break;
            case "2":
                Turmas();
                break;
            case "3":
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Digite uma opção VÁLIDA");
                menu();
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
                Alunos();
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
                Turmas();
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
    public static boolean validarDataNascimento(String data){
        if (data.isBlank()) return false;

        LocalDate dataNascimento = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(data, formatter);

        } catch (Exception e) {
            System.out.println("Data Inválida! Use o formato dd/mm/yyyy");
            return false;
        }
        return true;
    }
    public static void add_aluno (){
        String Nome = Leitura.dados("Digite o nome do aluno:");
        while (ValidarTextos(Nome)) {
            System.out.println("Nome INVÁLIDO! Não utilize numeros e nem caracteres especias");
            Nome = Leitura.dados("Digite o nome do aluno:");
        }
        String data;
        do{
             data = Leitura.dados("Digite a data de nascimento do aluno:");
        }while (!validarDataNascimento(data));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(data, formatter);

        if (listaTurmas.isEmpty()) {
            System.out.println("É impossível cadastrar aluno sem turmas existentes\n");
            Alunos();
        } else {
            System.out.println(" == Turmas Disponiveis == ");
            for (Turma T : listaTurmas) {
                System.out.println(" - " + T);
            }
        }
        Turma turma = null;
        while (turma == null) {
            String sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
            for (Turma T : listaTurmas) {
                if (T.getSigla().equalsIgnoreCase(sigla)) {
                    turma = T;
                    break;
                }
            }
            if (turma == null) {
                System.out.println("Turma não encontrada.");
            }
        }
        Aluno aluno = new Aluno(Nome, dataNascimento, turma);
        System.out.println("Aluno Criado com sucesso");
        listaAlunos.add(aluno);
        Alunos();
    }
    public static void edit_aluno (){
        System.out.println(" == Alunos Listados == ");
        if(listaAlunos.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Alunos();
        }else {
            for (Aluno A : listaAlunos) {
                System.out.println(A);
            }
        }
        int idAluno = -1;
        while (idAluno == -1){
            String escolha = Leitura.dados("Digite o id do aluno a ser editado:");
            try {
                    idAluno = Integer.parseInt(escolha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números.");
            }
        }
        boolean achou = false;
        for (Aluno A : listaAlunos){
            if(A.getId() == idAluno){
                achou = true;
                if (Confirmacao()){
                    System.out.println("O nome atual é " + A.getNome());
                    String escolha = Leitura.dados("Voce desejar alterar o nome atual? [S/N]");
                    escolha = escolha.toUpperCase();
                    switch (escolha){
                        case "S":
                            String NovoNome = Leitura.dados("Qual o novo nome da lista?");
                            while (ValidarTextos(NovoNome)) {
                                System.out.println("Nome INVÁLIDO! Não utilize numeros e nem caracteres especias");
                                NovoNome = Leitura.dados("Digite o nome do aluno:");
                            }
                            A.setNome(NovoNome);
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                    System.out.println("A data de nascimento atual é " + A.getDataNascimento());
                    String escolhaData = Leitura.dados("Voce desejar alterar a data de nascimento atual? [S/N]");
                    escolhaData = escolhaData.toUpperCase();
                    switch (escolhaData){
                        case "S":
                            String data = Leitura.dados("Digite a data de nascimento do aluno:");
                            LocalDate Novadata;
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                Novadata = LocalDate.parse(data, formatter);
                            }catch (Exception e){
                                System.out.println("Data Inválida! Use o formato dd/mm/yyyy");
                                return;
                            }
                            A.setDataNascimento(Novadata);
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                    System.out.println("O curso atual de " + A.getNome() + " é: " + listaTurmas.get(idAluno - 1).getCurso());
                    String escolhaCurso = Leitura.dados("Voce desejar alterar o curso atual? [S/N]");
                    escolhaCurso = escolhaCurso.toUpperCase();
                    switch (escolhaCurso) {
                        case "S":
                            String sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
                            while (sigla.isBlank() && sigla.length() > 5) {
                                System.out.println("Sigla INVÁLIDA");
                                sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
                            }
                            sigla = sigla.toUpperCase();
                            Turma NovaTurma = null;
                            for (Turma T : listaTurmas) {
                                if (T.getSigla().equalsIgnoreCase(sigla)) {
                                    NovaTurma = T;
                                    break;
                                }
                            }
                            if (NovaTurma == null) {
                                System.out.println("Turma não encontrada. Aluno não cadastrado");
                                Alunos();
                            }
                            A.setTurma(NovaTurma);
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                }
                System.out.println("Aluno editado com Sucesso!");
                break;
            }
        }
    if (!achou){
            System.out.println("Aluno não existe, Digite uma opção VÁLIDA!!");
            edit_aluno();
        }
        Alunos();
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
        else if (Confirmacao()){
            listaAlunos.remove(aluno);
            System.out.println("Aluno removido com sucesso!");
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
        String escolha;
        while (true) {
            escolha = Leitura.dados("""
            Escolha qual é o periodo da turma:
            1 - MATUTINO
            2 - VESPERTINO
            3 - NOTURNO
            4 - INTEGRAL
            """);
            if (escolha.equals("1") || escolha.equals("2") || escolha.equals("3") || escolha.equals("4")) {
                break;
            }
            System.out.println("Digite uma opção válida!");
        }
        int opcao = Integer.parseInt(escolha);
        Periodo[] periodos = Periodo.values();
        Periodo periodoEscolhido = periodos[opcao - 1];
        String Curso = Leitura.dados("Digite o nome do curso:");
        while (ValidarTextos(Curso)){
            System.out.println("Nome INVÁLIDO! Não utilize numeros");
            Curso = Leitura.dados("Digite o nome do curso:");
        }
        String sigla = Leitura.dados("Digite a sigla referente ao curso:");

        while(ValidarSigla(sigla)){
            System.out.println("Sigla INVÁLIDA! Não pode ser repetida, nem maior que 6 caracteres.");
            sigla = Leitura.dados("Digite a sigla referente ao curso:");
        }

        sigla = sigla.toUpperCase();
        Turma turma = new Turma(Curso,sigla,periodoEscolhido);
        listaTurmas.add(turma);
        System.out.println("Turma criada com sucesso!!");

        Turmas();
    }

    public static void edit_turma (){
        System.out.println(" == Turmas Disponiveis == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Alunos();
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(" - " +T);
            }
        }
        String escolha;
        String sigla = Leitura.dados("Digite a sigla da turma a ser editada:");
        sigla = sigla.toUpperCase();
        Turma NovaTurma = null;
        for (Turma T : listaTurmas) {
            if (T.getSigla().equalsIgnoreCase(sigla)){
                NovaTurma = T;
                if(Confirmacao()){
                    System.out.println("O perido atual da turma "+ T.getSigla() + " é: " + T.getPeriodo());
                    escolha = Leitura.dados("Deseja alterar o periodo atual? [S/N]");
                    escolha = escolha.toUpperCase();
                    switch (escolha) {
                        case "S":
                            while (true) {
                                escolha = Leitura.dados("""
                                Escolha qual é o periodo da turma:
                                1 - MATUTINO
                                2 - VESPERTINO
                                3 - NOTURNO
                                4 - INTEGRAL
                                """);
                                if (escolha.equals("1") || escolha.equals("2") || escolha.equals("3") || escolha.equals("4")) {
                                    break;
                                }
                                System.out.println("Digite uma opção válida!");
                            }
                            int opcao = Integer.parseInt(escolha);
                            Periodo[] periodos = Periodo.values();
                            Periodo periodoEscolhido = periodos[opcao-1];
                            T.setPeriodo(periodoEscolhido);
                            System.out.println("== Periodo Atualizado com Sucesso ==");
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                    System.out.println("O Curso atual da turma "+ T.getSigla() + " é: " + T.getCurso());
                    String escolhaCurso = Leitura.dados("Deseja alterar o curso atual? [S/N]");
                    escolhaCurso = escolhaCurso.toUpperCase();
                    switch (escolhaCurso){
                        case "S":
                            String NovoCurso = Leitura.dados("Digite o nome do curso:");
                            while (ValidarTextos(NovoCurso)) {
                                System.out.println("Nome INVÁLIDO! Não utilize números e nem caracteres especias");
                                NovoCurso = Leitura.dados("Digite o nome do aluno:");
                            }
                            T.setCurso(NovoCurso);
                            System.out.println("== Curso Atualizado com Sucesso ==");
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                    System.out.println("A sigla atual é " + T.getSigla());
                    String escolhaSigla = Leitura.dados("Deseja alterar a sigla atual? [S/N]");
                    escolhaSigla = escolhaSigla.toUpperCase();
                    switch (escolhaSigla){
                        case "S":
                            String NovaSigla = Leitura.dados("Digite a sigla referente ao curso:");
                            while(ValidarSigla(NovaSigla)){
                                System.out.println("Sigla INVÁLIDA! Não pode ser repetida, nem maior que 6 caracteres.");
                                sigla = Leitura.dados("Digite a sigla da turma a ser editada:");
                            }
                            T.setSigla(NovaSigla);
                            System.out.println("== Sigla Atualizada com Sucesso ==");
                        case "N":
                            break;
                        default:
                            System.out.println("Opção inválida digite S ou N!!");
                    }
                }
            }
        }
        if (NovaTurma == null){
            System.out.println("Turma não encontrada. Digite novamente!!");
            edit_turma();
        }
        Turmas();
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
            excluir_turma();
        }
        else if (Confirmacao()){
            listaTurmas.remove(turma);
            System.out.println("Turma Removida com sucesso!");
        }
        Turmas();
    }

    public static boolean ValidarTextos(String Texto){
        String TextoSemNumeros = Texto.replaceAll("\\d","");
        return Texto.isBlank() || !Texto.equals(TextoSemNumeros);
    }

    public static boolean ValidarSigla(String sigla){
        if (sigla.isBlank()) return true;

        for (Turma T : listaTurmas)
        {
            if (T.getSigla().equalsIgnoreCase(sigla )){
                return true;
            }
        }
        String SiglaCorreta = sigla.replaceAll("\\s","");

        return !sigla.equals(SiglaCorreta) || sigla.length() >= 7;
    }

    public static boolean Confirmacao (){
        String confirmar = Leitura.dados("Você tem certeza ? (S/N)");
        confirmar = confirmar.toUpperCase();

        return switch (confirmar) {
            case "S" -> true;
            case "N" -> false;
            default -> {
                System.out.println("Opção inválida, digite S para sim ou N para não");
                yield Confirmacao();
            }
        };
    }
}