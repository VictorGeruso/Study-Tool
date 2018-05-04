package loovsoft.com.br.studytool.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.MateriaAdapter;
import loovsoft.com.br.studytool.model.Materia;

public class HomeFragment extends Fragment {

    private ArrayList<Materia> materias;
    private ListView listaMaterias;
    private ArrayAdapter adapterListaMaterias;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        materias = new ArrayList<>();

        adapterListaMaterias = new MateriaAdapter(getContext(),materias);

        listaMaterias = rootView.findViewById(R.id.fragmenthome_listview);

        listaMaterias.setAdapter(adapterListaMaterias);

        final FloatingActionButton actionButton = rootView.findViewById(R.id.fragmenthome_floatinbutton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == actionButton) {
                    abrirCadastroMateria();
                }

            }
        });

        return rootView;
    }

    private void abrirCadastroMateria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_cadastro ,null);

        final EditText nomeMateria = view.findViewById(R.id.dialoglayout_nomeMateria);
        final EditText nomeProfessor = view.findViewById(R.id.dialoglayout_nomeProfessor);
        Button btnCadastrar = view.findViewById(R.id.dialoglayout_cadastrar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_cancelar);



        builder.setView(view);
        builder.setTitle("Cadastrar matéria");

        final AlertDialog dialog = builder.create();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarCadastro(nomeMateria, nomeProfessor);

                dialog.dismiss();

                Toast.makeText(getContext(), "Materia Cadastrada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });


        dialog.show();

    }

    private void validarCadastro(EditText nomeMateria, EditText nomeProfessor) {

        String materia = nomeMateria.getText().toString();
        String professor = nomeProfessor.getText().toString();

        if(materia.isEmpty() || professor.isEmpty()) {
            nomeMateria.setError("insira um nome");
            nomeProfessor.setError("insira um nome");
        } else {

            cadastrarMateria(materia, professor);

        }

    }

    private void cadastrarMateria(String materia, String professor) {

        Materia m = new Materia(materia, professor);

        materias.add(m);

        adapterListaMaterias.notifyDataSetChanged();

    }


}
