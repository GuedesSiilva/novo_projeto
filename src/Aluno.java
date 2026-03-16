import java.time.LocalDate;

public class Aluno implements Ativavel{
    private static int contador = 0;
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private Turma turma;
    private boolean ativo;

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

    public Turma getTurma() {
        return turma;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo){
        this.ativo =ativo;
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
