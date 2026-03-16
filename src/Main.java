import java.lang.reflect.InvocationTargetException;
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
                listarAlunos();
                break;
            case "2":
                addAluno();
                break;
            case "3":
                editAluno();
                break;
            case "4":
                excluirAluno();
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
                listarTurmas();
                Turmas();
                break;
            case "2":
                addTurma();
                Turmas();
                break;
            case "3":
                editTurma();
                Turmas();
                break;
            case "4":
                excluirTurma();
                Turmas();
                break;
            case "5":
                menu();
                break;
            default:
                System.out.println("Digite uma opção VÁLIDA");
                Turmas();
        }
    }
    public static void listarAlunos(){
        System.out.println(" == Alunos Listados == ");
        if(listaAlunos.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Alunos();
        }else{
            for (Aluno A : listaAlunos) {
                System.out.println(A);
            }
        }
    }
    public static boolean validarDataNascimento(String data){
        if (data.isBlank()) return false;


        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(data, formatter);

        } catch (Exception e) {
            System.out.println("Data Inválida! Use o formato dd/mm/yyyy");
            return false;
        }
        return true;
    }

    public static void addAluno(){
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
    public static void editAluno(){
       listarAlunos();
        int idEditar = -1;
        while (idEditar == -1){
            String escolha = Leitura.dados("Digite o id do aluno a ser editado:");
            try {
                    idEditar = Integer.parseInt(escolha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números.");
            }
        }
        boolean achou = false;
        for (Aluno A : listaAlunos){
            if(A.getId() == idEditar){
                achou = true;
                if (Confirmacao("Você tem certeza??")){

                    System.out.println("O nome atual é " + A.getNome());
                    if (Confirmacao("Você deseja alterar o nome? "))
                           // atualizarNome(A);

                    System.out.println("A data de nascimento atual é " + A.getDataNascimento());
                    if (Confirmacao("Você deseja alterar a idade?"))
                        atualizarIdade(A);

                    System.out.println("O curso atual de " + A.getNome() + " é: " + listaTurmas.get(idEditar - 1).getCurso());
                   if (Confirmacao("Deseja alterar o curso?"))
                       atualizarCursoAluno(A);
                }
                System.out.println("Aluno editado com Sucesso!");
                break;
            }
        }
    if (!achou){
            System.out.println("Aluno não existe, Digite uma opção VÁLIDA!!");
            editAluno();
        }
        Alunos();
    }
    public static void excluirAluno(){
       listarAlunos();
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
        else if (Confirmacao("Você tem certeza?")){
            listaAlunos.remove(aluno);
            System.out.println("Aluno removido com sucesso!");
        }
        Alunos();
    }
    public static void listarTurmas(){
        System.out.println(" == Turmas Disponiveis == ");
        if(listaTurmas.isEmpty()) {
            System.out.println("Está lista está vazia!!! \n");
            Turmas();
        }else{
            for (Turma T : listaTurmas) {
                System.out.println(T);
            }
        }
    }
    public static void addTurma(){
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
    }

    public static void editTurma(){
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();

        int idAtualizar = validaIdTurma();

        System.out.printf("O período atual é: %s", listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("período", idAtualizar);

        System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("curso", idAtualizar);

        System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("sigla", idAtualizar);

    }
    private static void excluirTurma() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

        if (Confirmacao("Deseja excluir está turma??")){
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluída com sucesso!");
        }
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

    public static boolean Confirmacao (String mensagem){
        String confirmar = Leitura.dados(mensagem + " (S/N)");
        confirmar = confirmar.toUpperCase();

        return switch (confirmar) {
            case "S" -> true;
            case "N" -> false;
            default -> {
                System.out.println("Opção inválida, digite S para sim ou N para não");
                yield Confirmacao(mensagem);
            }
        };
    }

    private static <T extends Ativavel> boolean isVazio(ArrayList<T> lista) {
        if (lista.isEmpty()) return true;

        for (T item : lista){
            if (item.isAtivo()) return false;
        }

        return true;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas:");
        for (int i=0;i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }


    public static void atualizarParcial(String atributo, int idEditar){
        boolean rodarNovamente = true;
        while (rodarNovamente){
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "período":
                            Periodo periodo = validarPeriodo();
                            listaTurmas.get(idEditar).setPeriodo(periodo);
                            break;
                        case "curso":
                            String curso = validarCursoTurma();
                            listaTurmas.get(idEditar).setCurso(curso);
                            break;
                        case "sigla":
                            String sigla = validarSiglaTurma();
                            sigla = sigla.toUpperCase();
                            listaTurmas.get(idEditar).setSigla(sigla);
                            break;
                    }
                    System.out.println(atributo + " atualizado com sucesso!");
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
            }
        }
    }

    public static void validarNome(){
        String NovoNome = Leitura.dados("Qual o novo nome da lista?");
        while (ValidarTextos(NovoNome)) {
            System.out.println("Nome INVÁLIDO!");
            NovoNome = Leitura.dados("Digite o nome do aluno:");
        }
    }

    public static void atualizarIdade(Aluno A){
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
    }
    public static void atualizarCursoAluno(Aluno A){
        listarTurmas();
        String sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
        while (sigla.isBlank() && sigla.length() > 5) {
            System.out.println("Sigla INVÁLIDA");
            sigla = Leitura.dados("Escolha uma das turmas disponiveis pela sigla:");
        }
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
    }
    public static Periodo validarPeriodo(){
        while (true) {
            String escolha = Leitura.dados("""
                                Escolha qual é o periodo da turma:
                                1 - MATUTINO
                                2 - VESPERTINO
                                3 - NOTURNO
                                4 - INTEGRAL
                                """);
            switch (escolha){
                case "1":
                    return Periodo.MATUTINO;
                case "2":
                    return Periodo.VESPERTINO;
                case "3":
                    return Periodo.NOTURNO;
                case "4":
                    return Periodo.INTEGRAL;
                default:
                    System.out.println("Opção inválida, digite novamente");
                    return validarPeriodo();
            }
        }
    }


    public static String validarCursoTurma(){
        String NovoCurso = Leitura.dados("Digite o nome do curso:");
        while (ValidarTextos(NovoCurso)) {
            System.out.println("Nome INVÁLIDO! Não utilize números e nem caracteres especias");
            NovoCurso = Leitura.dados("Digite o nome do curso:");
        }
        return NovoCurso;
    }
    public static String validarSiglaTurma(){
        String NovaSigla = Leitura.dados("Digite a sigla referente ao curso:");
        while(ValidarSigla(NovaSigla)){
            System.out.println("Sigla INVÁLIDA! Não pode ser repetida, nem maior que 6 caracteres.");
            NovaSigla = Leitura.dados("Digite a sigla da turma a ser editada:");
        }
        return NovaSigla;
    }

    private static int validaIdTurma() {
        String opcao = Leitura.dados("\nDigite o número da turma desejada: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if (opcaoUsuario==-1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("Digite o número da turma desejada: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }
    private static int validarItemLista(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && listaTurmas.size() > indiceLista ? indiceLista : -1;
    }
}