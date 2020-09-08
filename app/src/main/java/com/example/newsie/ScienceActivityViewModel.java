package com.example.newsie;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ScienceActivityViewModel extends ViewModel {
    ArrayList<News> arrayList = new ArrayList<News>();
    MutableLiveData<List<News>> mScience;


    public LiveData<List<News>> getScienceNews() {
        Log.i("ajeet", "getStates: method is called");
        if (mScience == null) {
            mScience = new MutableLiveData<List<News>>();
//             aynsta();
            ScienceAsynctask task = new ScienceAsynctask();
            task.execute("http://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=48f6cf55f79244fea4818e3455a08f64\n");
            Log.i("ajeet", "getStates: function is called");
        }
//         mStates.postValue(stateinfo); // yaha pr galti kr rahe the response aane k baad data add krna tha ( onpostexecute wale method m kiye h)
//        mNews.setValue(arrayList);
        return mScience;
    }



    class ScienceAsynctask extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<News> result = fetchStateData(urls[0]);
            return result;

        }

        protected void onPostExecute(ArrayList<News> data) {
            arrayList.clear();


            if (data != null && !data.isEmpty()) {

                mScience.setValue(data);

            }
        }
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("testing", "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(30000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("testing", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("testing", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<News> extractFeatureFromJson(String response) {
        ArrayList<News> list = new ArrayList<News>();
        try {
            JSONObject jsonresponse = new JSONObject(response);
//            JSONObject jsonObjectdata = jsonresponse.getJSONObject("data");
            JSONArray jsonArray = jsonresponse.getJSONArray("articles");
            final int max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject currentStatesResponse = jsonArray.getJSONObject(i);
                String title = currentStatesResponse.getString("title");
                String description = currentStatesResponse.getString("description");
                String imgurl = currentStatesResponse.getString("urlToImage");
                String articleurl = currentStatesResponse.getString("url");
                News news = new News(imgurl,title,description,articleurl);
                list.add(news);


            }

//
        } catch (JSONException e) {
            Log.e("testing", "extractFeatureFromJson: ", e);
        }

        return list;
    }

    public static ArrayList<News> fetchStateData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("testing", "Problem making the HTTP request.", e);
        }
        ArrayList<News> newss = extractFeatureFromJson(jsonResponse);
        return newss;
////        }
    }




}
