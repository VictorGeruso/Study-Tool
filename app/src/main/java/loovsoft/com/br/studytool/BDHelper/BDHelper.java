package loovsoft.com.br.studytool.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import loovsoft.com.br.studytool.model.Materia;
import loovsoft.com.br.studytool.model.Tarefa;

public class BDHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "banco.bd";
    private static final int VERSION = 1;

    public BDHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String materia = "CREATE TABLE materia (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, professor TEXT NOT NULL, horaInicio TEXT NOT NULL, horaFim TEXT NOT NULL);";

            String tarefa = "CREATE TABLE tarefa (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, tarefa TEXT NOT NULL, check INTEGER DEFAULT 0);";

            db.execSQL(materia);
            db.execSQL(tarefa);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String materia = "DROP TABLE IF EXISTS materia";
            String tarefa = "DROP TABLE IF EXISTS tarefa";

            db.execSQL(materia);
            db.execSQL(tarefa);
    }

    public void cadastrarMateria(Materia materia) {
        ContentValues values = new ContentValues();
        values.put("nome", materia.getNome());
        values.put("professor", materia.getProfessor());
        values.put("horaInicio", materia.getHorarioInicio());
        values.put("horaFim", materia.getHorarioFim());

        getWritableDatabase().insert("materia", null,values);
    }

    public void alterarMateria(Materia materia) {
        ContentValues values = new ContentValues();

        values.put("nome", materia.getNome());
        values.put("professor", materia.getProfessor());
        values.put("horaInicio", materia.getHorarioInicio());
        values.put("horaFim", materia.getHorarioFim());

        String[] args = {String.valueOf(materia.getId())};
        getWritableDatabase().update("materia",values,"_id=?",args);
    }

    public void deletarMateria(Materia materia){
        String[] args = {String.valueOf(materia.getId())};
        getWritableDatabase().delete("materia","_id=?",args);
    }

    public ArrayList<Materia> listarMateria(){
        String[] columns = {"_id","tarefa","check","horaInicio","horaFim"};
        Cursor cursor = getReadableDatabase().query("materia", columns,null,null,null,null,null,null);
        ArrayList<Materia> materias = new ArrayList<>();
        while (cursor.moveToNext()){
            Materia materia = new Materia();
            materia.setId(cursor.getInt(0));
            materia.setNome(cursor.getString(1));
            materia.setProfessor(cursor.getString(2));
            materia.setHorarioInicio(cursor.getString(3));
            materia.setHorarioFim(cursor.getString(4));

            materias.add(materia);
        }

        return materias;
    }

    public ArrayList<Tarefa> listarTarefa(){
        String[] columns = {"_id","tarefa","check"};
        Cursor cursor = getReadableDatabase().query("tarefa", columns,null,null,null,null,null,null);
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            tarefa.setId(cursor.getInt(0));
            tarefa.setTarefa(cursor.getString(1));
            tarefa.setCheck(cursor.getInt(2));



            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
