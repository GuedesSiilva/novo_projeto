import java.time.LocalDate;

public class Turma implements Ativavel{
    private static int proximoID = 1;
    private int id;
    private String curso;
    private String sigla;
    private Periodo periodo;
    private boolean ativo;

    public Turma(String curso, String sigla, Periodo periodo){
        this.curso = curso;
        this.sigla = sigla;
        this.periodo = periodo;
        this.id = proximoID++;
        this.ativo = true;
    }

    public String getSigla(){
        return sigla;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo){
        this.ativo =ativo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }
    public int getID() {
        return id;
    }

    public String getCurso() {
        return curso;
    }

    public String toString(){
        return curso + " | " + sigla + " | " + periodo;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
