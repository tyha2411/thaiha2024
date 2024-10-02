package vn.edu.usth.weather2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        } );
        Adapter adapter = new Adapter(getSupportFragmentManager());
        TabLayout tabLayout;
        tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Create a new Fragment to be placed in the activity
//         ForecastFragment firstFragment = new ForecastFragment();
//        // Add the fragment to the 'container' FrameLayout
//        getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment).commit();
//
//        WeatherFragment secondFragment = new WeatherFragment();
//
//        getSupportFragmentManager().beginTransaction().add(R.id.container, secondFragment).commit();
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
// This method is executed in main thread
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
// this method is run in a worker thread
                try {
// wait for 5 seconds to simulate a long network access
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
// Assume that we got our data from server
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "some sample json here");
// notify main thread
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        t.start();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.song2);
            File outputFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "song2.mp3");
            OutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

            // Initialize MediaPlayer
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(outputFile.getAbsolutePath());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Handle the refresh action
        if (id == R.id.refresh) {
            refreshContent();
            return true;
        }
        // Handle the settings action
        if (id == R.id.settings) {
            Intent intent = new Intent(this, vn.edu.usth.weather2.ActionToolbar.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshContent() {
        Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
    }
    public WeatherActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Weather", "onStart here");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Weather", "onStop here");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.i("Weather", "onDestroy here");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        Log.i("Weather", "onPause here");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        Log.i("Weather", "onResume here");
    }
}