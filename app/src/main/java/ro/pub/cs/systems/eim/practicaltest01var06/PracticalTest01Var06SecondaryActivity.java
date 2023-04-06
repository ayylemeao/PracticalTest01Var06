package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nr1 = extras.getString("number1");
            String nr2 = extras.getString("number2");
            String nr3 = extras.getString("number3");
            String nrOfChecks = extras.getString("nrOfChecks");

            Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var06MainActivity.class);
            String castigul = "0";


            if (nr1.equals(nr2) && nr2.equals(nr3) && nr1.equals(nr3)) {
                if (nrOfChecks.equals("0")) {
                    castigul = "100";
                }
                if (nrOfChecks.equals("1")) {
                    castigul = "50";
                }
                if (nrOfChecks.equals("2")) {
                    castigul = "10";
                }
                intent2.putExtra("castigul", castigul);
                setResult(1, intent2);
                finish();
            }
            else {
                finish();
            }
        }
    }
}