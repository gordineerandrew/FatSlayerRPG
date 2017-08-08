package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/30/2017.
 */

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private List<String> helms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_screen, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner_helm = (Spinner) view.findViewById(R.id.equip_helm);
        spinner_armor = (Spinner) view.findViewById(R.id.equip_armor);
        spinner_leggings = (Spinner) view.findViewById(R.id.equip_leggings);
        spinner_boots = (Spinner) view.findViewById(R.id.equip_boots);

        spinner_helm.setOnItemSelectedListener(this);
        spinner_armor.setOnItemSelectedListener(this);
        spinner_leggings.setOnItemSelectedListener(this);
        spinner_boots.setOnItemSelectedListener(this);

        List<String> helm_list = new ArrayList<String>();
        List<String> armor_list = new ArrayList<String>();
        List<String> leggings_list = new ArrayList<String>();
        List<String> boots_list = new ArrayList<String>();

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter helm_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, helm_list);
        ArrayAdapter armor_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, armor_list);
        ArrayAdapter leggings_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, leggings_list);
        ArrayAdapter boots_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, boots_list);

        helm_list.add("<None>");
        armor_list.add("<None>");
        leggings_list.add("<None>");
        boots_list.add("<None>");

        helm_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        armor_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leggings_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boots_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner_helm.setAdapter(helm_Adapter);
        spinner_armor.setAdapter(armor_Adapter);
        spinner_leggings.setAdapter(leggings_Adapter);
        spinner_boots.setAdapter(boots_Adapter);

        helm_list.add("Swag");



    }







    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}