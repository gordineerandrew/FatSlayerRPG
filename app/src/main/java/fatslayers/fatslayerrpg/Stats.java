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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Stats extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner_helm;
    Spinner spinner_armor;
    Spinner spinner_leggings;
    Spinner spinner_boots;


    List<String> helm_list;
    List<String> armor_list;
    List<String> leggings_list;
    List<String> boots_list;

    TextView expBonus;
    TextView expBoost;

    boolean helm_boolean;
    boolean armor_boolean;
    boolean leggings_boolean;
    boolean boots_boolean;

    private int helm_int;
    private int armor_int;
    private int leggings_int;
    private int boots_int;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stats_screen, container, false);

        expBonus = rootView.findViewById(R.id.exp_bonus);
        expBoost = rootView.findViewById(R.id.exp_boost);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner_helm = view.findViewById(R.id.equip_helm);
        spinner_armor = view.findViewById(R.id.equip_armor);
        spinner_leggings = view.findViewById(R.id.equip_leggings);
        spinner_boots = view.findViewById(R.id.equip_boots);

        spinner_helm.setOnItemSelectedListener(this);
        spinner_armor.setOnItemSelectedListener(this);
        spinner_leggings.setOnItemSelectedListener(this);
        spinner_boots.setOnItemSelectedListener(this);

        helm_list = new ArrayList<String>();
        armor_list = new ArrayList<String>();
        leggings_list = new ArrayList<String>();
        boots_list = new ArrayList<String>();

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter helm_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, helm_list);
        ArrayAdapter armor_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, armor_list);
        ArrayAdapter leggings_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, leggings_list);
        ArrayAdapter boots_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, boots_list);

        helm_list.add("No Helm");
        armor_list.add("No Armor");
        leggings_list.add("No Leggings");
        boots_list.add("No Boots");


        helm_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        armor_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leggings_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boots_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner_helm.setAdapter(helm_Adapter);
        spinner_armor.setAdapter(armor_Adapter);
        spinner_leggings.setAdapter(leggings_Adapter);
        spinner_boots.setAdapter(boots_Adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        if(!item.equals("No Helm") && !item.equals("No Armor") && !item.equals("No Leggings")
                && !item.equals("No Boots")){
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



    public int getTotalBoost(){
        int total_boost = 0;


        if(spinner_helm.getSelectedItemId() != 0){
            total_boost += 1;
        }

        if(spinner_armor.getSelectedItemId() != 0){
            total_boost += 1;
        }

        if(spinner_leggings.getSelectedItemId() != 0){
            total_boost += 1;
        }

        if(spinner_boots.getSelectedItemId() != 0){
            total_boost += 1;
        }

        expBoost.setText(String.valueOf(total_boost));

        return total_boost;
    }

    public void addItems(boolean helm_bool, boolean armor_bool, boolean leggings_bool, boolean boots_bool){

        if(helm_bool && !helm_list.contains("Santa Hat")){
            helm_list.add("Santa Hat");
        }
        if(armor_bool && !armor_list.contains("Santa Coat")){
            armor_list.add("Santa Coat");
        }
        if(leggings_bool && !leggings_list.contains("Santa Trunks")){
            leggings_list.add("Santa Trunks");
        }
        if(boots_bool && !boots_list.contains("Santa Flip Flops")){
            boots_list.add("Santa Flip Flops");
        }

    }

    public void restoreState(int helm, int armor, int leggings, int boots) {
        spinner_helm.setSelection(helm);
        spinner_armor.setSelection(armor);
        spinner_leggings.setSelection(leggings);
        spinner_boots.setSelection(boots);

    }

    public int getHelm() {
        return spinner_helm.getSelectedItemPosition();
    }

    public int getArmor() {
        return spinner_armor.getSelectedItemPosition();
    }

    public int getLeggings() {
        return spinner_leggings.getSelectedItemPosition();
    }

    public int getBoots() {
        return spinner_boots.getSelectedItemPosition();
    }


}

