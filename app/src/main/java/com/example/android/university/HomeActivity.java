package com.example.android.university;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {

    private String Tag = HomeActivity.class.getSimpleName();
    private static String URL = " https://api.rss2json.com/v1/api.json ";
    ArrayList<HashMap<String, String>> newslist;
    ArrayList<JSONArray> newsList;
    ListView listView;
    Button Exit;
    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.list);
        newslist = new ArrayList<>();
        new GetNews().execute();
////
//          NewsArrayAdapter adapter=new NewsArrayAdapter(HomeActivity.this, R.layout.list_row,newsList);

        Exit = (Button) findViewById(R.id.logout);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

        });

    }

    private class GetNews extends AsyncTask<Void, Void, Void> {
        @Override
            protected void onPreExecute() {
               super.onPreExecute();
                // Showing progress dialog
                ProgressDialog pDialog = new ProgressDialog(HomeActivity.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }

        @Override
        protected Void doInBackground(Void... arg0) {



            HttpHandler hp = new HttpHandler();
            String jsonstr = hp.makeServiceCall(URL);
            Log.e(Tag, "Response from Url" + jsonstr);
            if (jsonstr != null) {
                try {
                    JSONObject jsonobj = new JSONObject(jsonstr);
                    JSONArray news = jsonobj.getJSONArray("news");
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject c = news.getJSONObject(i);
                        String status = c.getString("status");
                        String feed = c.getString("feed");
                        String URL = c.getString("URL");
                        String title = c.getString("title");
                        String link = c.getString("link");
                        String author = c.getString("author");
                        String description = c.getString("description");
                        String image = c.getString("image");
                        HashMap<String, String> news1 = new HashMap<>();

                        newsList.add(news);

                    }
                } catch (final JSONException e) {
                    Log.e(Tag, "JSON PARSING ERROR" + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "JSON PARSING ERROR" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                  // e.printStackTrace();
                }
            } else {
                Log.e(Tag, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ProgressDialog pDialog = new ProgressDialog(HomeActivity.this);
            if (pDialog.isShowing())
                pDialog.dismiss();

            // Dismiss the progress dialog
           SimpleAdapter adapter = new SimpleAdapter(HomeActivity.this,newslist,R.layout.list_row, new String[]{"status","title","link"},new int[]{R.id.email,R.id.email});
            listView.setAdapter(adapter);
        }


        }
    }












