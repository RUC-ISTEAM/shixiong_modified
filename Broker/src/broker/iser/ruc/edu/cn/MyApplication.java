package broker.iser.ruc.edu.cn;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{
	public void onCreate()
	{
		super.onCreate();
		Log.i("MyApplication", "begining");
		Native n = new Native();
		n.Mod();
		Log.i("MyApplication", "ending");
	}
}
