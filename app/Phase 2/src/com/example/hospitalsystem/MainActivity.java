package com.example.hospitalsystem;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The title screen, which contains a button to begin the program and search patient by health card number.
 * Also automatically loads the current patient database and serializes it for later use by other activites.
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button searchButton;
	private PatientSystem patientSystem;
	private File f; 
	private File serialize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		f = new File(this.getApplicationContext().getFilesDir().getAbsolutePath() + "/patient_records.txt");
		/*Testing Code
		TextView test = new TextView(this);
		test.setText(this.getApplicationContext().getFilesDir().getAbsolutePath().toString());
		setContentView(R.layout.activity_main);
		*/
		//Instantiate Patient System
		patientSystem = new PatientSystem();
		try {  
			patientSystem.populateSystem(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		serialize = new File(this.getApplicationContext().getFilesDir().getAbsolutePath());
		try {
			patientSystem.serialize(serialize);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		searchButton = (Button) findViewById(R.id.LookUpPatient);
		searchButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				clickSearch();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


	}

	
	/**
	 * Executed when search button is pressed. Starts up the LookUpPatient activity.
	 */
	public void clickSearch() {
		Intent searchIntent = new Intent(this, LookUpPatient.class);
		startActivity(searchIntent);
	}
	
}
