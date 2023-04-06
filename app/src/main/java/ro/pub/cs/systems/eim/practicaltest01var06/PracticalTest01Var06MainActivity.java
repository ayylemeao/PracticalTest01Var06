package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    EditText number1;
    EditText number2;
    EditText number3;

    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;

    Button playButton;

    Integer score = 0;
    PlayButtonListener playButtonListener = new PlayButtonListener();

    private class PlayButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Integer random1 = (int) (Math.random() * 10 / 3);
            Integer random2 = (int) (Math.random() * 10 / 3);
            Integer random3 = (int) (Math.random() * 10 / 3);

            //Toast.makeText(getApplicationContext(), random1 + " " + random2 + " " + random3, Toast.LENGTH_LONG).show();
            if (!checkbox1.isChecked())
                number1.setText(String.valueOf(random1));
            if (!checkbox2.isChecked())
                number2.setText(String.valueOf(random2));
            if (!checkbox3.isChecked())
                number3.setText(String.valueOf(random3));


            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
            intent.putExtra("number1", number1.getText().toString());
            intent.putExtra("number2", number2.getText().toString());
            intent.putExtra("number3", number3.getText().toString());
            Integer nrOfChecks = ((checkbox1.isChecked() ? 1 : 0) + (checkbox2.isChecked() ? 1 : 0) + (checkbox3.isChecked() ? 1 : 0));
            intent.putExtra("nrOfChecks",   nrOfChecks.toString());
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
        stopService(intent);
        Toast.makeText(getApplicationContext(), "Service stopped", Toast.LENGTH_LONG).show();
    }

    private void startPracticalService() {
        if (score > 9) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
            intent.putExtra("scor", score.toString());
            getApplicationContext().startService(intent);

            Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("number1", number1.getText().toString());
        savedInstanceState.putString("number2", number2.getText().toString());
        savedInstanceState.putString("number3", number3.getText().toString());
        savedInstanceState.putBoolean("checkbox1", checkbox1.isChecked());
        savedInstanceState.putBoolean("checkbox2", checkbox2.isChecked());
        savedInstanceState.putBoolean("checkbox3", checkbox3.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("number1")) {
            number1.setText(savedInstanceState.getString("number1"));
        } else {
            number1.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("number2")) {
            number2.setText(savedInstanceState.getString("number2"));
        } else {
            number2.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("number3")) {
            number3.setText(savedInstanceState.getString("number3"));
        } else {
            number3.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("checkbox1")) {
            checkbox1.setChecked(savedInstanceState.getBoolean("checkbox1"));
        } else {
            checkbox1.setChecked(false);
        }
        if (savedInstanceState.containsKey("checkbox2")) {
            checkbox2.setChecked(savedInstanceState.getBoolean("checkbox2"));
        } else {
            checkbox2.setChecked(false);
        }
        if (savedInstanceState.containsKey("checkbox3")) {
            checkbox3.setChecked(savedInstanceState.getBoolean("checkbox3"));
        } else {
            checkbox3.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        number1 = (EditText)findViewById(R.id.number1);
        number2 = (EditText)findViewById(R.id.number2);
        number3 = (EditText)findViewById(R.id.number3);

        checkbox1 = (CheckBox)findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox)findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox)findViewById(R.id.checkbox3);

        playButton = (Button)findViewById(R.id.play);

        playButton.setOnClickListener(playButtonListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                Bundle bundle = data.getExtras();
                Log.d("result", "GAINED");
                String castigul = bundle.getString("castigul");
                Integer castigInt = Integer.parseInt(castigul);
                score += castigInt;
                Toast.makeText(getApplicationContext(), castigInt.toString(), Toast.LENGTH_LONG).show();
                startPracticalService();

            }
        }
    }


}