package custom.expandable.list.view.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    SplashActivityAsyncTask splashActivityAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //******* set action bar **********
        assert getSupportActionBar() != null;
        getSupportActionBar().hide();
        splashActivityAsyncTask = new SplashActivityAsyncTask();
        splashActivityAsyncTask.execute();
    }

    public class SplashActivityAsyncTask extends AsyncTask<Void, Integer, String> {

        String TAG = getClass().getSimpleName();

        @Override
        protected void onPreExecute() {
            Log.d(TAG + " onPreExecute()","On pre Exceute......");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG + " doInBackground","On doInBackground...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent goToExpandableListViewActivity = new Intent(SplashActivity.this, ExpandableListViewActivity.class);
            startActivity(goToExpandableListViewActivity);
            SplashActivity.this.finish();

            return "Finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG + " onPostExecute", "" + result);
            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
