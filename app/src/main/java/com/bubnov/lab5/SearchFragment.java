package com.bubnov.lab5;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SearchFragment extends Fragment {

    String input;
    private TextInputEditText stringText;
    private Button buttonSave;
    private WebView webView;

    public SearchFragment(){
        super(R.layout.fragment_search);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringText = view.findViewById(R.id.string);
        buttonSave = view.findViewById(R.id.buttonAdd);
        webView = view.findViewById(R.id.webView);
        input = new String("");
        if(webView!=null)
            webView.getSettings().setJavaScriptEnabled(true);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = stringText.getText().toString();
                if (!string.isEmpty()) {
                    input = "https://search.yahoo.com/search?p=" + stringText.getText();
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getText(R.string.toastSuccess), Toast.LENGTH_SHORT).show();
                    new WebTask().execute();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getText(R.string.toastError), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class WebTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... unused) {

            try{
                String content = getContent(input);
                webView.post(new Runnable() {
                    public void run() {
                        webView.loadDataWithBaseURL(input,content, "text/html", "UTF-8", "https://stackoverflow.com/");
                        Toast.makeText(getActivity().getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch (IOException ex){

            }
            return(null);
        }
        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            InputStream stream = null;
            HttpsURLConnection connection = null;
            try {
                URL url=new URL(path);
                connection =(HttpsURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.connect();
                stream = connection.getInputStream();
                reader= new BufferedReader(new InputStreamReader(stream));
                StringBuilder buf=new StringBuilder();
                String line;
                while ((line=reader.readLine()) != null) {
                    buf.append(line).append("\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
                if (stream != null) {
                    stream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getActivity().getApplicationContext(), "Задача завершена", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}