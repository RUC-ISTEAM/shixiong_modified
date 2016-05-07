package target.iser.ruc.edu.cn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    
	static {
		System.out.println("I am MainActivity1");
	}
	Button first;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			System.out.println("I am MainActivity2");
		    setContentView(R.layout.activity_main);
		    first=(Button)findViewById(R.id.button1);  
	       
	        first.setOnClickListener(new OnClickListener()  
	        {         
	            public void onClick(View v)  
	            {  
	              Log.d("TARGET", "you click it");
	              first.append("you click it!");
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
