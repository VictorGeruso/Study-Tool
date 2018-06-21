package loovsoft.com.br.studytool.model;

public class Tarefa {

    private Integer id;
    private String tarefa;
    private boolean feito;

    public Tarefa(){

    }

    public Tarefa(String tarefa){
        this.tarefa = tarefa;
    }

    public Tarefa(int id,String tarefa){
        this.id = id;
        this.tarefa = tarefa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }


    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }
}
