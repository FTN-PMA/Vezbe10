package com.example.audiorecordingexample;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaRecorder myAudioRecorder;
	private String outputFile = null;
	private Button start, stop, play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);
		play = (Button) findViewById(R.id.button3);

		stop.setEnabled(false);
		play.setEnabled(false);
		outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/myrecording.3gp";
		;

		myAudioRecorder = new MediaRecorder();
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(outputFile);

	}

	public void start(View view) {
		try {
			myAudioRecorder.prepare();
			myAudioRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start.setEnabled(false);
		stop.setEnabled(true);
		Toast.makeText(getApplicationContext(), "Recording started",
				Toast.LENGTH_LONG).show();

	}

	public void stop(View view) {
		myAudioRecorder.stop();
		myAudioRecorder.release();
		myAudioRecorder = null;
		stop.setEnabled(false);
		play.setEnabled(true);
		Toast.makeText(getApplicationContext(), "Audio recorded successfully",
				Toast.LENGTH_LONG).show();
	}


	public void play(View view) throws IllegalArgumentException,
			SecurityException, IllegalStateException, IOException {

		MediaPlayer m = new MediaPlayer();
		m.setDataSource(outputFile);
		m.prepare();
		m.start();
		Toast.makeText(getApplicationContext(), "Playing audio",
				Toast.LENGTH_LONG).show();

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
