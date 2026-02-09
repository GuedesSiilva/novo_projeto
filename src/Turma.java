import java.time.LocalDate;

public class Turma {
    private String curso;
    private String sigla;
    private Periodo periodo;

    public Turma(String curso, String sigla, Periodo periodo){
        this.curso = curso;
        this.sigla = sigla;
        this.periodo = periodo;
    }

    public String getSigla(){
        return sigla;
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
