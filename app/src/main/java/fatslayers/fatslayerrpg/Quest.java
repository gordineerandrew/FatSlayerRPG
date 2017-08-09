package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/30/2017.
 */
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class Quest extends Fragment implements StepListener, SensorEventListener {


    View myView;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private int questSteps;
    private TextView tv_steps;
    boolean helm_bool;
    boolean armor_bool;
    boolean leggings_bool;
    boolean boots_bool;
    private TextView helmGet;
    private TextView armorGet;
    private TextView leggingsGet;
    private TextView bootsGet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.quest_screen, container, false);

        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.quest_screen, container, false);
        tv_steps = rl.findViewById(R.id.tv_steps);
        helmGet = rl.findViewById(R.id.helmet_get);
        armorGet = rl.findViewById(R.id.armor_get);
        leggingsGet = rl.findViewById(R.id.leggings_get);
        bootsGet = rl.findViewById(R.id.boots_get);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        helmGet.setVisibility(View.VISIBLE);


//Tvsteps is initialised here
        Button BtnStart = rl.findViewById(R.id.btn_start);
        Button BtnStop = rl.findViewById(R.id.btn_stop);
        final CheckBox helmetCheck = rl.findViewById(R.id.Helmet);

        helmetCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //System.out.println("Checked");
//System.out.println("Un-Checked");
                helm_bool = helmetCheck.isChecked();
            }
        });
        final CheckBox armorCheck = rl.findViewById(R.id.Armor);

        armorCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //System.out.println("Checked");
//System.out.println("Un-Checked");
                armor_bool = armorCheck.isChecked();
            }
        });
        final CheckBox leggingsCheck = rl.findViewById(R.id.Leggings);

        leggingsCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //System.out.println("Checked");
//System.out.println("Un-Checked");
                leggings_bool = leggingsCheck.isChecked();
            }
        });
        final CheckBox bootsCheck = rl.findViewById(R.id.Boots);

        bootsCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //System.out.println("Checked");
//System.out.println("Un-Checked");
                boots_bool = bootsCheck.isChecked();
            }
        });

        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                questSteps = 0;
                sensorManager.registerListener(Quest.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });

        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(Quest.this);

            }
        });

        return rl;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        running = true;
//        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if (countSensor != null) {
//            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        } else {
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        running = false;
//        // unregister
//        //sensorManager.unregisterListener(this);
//    }
//    static int initialStepCount = 0;
//    static int stepCount = 0;
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (initialStepCount == 0) {
//            initialStepCount = (int) event.values[0];
//        }
//
//        stepCount = (int)event.values[0] - initialStepCount;
//
//        if (running) {
//            tv_steps.setText(String.valueOf(stepCount));
//
//        }
//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        questSteps++;
        tv_steps.setText(TEXT_NUM_STEPS + questSteps);

    }
    public ArrayList<String> getItemsList(){
        ArrayList<String> tempList = new ArrayList<>();
        if(helm_bool&& questSteps > 91){
            tempList.add("Helmet");
            helmGet.setVisibility(View.VISIBLE);
        }
        if(armor_bool&& questSteps > 81){
            tempList.add("Armor");
            armorGet.setVisibility(View.VISIBLE);
        }
        if(leggings_bool&& questSteps > 71){
            tempList.add("Leggings");
            leggingsGet.setVisibility(View.VISIBLE);
        }
        if(boots_bool&& questSteps > 11){
            tempList.add("Boots");
            bootsGet.setVisibility(View.VISIBLE);
        }
        return tempList;
    }
    public int getProgress(){
        return numSteps;
    }

    public void setProgress(int progress){
        numSteps = progress;
    }
}









