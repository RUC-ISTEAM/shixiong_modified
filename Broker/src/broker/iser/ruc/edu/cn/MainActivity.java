package broker.iser.ruc.edu.cn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import android.app.Activity;
import android.app.ResultInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.BrokerBinderProxy;
import android.os.BrokerSystemManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IServiceManager;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
    //public static final String TARGET = "target.iser.ruc.edu.cn";
	private Button button1;//to start target activity
	private EditText editText1;//to get target package's name
    

  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("Broker PID: " + Process.myPid());				
		super.onCreate(savedInstanceState);
		System.out.println("state:"+savedInstanceState);
		setContentView(R.layout.activity_main);
		startTargetActivity();
		
		
		
	}
	private void startTargetActivity(){
		button1 = (Button)findViewById(R.id.button1);
		editText1 =(EditText)findViewById(R.id.editText1);
	    button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String content = editText1.getText().toString();
				Intent intent = new Intent(MainActivity.this,TargetActivity.class);
				intent.putExtra("TargetName", content);
				startActivity(intent);				
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
