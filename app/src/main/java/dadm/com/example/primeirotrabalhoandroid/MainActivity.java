package dadm.com.example.primeirotrabalhoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay, btn_ir;
    EditText edtInput, edtLog;
    Spinner spnr;

    private static final String[] COUNTRIES = new String[]{
        "Estônia", "Brasil", "Alemanha", "Colômbia", "Nigéria", "Itália",
        "Suécia", "Eslováquia", "Hungria", "Polônia", "Espanha", "Inglaterra",
        "Rússia", "China", "Japão", "Aral", "Vancouver"
    };

    String[] stacks = {
        "NodeJs", "ReactJs", "React Native","Android","Kotlin","Java",
    };

    String[] items = {"Material", "Design", "Components", "Android", "Kotlin"};
    AutoCompleteTextView auto_complete_txt;
    ArrayAdapter<String> adapterItems;

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();

        edtInput = findViewById(R.id.edtInput);
        edtLog = findViewById(R.id.edtLog);

        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, stacks);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(),"You have selected "+stacks[+position],Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );

        AutoCompleteTextView editText = findViewById(R.id.actvComplete);
        ArrayAdapter<String> adapterCountries = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES);
        editText.setAdapter(adapterCountries);

        //menu dropdown
        auto_complete_txt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        auto_complete_txt.setAdapter(adapterItems);
        auto_complete_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item:"+item , Toast.LENGTH_SHORT).show();
            }
        });

        //evento clique longo
        TextView txtView = (TextView) findViewById(R.id.txtView);
        txtView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "You have pressed it long :)", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        txtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Not Long Enough :(",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //navegação para outra activity
        btn_ir = findViewById(R.id.btn_ir);
        btn_ir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        //tocando um som
        mysound = MediaPlayer.create(this,R.raw.sigue_luan);
    }

    public void playMusic(View view) {
        mysound.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mysound.release();
    }

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OnClickButton01(View view){
        String textoEdtInput = edtInput.getText().toString();
        edtInput.setText("");

        String textoEdtLog = edtLog.getText().toString();
        textoEdtLog += "-" + textoEdtInput;

        edtLog.setText(textoEdtLog);

        Log.d( "test", textoEdtInput);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_java:
                if (checked)
                    break;
            case R.id.radio_kotlin:
                if (checked)
                    break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this,"Sub Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this,"Sub Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}