package com.trabalhoprog1.uffsclasshelp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trabalhoprog1.uffsclasshelp.DB.DBHELPCLASS;
import com.trabalhoprog1.uffsclasshelp.dominio.repositorio.ClasseRepositorio;
import com.trabalhoprog1.uffsclasshelp.dominio.entidade.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActCadastroTurma extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText edtClasse;
    private java.util.Calendar datahora = java.util.Calendar.getInstance();
    private TextView dataAula;
    private Button btnHora;
    private Button btnData;
    private EditText edtConteudo;
    //private DateFormat formatodatahora = DateFormat.getDateTimeInstance();
    private SimpleDateFormat formatodatahora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date data = new Date();
    Spinner salas;
    Spinner blocos;
    private ClasseRepositorio classeRepositorio;
    private SQLiteDatabase conexao;
    private DBHELPCLASS dbhelpclass;
    private Turma turma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cadastro_turma);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataAula = (TextView) findViewById(R.id.lblData);
        btnData = (Button) findViewById(R.id.bntData);
        btnHora = (Button) findViewById(R.id.bntHora);
        edtClasse = (EditText) findViewById(R.id.edtClasse);
        edtConteudo = (EditText) findViewById(R.id.edtConteudo);
        salas = (Spinner) findViewById(R.id.spinerSala);
        blocos = (Spinner) findViewById(R.id.spinerBloco);

        ArrayAdapter lstSalas = ArrayAdapter.createFromResource(this, R.array.Salas, android.R.layout.simple_spinner_item);
        ArrayAdapter lstBlocos = ArrayAdapter.createFromResource(this, R.array.Bloco, android.R.layout.simple_spinner_item);
        salas.setAdapter(lstSalas);
        blocos.setAdapter(lstBlocos);

        criarConexao();
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHora();
            }
        });

        Pegardata();
    }

    private void criarConexao() {
        try {
            dbhelpclass = new DBHELPCLASS(this);
            conexao = dbhelpclass.getWritableDatabase();
            androidx.appcompat.app.AlertDialog.Builder dlg = new androidx.appcompat.app.AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage("Deu Certo");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            //Snackbar.make(layoutContatMain, "Conexao Criada com Sucesso", Snackbar.LENGTH_SHORT).setAction("OK", null).show();
            classeRepositorio = new ClasseRepositorio(conexao);

        } catch (SQLException ex) {

            androidx.appcompat.app.AlertDialog.Builder dlg = new androidx.appcompat.app.AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }
    }

    private void setData() {
        new DatePickerDialog(this, d, datahora.get(Calendar.YEAR), datahora.get(Calendar.MONTH), datahora.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setHora() {
        new TimePickerDialog(this, t, datahora.get(Calendar.HOUR_OF_DAY), datahora.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datahora.set(Calendar.YEAR, year);
            datahora.set(Calendar.MONTH, month);
            datahora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Pegardata();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            datahora.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datahora.set(Calendar.MINUTE, minute);
            Pegardata();
        }
    };

    private void Pegardata() {
        dataAula.setText(formatodatahora.format(datahora.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_class, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_salvar:
               confirmar();
                break;

            case R.id.action_sair:
                // Toast.makeText(this,"Botao Sair Selecionado",Toast.LENGTH_SHORT).show();
                ValidaCampos();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void confirmar() {

        turma = new Turma();

        if (ValidaCampos() == false) {
            try {
                classeRepositorio.inserir(turma);
                finish();
            } catch (SQLException ex) {
                androidx.appcompat.app.AlertDialog.Builder dlg = new androidx.appcompat.app.AlertDialog.Builder(this);
                dlg.setTitle("Erro");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("Ok", null);
                dlg.show();
            }


        }

    }

    private boolean ValidaCampos() {

        boolean resposta = false;
        String classe = edtClasse.getText().toString();
        String conteudo = edtConteudo.getText().toString();

        if (resposta = campovazio(classe)) {
            edtClasse.requestFocus();
        } else if (resposta = campovazio(conteudo)) {
            edtConteudo.requestFocus();
        }

        if (resposta) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos branco!!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
        return resposta;
    }

    private boolean campovazio(String campo) {
        boolean resultado = (TextUtils.isEmpty(campo) || campo.trim().isEmpty());
        return resultado;
    }
}
