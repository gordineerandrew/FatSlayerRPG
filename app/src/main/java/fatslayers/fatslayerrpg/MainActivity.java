package fatslayers.fatslayerrpg;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "pls no error";

    private QuestGame questGame = new QuestGame();
    private ProgressBar expBar = null;
    public boolean inQuest = false;
    private Quest quest;
    private final int Exp = 5;
    private int numSteps;
    private Intent music = new Intent();

    private TextView levelText;
    private TextView nameText;

    private int level = 1;

    public boolean mSoundOn;

    private static final int SETTINGS_REQUEST = 0;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;

    private boolean mIsBound = false;
    private String username;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameText = (TextView) findViewById(R.id.userId);
        levelText = (TextView) findViewById(R.id.userLvl);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        expBar = (ProgressBar) findViewById(R.id.progressBar);
        expBar.setMax(Exp);

        doBindService();


        music.setClass(this,MusicService.class);
        startService(music);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //update here
                if(inQuest){

                    if(quest.getProgress() == 0){
                        quest.setProgress(numSteps);
                    }

                    expBar.setProgress(quest.getProgress()%(Exp+1));
                    //TODO: LEVEL UP
                    if(quest.getProgress()>0 && quest.getProgress()%(Exp)==0) {
                        expBar.setProgress(1);
                        numSteps = quest.getProgress();
                        level = numSteps/Exp;
                        levelText.setText(String.valueOf(level));
                    }
                }
                handler.postDelayed(this,500);
            }
        });

        restoreData();
        levelText.setText(String.valueOf(level));
        nameText.setText(username);


//        quest.setProgress(numSteps);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("name",username);
            startActivityForResult(intent, SETTINGS_REQUEST);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (inQuest){
            editor.putInt("numSteps", quest.getProgress());

        }
        editor.putInt("expBar", expBar.getProgress());
        editor.putInt("level", level);
        editor.putString("username", username);
        stopService(music);
        doUnbindService();
        editor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();
        startService(music);
    }

//
//    @Override
//    protected void onPause(){
//        super.onPause();
//        mServ.pauseMusic();
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mServ.stopMusic();
//        doUnbindService();
//    }

    private void restoreData(){
        SharedPreferences sharedPref = getPreferences (MODE_PRIVATE);
        mSoundOn = sharedPref.getBoolean ("sound", true);
        expBar.setProgress(sharedPref.getInt("expBar", 0));
        numSteps = sharedPref.getInt("numSteps", 0);
        level = sharedPref.getInt("level", 1);
        username = sharedPref.getString("username", "Player");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        username=data.getStringExtra("name");
        nameText.setText(username);
        if (requestCode == SETTINGS_REQUEST) {

            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            // Apply potentially new settings
            mSoundOn = sharedPref.getBoolean("sound", true);
            Log.d(TAG,"sound is on: "+sharedPref.getBoolean("sound", true));
            //mHumanWinString = sharedPref.getString ("victory_message", "You Won!");
            String[] levels = getResources().getStringArray(R.array.difficulty_levels);
            // set difficulty, or use hardest if not present,
            String difficultyLevel
                    = sharedPref.getString("difficulty_level", levels[levels.length - 1]);
            int i = 0;
            while(i < levels.length) {
                if(difficultyLevel.equals(levels[i])) {
                    questGame.setDifficultyLevel(QuestGame.DifficultyLevel.values()[i]);
                    i = levels.length; // to stop loop
                }
                i++;
            }
        }
    }

    public void setDifficulty(int difficulty) {
        // check bounds;
        if (difficulty < 0 || difficulty >= QuestGame.DifficultyLevel.values().length) {
            Log.d(TAG, "Unexpected difficulty: " + difficulty + "." +
                    " Setting difficulty to Easy / 0.");
            difficulty = 0; // if out of bounds set to 0
        }
        QuestGame.DifficultyLevel newDifficulty
                = QuestGame.DifficultyLevel.values()[difficulty];

        questGame.setDifficultyLevel(newDifficulty);
        String message = "Difficulty set to " +
                newDifficulty.toString().toLowerCase() + " .";
    }
            /**
             * A placeholder fragment containing a simple view.
             */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.home_screen, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }

            /**
             * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
             * one of the sections/tabs/pages.
             */
            public class SectionsPagerAdapter extends FragmentPagerAdapter {

                public SectionsPagerAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int position) {
                    switch (position) {
                        case 0:
                            Home home = new Home();
                            return home;
                        case 1:
                            Craft craft = new Craft();
                            return craft;
                        case 2:
                            quest = new Quest();
                            inQuest = true;
                            return quest;
                        case 3:
                            Stats stats = new Stats();
                            return stats;
                        default:
                            return null;
                    }
                }

                @Override
                public int getCount() {
                    // Show 4 total pages.
                    return 4;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position) {
                        case 0:
                            return "HOME";
                        case 1:
                            return "CRAFT";
                        case 2:
                            return "QUEST";
                        case 3:
                            return "STATS";
                    }
                    return null;
                }
            }

        }

