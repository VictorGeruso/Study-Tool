package loovsoft.com.br.studytool.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import loovsoft.com.br.studytool.adapters.AtividadeAdapter;
import loovsoft.com.br.studytool.model.Atividade;
import loovsoft.com.br.studytool.model.Materia;

public class AtividadeFragment extends Fragment {

    private ArrayList<Atividade> atividadeListaBd;
    private ListView listaAtividades;
    private ArrayAdapter adapterListaAtividades;
    private BDHelper BDHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BDHelper = new BDHelper(getContext());

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_atividade, container, false);

        atividadeListaBd = BDHelper.listarAtividade();
        BDHelper.close();

        adapterListaAtividades = new AtividadeAdapter(getContext(), atividadeListaBd);

        listaAtividades = rootView.findViewById(R.id.fragmentatividade_listview);

        listaAtividades.setAdapter(adapterListaAtividades);

        adapterListaAtividades.notifyDataSetChanged();

        listaAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     abrirInformacoesAtividade(position);
            }
        });

        final FloatingActionButton actionButton = rootView.findViewById(R.id.fragmentatividade_floatinbutton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == actionButton) {
                    abrirCadastroAtividade();
                }
            }
        });

        return rootView;
    }

    private void abrirInformacoesAtividade(final int position) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_informacoes_atividade, null);

        final TextView nomeAtividade = view.findViewById(R.id.dialoglayout_infoatv_nomeatividade);
        final TextView nomeMateria = view.findViewById(R.id.dialoglayout_infoatv_nomemateria);
        final TextView data = view.findViewById(R.id.dialoglayout_infoatv_dataatividade);
        Button btnOk = view.findViewById(R.id.dialoglayout_infoatv_btn_ok);
        Button btnEditar = view.findViewById(R.id.dialoglayout_infoatv_btn_editar);
        Button btnRemover = view.findViewById(R.id.dialoglayout_infoatv_btn_remover);

        builder.setView(view);
        builder.setTitle("Informações Atividade");

        final AlertDialog dialog = builder.create();

        Atividade atividadeescolhida = atividadeListaBd.get(position);

        nomeAtividade.setText(atividadeescolhida.getNome());
        nomeMateria.setText(atividadeescolhida.getMateria());
        data.setText(atividadeescolhida.getData());

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerAtividade(position);
                dialog.dismiss();
                Toast.makeText(getContext(), "Atividade Removida com Sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                abrirAlterarAtividade(position);
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

    private void abrirAlterarAtividade(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_alteracao_atividade, null);

        final EditText nomeAtividade = view.findViewById(R.id.dialoglayout_alteracaoatv_alterarNomeAtividade);
        final EditText nomeMateria = view.findViewById(R.id.dialoglayout_alteracaoatv_alterarNomeMateria);
        final EditText dataAtv =  view.findViewById(R.id.dialoglayout_alteracaoatv_alterarData);
        Button btnAlterar = view.findViewById(R.id.dialoglayout_btn_alterar_atividade);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_btn_alterar_cancelar_atividade);

        builder.setView(view);
        builder.setTitle("Alterar Atividade");

        final AlertDialog dialog = builder.create();

        final Atividade atividadeEscolhida = atividadeListaBd.get(position);

        nomeAtividade.setText(atividadeEscolhida.getNome());
        nomeMateria.setText(atividadeEscolhida.getMateria());
        dataAtv.setText(atividadeEscolhida.getData());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = atividadeEscolhida.getId();
                String nome = nomeAtividade.getText().toString();
                String materia = nomeMateria.getText().toString();
                String data = dataAtv.getText().toString();

                editarAtividade(id, nome, materia, data);
                dialog.dismiss();
                Toast.makeText(getContext(), "Atividade Alterada com Sucesso", Toast.LENGTH_SHORT).show();
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

    private void editarAtividade(int id, String nome, String materia, String data) {
        for(int i = 0; i < atividadeListaBd.size(); i++) {
            if(atividadeListaBd.get(i).getId() == id) {
                Atividade atividade = new Atividade(id, nome, materia, data);
                atividadeListaBd.get(i).setNome(nome);
                atividadeListaBd.get(i).setMateria(materia);
                atividadeListaBd.get(i).setData(data);
                BDHelper.alterarAtividade(atividade);
                BDHelper.close();
                adapterListaAtividades.notifyDataSetChanged();
            }
        }
    }

    private void removerAtividade(int position) {
        Atividade atividade = atividadeListaBd.get(position);
        atividadeListaBd.remove(atividade);
        BDHelper.deletarAtividade(atividade);
        BDHelper.close();
        adapterListaAtividades.notifyDataSetChanged();
    }

    private void abrirCadastroAtividade() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_cadastro_atividade, null);

        final EditText nomeAtividade = view.findViewById(R.id.dialoglayout_atv_nomeAtividade);
        final EditText nomeMateria = view.findViewById(R.id.dialoglayout_atv_nomeMateria);
        final EditText dataAtv = view.findViewById(R.id.dialoglayout_atv_data);
        Button cadastrar = view.findViewById(R.id.dialoglayout_atv_cadastrar);
        Button cancelar = view.findViewById(R.id.dialoglayout_atv_cancelar);

        builder.setView(view);
        builder.setTitle("Cadastrar Atividade");

        final AlertDialog dialog = builder.create();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCadastro(nomeAtividade, nomeMateria, dataAtv);
                dialog.dismiss();
                Toast.makeText(getContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void validarCadastro(EditText nomeAtividade, EditText nomeMateria, EditText dataAtv) {
        String atividade = nomeAtividade.getText().toString();
        String materia = nomeMateria.getText().toString();
        String data = dataAtv.getText().toString();

        if(atividade.isEmpty() || materia.isEmpty() || data.isEmpty()) {
            nomeAtividade.setError("Insira o nome da atividade");
            nomeMateria.setError("Insira o nome da materia");
            dataAtv.setError("insira a data");
        } else {
            cadastrarAtividade(atividade, materia, data);
        }
    }

    private void cadastrarAtividade(String atividade, String materia, String data) {
        Atividade a = new Atividade(atividade, materia, data);
        atividadeListaBd.add(a);
        BDHelper.cadastrarAtividade(a);
        BDHelper.close();
        adapterListaAtividades.notifyDataSetChanged();
    }

}
