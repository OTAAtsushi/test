package com.example.sample;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	private String path = "mydata.txt";
	private String path2 = "mydata2.txt";
	private String a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void doSave(View view){
		EditText edit1 = (EditText)this.findViewById(R.id.editText1);
		Editable str = edit1.getText();
		FileOutputStream output = null;
		try{
			output = this.openFileOutput(path2, Context.MODE_APPEND);	//MODE_APPEND=追加書き込み	MODE_PRIVATE=他のアプリからのアクセス不可
			output.write(str.toString().getBytes());
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void doLoad(View view){
		EditText edit1 = (EditText)this.findViewById(R.id.editText1);
		FileInputStream input = null;
		try{
			input = this.openFileInput(path2);
			byte[] buffer = new byte[1000];
			input.read(buffer);
			String s = new String(buffer).trim();
			edit1.setText(s);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void doReset(View view){
		FileOutputStream output = null;
		try{
			a = null;
			output = this.openFileOutput(path2, Context.MODE_PRIVATE);	//MODE_APPEND=追加書き込み	MODE_PRIVATE=他のアプリからのアクセス不可
			output.write(a.getBytes());
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
