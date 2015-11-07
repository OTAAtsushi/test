package com.example.sample;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class OtherActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		 ScrollView scr_view = (ScrollView)findViewById(R.id.scr_view);
		    Button button[] =new Button[3];
		    int h=100;
		    int w=200;
		    int hh = 30;
		    int ww = 30;

		    //---------(1)�c���јg��S��ʕ\���Őݒu���܂��B
		    LinearLayout LL0 = new LinearLayout(this);
		    LL0.setOrientation(LL0.VERTICAL);
		    LL0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		        //---------(2)�����јg���Őݒu���܂��B
		        LinearLayout LL1 = new LinearLayout(LL0.getContext());
		        LL1.setOrientation(LL1.HORIZONTAL);
		         //---------(3)�c���јg��ݒu���܂��B
		        LinearLayout ll = new LinearLayout(LL1.getContext());
		        ll.setOrientation(ll.VERTICAL);
		         //---------(4)�c���јg�̍ŏ���Button_1�̃{�^����ݒu���܂��B
		        for(int i=0;i<10;i++){
		        	 button[i] = new Button(this);
				     button[i].setText((i+1)+"");
				     button[i].setHeight(hh);
				     button[i].setWidth(ww);
				     button[i].setOnClickListener(this);
				     ll.addView(button[i]);
		        }
		       
		         //---------(5)�c���јg��2�i�ڂ�Button_2�̃{�^����ݒu���܂��B
		        button[1] = new Button(this);
		        button[1].setText("Button_2");
		        button[1].setHeight(h);
		        button[1].setWidth(w);
		        ll.addView(button[1]);
		        //---------(6)�c���јg����܂��B
		        LL1.addView(ll);
		        //---------(7)�����јg��Button_3��(������{�ɂ���)�{�^����ݒu���܂��B
		        button[2] = new Button(this);
		        button[2].setText("Button_3");
		        button[2].setHeight(h*2);
		        button[2].setWidth(w);
		        LL1.addView(button[2]);
		    //---------(8)�����јg����܂��B
		    LL0.addView(LL1);
		    //---------(9)�S��ʃT�C�Y�̏c���јg����܂��B
		    scr_view.addView(LL0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
