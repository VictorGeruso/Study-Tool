package loovsoft.com.br.studytool.model;

public class Atividade {

    private int id;
    private String nome;
    private String materia;
    private String data;

    public Atividade() {

    }

    public Atividade(String nome, String materia, String data) {
        this.setNome(nome);
        this.setMateria(materia);
        this.setData(data);
    }

    public Atividade(int id, String nome, String materia, String data) {
        this.setNome(nome);
        this.setMateria(materia);
        this.setData(data);
        this.setId(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
