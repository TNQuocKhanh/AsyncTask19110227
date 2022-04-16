package com.example.asynctask19110227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView txtShow, txtHeading;
    private ProgressBar pgbMain;

    private AsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHeading.setText("Working...");
                txtShow.setText("");

                myTask = new DownloadTask()
                        .execute("Task 1", "Task 2", "Task 3", "Task 4", "Task 5",
                                "Task 6", "Task 7", "Task 8", "Task 9", "Task 10"
                        );
            }
        });
    }

    private void AnhXa() {
        btnStart = (Button) findViewById(R.id.buttonStartTask);
        txtShow = (TextView) findViewById(R.id.textViewShow);
        txtHeading = (TextView) findViewById(R.id.textViewHeading);
        pgbMain = (ProgressBar) findViewById(R.id.progressBar);
    }

    private class DownloadTask extends AsyncTask<String,Integer, List<String>>{
        protected void onPreExecute(){
        }

        protected List<String> doInBackground(String...tasks){
            int count = tasks.length;
            List<String> taskList= new ArrayList<>(count);

            for(int i =0;i<count;i++){
                String currentTask = tasks[i];
                taskList.add(currentTask);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress((int) (((i+1) / (float) count) * 100));

                if(isCancelled()){
                    break;
                }
            }
            return taskList;
        }

        protected void onProgressUpdate(Integer... progress){
            txtShow.setText(""+progress[0] + " %");
            pgbMain.setProgress(progress[0]);

            if(pgbMain.getProgress()==100){
                txtHeading.setText("Done.");
            }
        }
        protected void onPostExecute(List<String> result){
            for (int i=0;i<result.size();i++){
            }
        }
    }
}