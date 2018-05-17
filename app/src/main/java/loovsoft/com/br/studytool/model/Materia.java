package loovsoft.com.br.studytool.model;

public class Materia {

    private int id;
    private String nome;
    private String professor;
    private String horarioInicio;
    private String horarioFim;

    public Materia(String nome, String professor, String horarioInicio, String horarioFim) {
        this.nome = nome;
        this.professor = professor;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.id = (int) Math.random();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

}
