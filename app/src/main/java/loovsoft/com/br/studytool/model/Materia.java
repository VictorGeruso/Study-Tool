package loovsoft.com.br.studytool.model;

public class Materia {

    private int id;
    private String nome;
    private String professor;

    public Materia(String nome, String professor) {
        this.nome = nome;
        this.professor = professor;
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

}
