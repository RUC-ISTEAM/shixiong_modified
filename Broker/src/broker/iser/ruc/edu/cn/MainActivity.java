package broker.iser.ruc.edu.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ResultInfo;
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
import android.os.IServiceManager;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    public static final String TARGET = "target.iser.ruc.edu.cn";
    public static Context context = null;
    public static Object sServiceManager;
	private IIsolatedProcessService mService;
	private IServiceManager nService;
    
	private void getServiceManager(){
	    Log.d("!!!","excuseme");
		sServiceManager = Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null); 
		System.out.println("sServiceManager"+sServiceManager);
	}
    private ServiceConnection mServiceConnection = new ServiceConnection() {
    	
    	private IBinder getApplicationThread() {
    		IBinder result = null;
    		try {
    			result = mService.getApplicationThread();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}    		
    		return result;
    	}
    	    	
    	private IBinder getToken() {
    		Context context = (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, MainActivity.this);
        	IBinder token = (IBinder) Reflect.getField("android.app.ContextImpl", "mActivityToken", context);
    				
    		System.out.println("token:"+token);
    		return token;
    	}
    	private Object getActivityThread() {
    		Object ActivityThread = Reflect.invokeMethod("android.app.ActivityThread", "currentActivityThread", null, null);
    		System.out.println("ActivityThread:"+ActivityThread);
    		Object ActivityClientRecord = Reflect.getField("android.app.ActivityThread", "mNewActivities", ActivityThread);
    		System.out.println("ActivityClientRecord:"+ActivityClientRecord);
    		ParcelFileDescriptor profileFd = (ParcelFileDescriptor) Reflect.getField("android.app.ActivityThread$ActivityClientRecord", "profileFd", ActivityClientRecord);
    		System.out.println("profileFd:"+profileFd);
    		return ActivityThread;
    	}
    	
    	private void scheduleLaunchActivity(IBinder applicationThread) {
    		IBinder token = (IBinder) BrokerBinderProxy.mBrokerBinderProxy;
    		
    		Parcel data = Parcel.obtain();
    		data.writeInterfaceToken("android.app.IApplicationThread"); //descriptor
    		Intent intent = new Intent();//intent
    		intent.setAction("target.iser.Main");
    		intent.writeToParcel(data, 0);
    		data.writeStrongBinder(getToken()); //token
//    		data.writeStrongBinder(token);
    		System.out.println("token:"+token);
    		data.writeInt(0); //ident    		
			try {
				ComponentName component = new ComponentName("target.iser.ruc.edu.cn", "target.iser.ruc.edu.cn.MainActivity");
				ActivityInfo info = getPackageManager().getActivityInfo(component, PackageManager.GET_META_DATA);
				info.writeToParcel(data, 0);
				System.out.println("target:"+info.getThemeResource());
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //info

    		Configuration curConfig = new Configuration();  //curConfig
    		curConfig.writeToParcel(data,0);
    		CompatibilityInfo compatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;  //compatInfo
    		compatInfo.writeToParcel(data, 0);
    		Bundle state = new Bundle();  //state
//		    Fragment fragment = ;
    		data.writeBundle(state);    		
    		
    		//List<ResultInfo> pendingResults
    		List<ResultInfo> pendingResults = new ArrayList<ResultInfo>();
    		data.writeTypedList(pendingResults);
    		
    		List<Intent> pendingNewIntents = new ArrayList<Intent>();   //pendingNewIntents 
    		data.writeTypedList(pendingNewIntents);
    		data.writeInt(0);  //notResumed
    		data.writeInt(0);  //isForward
    		data.writeString("profilename");  //profileName
    		data.writeInt(0);
    		
//    		data.writeInt(1);             
//    		ParcelFileDescriptor profileFd = new ParcelFileDescriptor(null);    //profileFd
//    		profileFd.writeToParcel(data, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
    		
    		data.writeInt(0);
    		
    		Log.d("schedule","launch2");
    		
    		try {
				applicationThread.transact(7, data, null, IBinder.FLAG_ONEWAY);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    		data.recycle();
    	}
    	
    	private void bindApplication(IBinder applicationThread) {
    		 
    		System.out.println("bind Application 1");
    		
			try {
				Parcel data =Parcel.obtain();
				data.writeInterfaceToken("android.app.IApplicationThread");
				data.writeString(TARGET);
				
				System.out.println("bind Application 2");
				
				ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(TARGET, 0);
				appInfo.writeToParcel(data,0);
				List<ProviderInfo> providers = null;
				data.writeTypedList(providers);//providers  
				data.writeInt(0);//testname ==NULL
				
				System.out.println("bind Application 3");
				String profileName =null;
				data.writeString(profileName);
				data.writeInt(0);//profileFd
				data.writeInt(0);//autoStopProfiler
				
				System.out.println("bind Application 4");
				Bundle testArgs = new Bundle();
				data.writeBundle(testArgs);//testArgs
				data.writeStrongInterface(null);//testWatcher
				data.writeInt(0);//debugMode;
				data.writeInt(0);//openGLtrace
				data.writeInt(0);//restricted backup mode
				data.writeInt(0);//persistent   		    
				
				System.out.println("bind Application 5");
				Configuration config = new Configuration();
				config.setToDefaults();   		   
				config.locale=new Locale("en_US");
				config.writeToParcel(data, 0); 			
				data.writeInt(1);//
				data.writeInt(DisplayMetrics.DENSITY_DEFAULT);
				data.writeFloat(1.0f);
				data.writeFloat(1.0f);
				HashMap<String, IBinder> services = null;
				data.writeMap(services);//data.writeMap(services);
			    Bundle coreSettings =new Bundle();
				data.writeBundle(coreSettings);//data.writeBundle(coreSettings);          
				
				System.out.println("bind Application 6");
           
				applicationThread.transact(13, data, null, IBinder.FLAG_ONEWAY);
				data.recycle();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_SHORT).show();          
            mService = IIsolatedProcessService.Stub.asInterface(service);
            IBinder applicationThread = getApplicationThread();
            bindApplication(applicationThread);
            getActivityThread();
            scheduleLaunchActivity(applicationThread);
            
        /*    try {
				mService.registerCallBack(mBroker);
			} catch (RemoteException e) {
				e.printStackTrace();
			}*/
           
            
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
        }
    };
	
	public void startIsolatedProcessService() {
    	Intent intent = new Intent("broker.iser.ruc.edu.cn.IsolatedProcessService");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
	}
	public void startSetServiceManager() throws RemoteException {
		Log.d("!!!", "enter StartServiceManager");
		BrokerSystemManager.setSM (sServiceManager);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		System.out.println("Broker PID: " + Process.myPid());
		
		super.onCreate(savedInstanceState);
		System.out.println("state:"+savedInstanceState);
		setContentView(R.layout.activity_main);
		MainActivity.context = this;
		getServiceManager();
        Log.d("!!!", "getServiceManager!!!");
        try {
			startSetServiceManager();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
     
		startIsolatedProcessService();
		
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
