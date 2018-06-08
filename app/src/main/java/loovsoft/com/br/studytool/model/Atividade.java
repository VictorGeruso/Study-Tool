package loovsoft.com.br.studytool.model;

import java.time.LocalDate;

public class Atividade {

    private int id;
    private Materia materia;
    private LocalDate data;
    private String assunto;

    public Atividade() {

    }

    public Atividade(Materia materia, LocalDate data) {
        this.materia = materia;
        this.data = data;
        this.id = (int) Math.random();
    }

    public Atividade(int id, Materia materia, LocalDate data) {
        this.materia = materia;
        this.data = data;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
