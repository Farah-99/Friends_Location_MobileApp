package com.example.friendslocation.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;

import com.example.friendslocation.JSONParser;
import com.example.friendslocation.MainActivity;
import com.example.friendslocation.MyLocation;
import com.example.friendslocation.R;
import com.example.friendslocation.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    ArrayAdapter ad;
    ArrayList<MyLocation> data = new ArrayList<MyLocation>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ad = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
        binding.lvLocations.setAdapter(ad);
        /*binding.btndownload.setOnClickListener(v -> {
            Telechargement t = new Telechargement(getActivity());
            t.execute();


        });*/



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //thread
    class Telechargement extends AsyncTask {
        Context conn;
        AlertDialog alert;

        public Telechargement(Context conn) {
            this.conn = conn;
        }

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder builder = new AlertDialog.Builder(conn);
            builder.setTitle("Downloading!");
            builder.setMessage("Please Wait....");
            alert = builder.create();
            alert.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String ip = "192.168.100.175";
            String url = "http://"+ ip+ "/servicephp/getAll.php";
            JSONParser parser = new JSONParser();
            JSONObject response =parser.makeHttpRequest(url,"GET", null);
            try {
                int success = response.getInt("success");
                if (success == 1){
                    // pour mois d'erreur fichier config variable static and you call it (no typos)
                    JSONArray tab = response.getJSONArray("Ami");
                    data.clear();
                    for (int i =0; i< tab.length(); i++){
                        JSONObject ligne = tab.getJSONObject(i);
                        String nom = ligne.getString("nom");
                        String numero = ligne.getString("numero");
                        String longitude = ligne.getString("longitude");
                        String latitude = ligne.getString("latitude");
                        data.add(new MyLocation(nom, numero, longitude,latitude));
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            ad.notifyDataSetChanged();
            alert.dismiss();

        }

    }
}