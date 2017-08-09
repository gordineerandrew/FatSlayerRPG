package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/30/2017.
 */

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Home extends Fragment {

    private ImageView getSkele1;
    private ImageView getSkele2;
    private ImageView getSkele3;

    private ImageView getGob1;
    private ImageView getGob2;
    private ImageView getGob3;

    private ImageView getOrc1;
    private ImageView getOrc2;
    private ImageView getOrc3;

    private ImageView getVik1;
    private ImageView getVik2;
    private ImageView getVik3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_screen, container, false);
        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.home_screen, container, false);

        getSkele1 = rl.findViewById(R.id.skele_sprite);
        getSkele2 = rl.findViewById(R.id.skele_sprite2);
        getSkele3 = rl.findViewById(R.id.skele_sprite3);

        getGob1 = rl.findViewById(R.id.gobble_sprite);
        getGob2 = rl.findViewById(R.id.gobble_sprite2);
        getGob3 = rl.findViewById(R.id.gobble_sprite3);

        getOrc1 = rl.findViewById(R.id.orca_sprite);
        getOrc2 = rl.findViewById(R.id.orca_sprite2);
        getOrc3 = rl.findViewById(R.id.orca_sprite3);

        getVik1 = rl.findViewById(R.id.vicky_sprite);
        getVik2 = rl.findViewById(R.id.vicky_sprite2);
        getVik3 = rl.findViewById(R.id.vicky_sprite3);




        return rl;
    }

    public void showFigures(int level, int boost){
        System.out.println("Inside ShowFigures The Level is: "+ level + ". The boost is: "+ boost);
        if(boost <=1){
            System.out.println("Skeleton Should be showing");
            if(level < 5){
                turnOff();
                getSkele3.setVisibility(View.VISIBLE);
            }else if(level <10){
                turnOff();
                getSkele2.setVisibility(View.VISIBLE);
            }else{
                turnOff();
                getSkele1.setVisibility(View.VISIBLE);
            }
        }else if(boost ==2){
            System.out.println("Gob Should be showing");
            if(level < 5){
                turnOff();
                getGob3.setVisibility(View.VISIBLE);
            }else if(level <10){
                turnOff();
                getGob2.setVisibility(View.VISIBLE);
            }else{
                turnOff();
                getGob1.setVisibility(View.VISIBLE);
            }
        }else if(boost ==3){
            System.out.println("Orc Should be showing");
            if(level < 5){
                turnOff();
                getOrc3.setVisibility(View.VISIBLE);
            }else if(level <10){
                turnOff();
                getOrc2.setVisibility(View.VISIBLE);
            }else{
                turnOff();
                getOrc1.setVisibility(View.VISIBLE);
            }
        }else{
            System.out.println("Viking Should be showing");
            if(level < 5){
                turnOff();
                getVik3.setVisibility(View.VISIBLE);
            }else if(level <10){
                turnOff();
                getVik2.setVisibility(View.VISIBLE);
            }else{
                turnOff();
                getVik1.setVisibility(View.VISIBLE);
            }
        }
    }

    public void turnOff (){
        getSkele1.setVisibility(View.INVISIBLE);
        getSkele2.setVisibility(View.INVISIBLE);
        getSkele3.setVisibility(View.INVISIBLE);
        getGob1.setVisibility(View.INVISIBLE);
        getGob2.setVisibility(View.INVISIBLE);
        getGob3.setVisibility(View.INVISIBLE);
        getOrc1.setVisibility(View.INVISIBLE);
        getOrc2.setVisibility(View.INVISIBLE);
        getOrc3.setVisibility(View.INVISIBLE);
        getVik1.setVisibility(View.INVISIBLE);
        getVik2.setVisibility(View.INVISIBLE);
        getVik3.setVisibility(View.INVISIBLE);
    }






}
