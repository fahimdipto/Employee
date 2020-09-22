package com.example.employee;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

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
import java.util.ArrayList;

public class data extends AsyncTask< Void, Void, Void> {
    String data;
    String dataPersed="";
    String name="";
    String location2="location";
    String singleperse="";
    String doubleperse="";
    String latitude="";
    String longitude="";
    String location="";
    public static ArrayList<String> nameData= new ArrayList<String>();
    public static ArrayList<String> locationData= new ArrayList<String>();
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url= new URL("https://anontech.info/courses/cse491/employees.json");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line= "";

            while(line!=null){
                line=bufferedReader.readLine();
                data= data+line;
            }
           // line =line.substring(4,l);
            String crappyPrefix = "null";

            if(data.startsWith(crappyPrefix)){
                data = data.substring(crappyPrefix.length(),data.length());
            }
            JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++ )
            {
                JSONObject jo=(JSONObject)ja.get(i);
                name = "Name:" + jo.get("name") + "\n";
                location ="location"+ jo.get("location");
                Log.d("loc",location);                nameData.add(name);
                if (location.equalsIgnoreCase("locationnull") || location.equalsIgnoreCase("null")){


                }else{
                    String prelat = location.split(",")[0];
                    String prelng = location.split(",")[1];
                    String lat = prelat.substring(20,prelat.length());
                    String lng = prelng.substring(12,prelng.length()-1);
                    locationData.add(lat+','+lng);
                    Log.d("CODE", lat);
                    Log.d("CODE", lng);
                }

//                if(location != null) {
//                    String[] preLat = location.split(",");
//                    String lat = preLat[0].split(":")[1];
//                    String lng = preLat[1].split(":")[1];
//                    Log.d("CODE", lat);
//                }
               /* JSONObject location=jo.getJSONObject("location");

                if(location.isNull("latitude")) {
                    //latitude = (JSONObject)null+ "" + "\n";
                }
                else{
                    latitude = "latitude:" + location.getDouble("latitude") + "\n";
                }
                if(location.isNull("longitude")) {
                    //longitude = (JSONObject)null"" + "\n";
                }
                else{
                    longitude = "longitude:" + location.getDouble("longitude") + "\n";
                }

                */
                singleperse = name + location2 + "\n";
                doubleperse=singleperse+ latitude + longitude +"\n";
                dataPersed = dataPersed + doubleperse + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("url", "URL er probl");
        }

        return null;
    }
}