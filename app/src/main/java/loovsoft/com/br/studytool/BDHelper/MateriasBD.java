package loovsoft.com.br.studytool.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import loovsoft.com.br.studytool.model.Materia;

public class MateriasBD extends SQLiteOpenHelper {

    private static final String DATABASE = "materias.bd";
    private static final int VERSION = 1;

    public MateriasBD(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String materia = "CREATE TABLE materia (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, professor TEXT NOT NULL, horaInicio TEXT NOT NULL, horaFim TEXT NOT NULL);";
            db.execSQL(materia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String materia = "DROP TABLE IF EXISTS materia";
            db.execSQL(materia);
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
        getWritableDatabase().update("materia",values,"id=?",args);
    }

    public void deletarMateria(Materia materia){
        String[] args = {String.valueOf(materia.getId())};
        getWritableDatabase().delete("materia","id=?",args);
    }

    public ArrayList<Materia> listar(){
        String[] columns = {"_id","nome","professor","horaInicio","horaFim"};
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
}
