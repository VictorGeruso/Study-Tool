package loovsoft.com.br.studytool.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import loovsoft.com.br.studytool.BDHelper.BDHelper;
import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.MateriaAdapter;
import loovsoft.com.br.studytool.model.Materia;

public class HomeFragment extends Fragment {

    private ArrayList<Materia> materiaListaBd;
    private ListView listaMaterias;
    private ArrayAdapter adapterListaMaterias;
    private BDHelper BDHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        BDHelper = new BDHelper(getContext());

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        materiaListaBd = BDHelper.listarMateria();
        BDHelper.close();

        adapterListaMaterias = new MateriaAdapter(getContext(), materiaListaBd);

        listaMaterias = rootView.findViewById(R.id.fragmenthome_listview);

        listaMaterias.setAdapter(adapterListaMaterias);

        adapterListaMaterias.notifyDataSetChanged();

        listaMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirInformacoesMateria(position);
            }
        });

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

    private void abrirInformacoesMateria(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_informacoes, null);

        final TextView nomeMateria = view.findViewById(R.id.dialoglayout_info_materia);
        final TextView nomeProfessor = view.findViewById(R.id.dialoglayout_info_professor);
        final TextView horarioInicio = view.findViewById(R.id.dialoglayout_info_horarioinicio);
        final TextView horarioFim = view.findViewById(R.id.dialoglayout_info_horariofim);
        Button btnOk = view.findViewById(R.id.dialoglayout_info_btn_ok);
        Button btnRemover = view.findViewById(R.id.dialoglayout_info_btn_remover);
        Button btnEditar = view.findViewById(R.id.dialoglayout_info_btn_editar);

        builder.setView(view);
        builder.setTitle("Informações matéria");

        final AlertDialog dialog = builder.create();

        Materia materiaEscolhida = materiaListaBd.get(position);

        nomeMateria.setText(materiaEscolhida.getNome());
        nomeProfessor.setText(materiaEscolhida.getProfessor());
        horarioInicio.setText(materiaEscolhida.getHorarioInicio());
        horarioFim.setText(materiaEscolhida.getHorarioFim());

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerMateria(position);
                dialog.dismiss();
                Toast.makeText(getContext(), "Materia Removida com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                abrirAlteracaoMateria(position);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void abrirAlteracaoMateria(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_alteracao, null);

        final EditText nomeMateria = view.findViewById(R.id.dialoglayout_alterarNomeMateria);
        final EditText nomeProfessor = view.findViewById(R.id.dialoglayout_alterarNomeProfessor);
        final EditText horarioInicio = view.findViewById(R.id.dialoglayout_alterar_horario_inicio);
        final EditText horarioFim = view.findViewById(R.id.dialoglayout_alterar_horario_fim);
        Button btnAlterar = view.findViewById(R.id.dialoglayout_btn_alterar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_btnAlterar_cancelar);

        builder.setView(view);
        builder.setTitle("Altera Matéria");

        final AlertDialog dialog = builder.create();

        final Materia materiaEscolhida = materiaListaBd.get(position);

        nomeMateria.setText(materiaEscolhida.getNome());
        nomeProfessor.setText(materiaEscolhida.getProfessor());
        horarioInicio.setText(materiaEscolhida.getHorarioInicio());
        horarioFim.setText(materiaEscolhida.getHorarioFim());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = materiaEscolhida.getId();
                String nome = nomeMateria.getText().toString();
                String professor = nomeProfessor.getText().toString();
                String inicio = horarioInicio.getText().toString();
                String fim = horarioFim.getText().toString();

                validarHorario(horarioInicio,horarioFim,inicio,fim);
                editarMateria(id,nome,professor,inicio,fim);
                dialog.dismiss();
                Toast.makeText(getContext(),"Matéria Alterada com Sucesso!",Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void editarMateria(int id, String nome, String professor, String inicio, String fim) {

        for (int i=0; i < materiaListaBd.size(); i++){
            if (materiaListaBd.get(i).getId() == id){
                Materia materia = new Materia(id,nome, professor, inicio, fim);
                materiaListaBd.get(i).setNome(nome);
                materiaListaBd.get(i).setProfessor(professor);
                materiaListaBd.get(i).setHorarioInicio(inicio);
                materiaListaBd.get(i).setHorarioFim(fim);
                BDHelper.alterarMateria(materia);
                BDHelper.close();
                adapterListaMaterias.notifyDataSetChanged();
            }
        }
    }

    private void removerMateria(int position) {
        Materia materia = materiaListaBd.get(position);
        materiaListaBd.remove(materia);
        BDHelper.deletarMateria(materia);
        BDHelper.close();
        adapterListaMaterias.notifyDataSetChanged();
    }

    private void abrirCadastroMateria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_cadastro ,null);

        final EditText nomeMateria = view.findViewById(R.id.dialoglayout_nomeMateria);
        final EditText nomeProfessor = view.findViewById(R.id.dialoglayout_nomeProfessor);
        final EditText horarioInicio = view.findViewById(R.id.dialoglayout_horario_inicio);
        final EditText horarioFim = view.findViewById(R.id.dialoglayout_horario_fim);
        Button btnCadastrar = view.findViewById(R.id.dialoglayout_cadastrar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_cancelar);

        builder.setView(view);
        builder.setTitle("Cadastrar matéria");

        final AlertDialog dialog = builder.create();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCadastro(nomeMateria, nomeProfessor, horarioInicio, horarioFim);
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

    private void validarCadastro(EditText nomeMateria, EditText nomeProfessor, EditText horarioInicio, EditText horarioFim) {
        String materia = nomeMateria.getText().toString();
        String professor = nomeProfessor.getText().toString();
        String hInicio = horarioInicio.getText().toString();
        String hFim = horarioFim.getText().toString();

        validarHorario(horarioInicio, horarioFim, hInicio, hFim);
        if(materia.isEmpty() || professor.isEmpty()) {
            nomeMateria.setError("insira um nome");
            nomeProfessor.setError("insira um nome");

        } else {
            cadastrarMateria(materia, professor, hInicio, hFim);

        }

    }

    private void validarHorario(EditText horarioInicio, EditText horarioFim, String hInicio, String hFim) {
       for (int i = 0; i < materiaListaBd.size(); i++){
           String hi = materiaListaBd.get(i).getHorarioInicio();
           String hf = materiaListaBd.get(i).getHorarioFim();
           if (hi.equals(hf)){
               horarioFim.setError("Escolha Outro Horario");
           } else if (hi.equals(hInicio)){
              horarioInicio.setError("Esse Horario já foi Escolhido");
           } else if (hf.equals(hFim)){
              horarioFim.setError("Esse Horario já foi Escolhido");
           }
       }
    }

    private void cadastrarMateria(String materia, String professor, String inicio, String fim) {
        Materia m = new Materia(materia, professor, inicio, fim);
        materiaListaBd.add(m);
        BDHelper.cadastrarMateria(m);
        BDHelper.close();
        adapterListaMaterias.notifyDataSetChanged();
    }
}
