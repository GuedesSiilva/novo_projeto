import java.time.LocalDate;

public class Aluno {
    private static int contador = 0;
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private Turma turma;

    public Aluno(String nome, LocalDate dataNascimento, Turma turma){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        contador++;
        this.id = contador;
        this.turma = turma;
    }

    public static int getContador() {
        return contador;
    }
    public int getId(){
        return id;
    }

    public String toString(){
        return id + " | " + nome + " | " + dataNascimento + " | " + turma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
