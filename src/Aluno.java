import java.time.LocalDate;

public class Aluno {
    private static int contador = 0;
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private Turma turma;

    public Aluno(String nome, LocalDate dataNascimento){
        this.nome = nome;
        contador++;
        this.id = contador;
    }

    public static int getContador() {
        return contador;
    }
    public int getId(){
        return id;
    }
}
