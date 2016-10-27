package com.test.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

 
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
	}
	
	public void onClick(View v){
		 Intent intent = new Intent(this,HelloDemoActivity.class);
		 startActivity(intent);
	}
}
