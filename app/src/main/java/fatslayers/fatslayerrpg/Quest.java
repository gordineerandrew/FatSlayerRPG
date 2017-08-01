package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/30/2017.
 */
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Quest extends Fragment implements StepListener, SensorEventListener {

    boolean running = false;
    View myView;

    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    TextView tv_steps;

    int initialValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.quest_screen, container, false);

        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.quest_screen, container, false);
        tv_steps = rl.findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

//Tvsteps is initialised here
        Button BtnStart = rl.findViewById(R.id.btn_start);
        Button BtnStop = rl.findViewById(R.id.btn_stop);

        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
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

//error in this Tvsteps
        tv_steps.setText(TEXT_NUM_STEPS + numSteps);

    }
}









