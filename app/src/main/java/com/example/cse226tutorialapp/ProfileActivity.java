package com.example.cse226tutorialapp;

import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cse226tutorialapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button start;
    Button RStart;
    Button cancel;
    EditText text;
    int i;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Button btnStartProgress;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        YouTubePlayerView youTubePlayerView1 = findViewById(R.id.youtube_player_view1);
        getLifecycle().addObserver(youTubePlayerView1);
        YouTubePlayerView youTubePlayerView2 = findViewById(R.id.youtube_player_view2);
        getLifecycle().addObserver(youTubePlayerView2);
        YouTubePlayerView youTubePlayerView3 = findViewById(R.id.youtube_player_view3);
        getLifecycle().addObserver(youTubePlayerView3);
        YouTubePlayerView youTubePlayerView4 = findViewById(R.id.youtube_player_view4);
        getLifecycle().addObserver(youTubePlayerView4);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        String email = user.getEmail();

        TextView welcomeText =  findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome To Our Android Tutorial Application\n Email ID : " + email);

        Button logoutButton =  findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });

        start = findViewById(R.id.button);
        RStart = findViewById(R.id.RStart);
        cancel = findViewById(R.id.cancel_button);
        text = findViewById(R.id.time);
        ratingbar = findViewById(R.id.ratingbar);
        Button btn = findViewById(R.id.btnShow);
        ratingbar.setRating(0.0f);

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingbar.setRating(rating);
                Toast.makeText(ProfileActivity.this,"New Rating is : "+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast_layout,
                        (ViewGroup) findViewById(R.id.custom_toast_layout));
                TextView tv = (TextView) layout.findViewById(R.id.txtvw);
                tv.setText("Hurray....!!");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
        addListenerOnButtonClick();

        intent = new Intent(ProfileActivity.this,
                com.example.cse226tutorialapp.AlarmBroadcastManager.class);
        pendingIntent = PendingIntent.getBroadcast
                (ProfileActivity.this,234,intent,0 );

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = Integer.parseInt(text.getText().toString());
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis()
                                + (i * 1000), pendingIntent);
                Toast.makeText(ProfileActivity.this,
                        "Alarm set in " + i + " seconds",Toast.LENGTH_LONG).show();
            }
        });
        RStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating
                        (AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),
                                (1000*5),pendingIntent);
                Toast.makeText(ProfileActivity.this,
                        "Repeating Alarm 5 Seconds",Toast.LENGTH_LONG).show();


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pendingIntent);
                com.example.cse226tutorialapp.AlarmBroadcastManager.mp.stop();
                Toast.makeText(ProfileActivity.this,
                        "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.aq:
                Intent askq = new Intent(ProfileActivity.this, AskQuery.class);
                startActivity(askq);
                Toast.makeText(getApplicationContext(),"You have clicked on Ask Query?",Toast.LENGTH_LONG).show();
                return true;
            case R.id.ri:
                Intent rps = new Intent(ProfileActivity.this, ReportIssue.class);
                startActivity(rps);
                Toast.makeText(getApplicationContext(),"You have clicked on Report-Issue.",Toast.LENGTH_LONG).show();
                return true;
            case R.id.fb:
                Intent fbk = new Intent(ProfileActivity.this, Feedback.class);
                startActivity(fbk);
                Toast.makeText(getApplicationContext(),"You have clicked on Feedback",Toast.LENGTH_LONG).show();
                return true;
            case R.id.au:
                Intent auk = new Intent(ProfileActivity.this, AboutUs.class);
                startActivity(auk);
                Toast.makeText(getApplicationContext(),"You have clicked on About US",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addListenerOnButtonClick() {
        btnStartProgress = findViewById(R.id.button1);
        btnStartProgress.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // creating progress bar dialog
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("File downloading ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                //reset progress bar and filesize status
                progressBarStatus = 0;
                fileSize = 0;

                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {
                            // performing operation
                            progressBarStatus = doOperation();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // Updating the progress bar
                            progressBarHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }
                        // performing operation if file is downloaded,
                        if (progressBarStatus >= 100) {
                            // sleeping for 1 second after operation completed
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // close the progress bar dialog
                            progressBar.dismiss();
                        }
                    }
                }).start();
            }//end of onClick method
        });
    }
    // checking how much file is downloaded and updating the filesize
    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40; // you can add more else if
            }
         /* else {
                return 100;
            }*/
        }//end of while
        return 100;
    }//end of doOperation
}