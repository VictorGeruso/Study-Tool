package loovsoft.com.br.studytool.model;

public class Atividade {

    private int id;
    private String data;
    private String assunto;

    public Atividade() {

    }

    public Atividade(String assunto, String data) {
        this.assunto = assunto;
        this.data = data;
    }

    public Atividade(int id, String assunto, String data) {
        this.assunto = assunto;
        this.data = data;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
