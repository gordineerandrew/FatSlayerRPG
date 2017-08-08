package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/30/2017.
 */

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Stats extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner_helm;
    Spinner spinner_armor;
    Spinner spinner_leggings;
    Spinner spinner_boots;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_screen, container, false);

        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.stats_screen, container, false);






        spinner_helm = (Spinner) rl.findViewById(R.id.equip_helm);
        spinner_armor = (Spinner) rl.findViewById(R.id.equip_armor);
        spinner_leggings = (Spinner) rl.findViewById(R.id.equip_leggings);
        spinner_boots = (Spinner) rl.findViewById(R.id.equip_boots);


        List<String> helm_list = new ArrayList<String>();
        helm_list.add("<None>");
        List<String> armor_list = new ArrayList<String>();
        armor_list.add("<None>");
        List<String> leggings_list = new ArrayList<String>();
        leggings_list.add("<None>");
        List<String> boots_list = new ArrayList<String>();
        boots_list.add("<None>");


        // Creating adapter for spinner
        ArrayAdapter<String> helm_Adapter = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, helm_list);
        ArrayAdapter<String> armor_Adapter = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, armor_list);
        ArrayAdapter<String> leggings_Adapter = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, leggings_list);
        ArrayAdapter<String> boots_Adapter = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, boots_list);

        // Drop down layout style - list view with radio button
        helm_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        armor_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leggings_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boots_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Log.d("Test helm_adapter", spinner_helm.toString());
        spinner_helm.setAdapter(helm_Adapter);
        Log.d("Test helm_adapter after", spinner_helm.toString());

        spinner_armor.setAdapter(armor_Adapter);
        spinner_leggings.setAdapter(leggings_Adapter);
        spinner_boots.setAdapter(boots_Adapter);


        return rootView;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}