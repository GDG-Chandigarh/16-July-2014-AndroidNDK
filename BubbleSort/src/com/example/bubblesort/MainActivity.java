package com.example.bubblesort;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	static {
		System.loadLibrary("bubble-sort");
	}
	
	public native long bubbleSortC(int[] input);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void performBubbleSort(View view){
		int limit = 5000;
		int[] input_java = new int[limit];
		int[] input_c = new int[limit];
		for(int i=limit -1; i >= 0; i--){
			input_java[i] = i;
			input_c[i] = i;
		}
		float timetaken_java = bubbleSortJava(input_java);
		long timetaken_c = bubbleSortC(input_c);
		TextView text_view_java = (TextView)findViewById(R.id.textView2);
		TextView text_view_c = (TextView)findViewById(R.id.textView3);
		text_view_java.setText("Time taken for bubble sort in Java is "+timetaken_java+" micro sec.");
		text_view_c.setText("Time taken for bubble sort in C is "+timetaken_c+" micro sec.");
	}
	
	public long bubbleSortJava(int[] input){
		int n, c, d, swap;
		n = input.length;
		long tstart,tend;
		tstart = System.nanoTime();
	    for (c = 0; c < ( n - 1 ); c++) {
	      for (d = 0; d < n - c - 1; d++) {
	        if (input[d] > input[d+1]) 
	        {
	          swap       = input[d];
	          input[d]   = input[d+1];
	          input[d+1] = swap;
	        }
	      }
	    }
	    tend = System.nanoTime();
	    long time_diff = (tend - tstart)/1000;
	    return time_diff;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
