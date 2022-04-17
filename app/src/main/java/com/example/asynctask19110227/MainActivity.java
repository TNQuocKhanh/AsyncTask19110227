package com.example.asynctask19110227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtStatus;
    private Button btnStartTask;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        btnStartTask = (Button) findViewById(R.id.buttonStartTask);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStatus.setText(R.string.text_working);
                new DownloadTask().execute("");
            }
        });
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... params) {
            for (int i = 0; i < 100; i = i + 10) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress((i + 10));
                if (isCancelled()) {
                    break;
                }
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);

            if (progressBar.getProgress() == 100) {
                txtStatus.setText(R.string.text_complete);
            }
        }
    }
}