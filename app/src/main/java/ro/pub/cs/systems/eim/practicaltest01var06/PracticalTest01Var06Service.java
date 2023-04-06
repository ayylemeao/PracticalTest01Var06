package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class PracticalTest01Var06Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int scor = intent.getIntExtra("scor", 0);
        // log the victory, score and the time
        Log.d("PracticalTest01Var06Service", "Scor: " + scor);
        Log.d("PracticalTest01Var06Service", "Time+date: " + System.currentTimeMillis());
        Log.d("PracticalTest01Var06Service", "Victory!!!");
        return Service.START_REDELIVER_INTENT;
    }
}
