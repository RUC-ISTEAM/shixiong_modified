package broker.iser.ruc.edu.cn;

import android.app.Activity;
import android.app.BrokerActivityManagerProxy;
import android.app.ContentProviderHolder;
import android.app.IActivityManager;
import android.app.IApplicationThread;

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
import android.widget.TextView;
import android.widget.Toast;

public class TargetActivity extends Activity{
    private String TARGET;
    private TextView tv;
    public static Context context = null;
    public static IInterface sServiceManager;
	private IIsolatedProcessService mService;
	private IBinder temp;//temp service 
	private static Object AppThreadBroker;
	private static Context context2 =null;

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
    		Context context = (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, TargetActivity.this);
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
				ActivityInfo info = context.getPackageManager().getActivityInfo(component, PackageManager.GET_META_DATA);
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
            Toast.makeText(TargetActivity.this, "Service connected", Toast.LENGTH_SHORT).show();          
            mService = IIsolatedProcessService.Stub.asInterface(service);            
            IBinder applicationThread = getApplicationThread();
            try{
            	mService.registerCallBack(mBrokerProcess);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
            try {
				mService.setRealServiceManager(sServiceManager.asBinder());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Log.d("order", "onConnected:before bindApplication ");
            bindApplication(applicationThread);
            Log.d("order", "onConnected:after bindApplication ");
            getActivityThread();
            Log.d("order", "onConnected:before launchApplication ");
            scheduleLaunchActivity(applicationThread); 
            Log.d("order", "onConnected:after launchApplication ");
            try {
            	Log.d("NEWS FORM TARGET", "TargetActivity want to help set the package manager of target!");
				mService.trySetPackageManager();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            try {
            	Log.d("NEWS FORM TARGET", "TargetActivity want to help set the sCache!");
				mService.trySetServiceCache();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            IBinder b = null;
            try {
				 b=BrokerSystemManager.mBrokerSystemManager.getService("activity");
				 Log.d("NEWS FORM TARGET", "TargetActivity get the AMBinder--"+b);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}           
            try {
            	Log.d("NEWS FORM TARGET", "TargetActivity want to set AMBinder");
				mService.transAMBinder(b);;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
            	Log.d("NEWS FORM TARGET", "TargetActivity want to help change AM");
				mService.tryChangeAm();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
//            try {
//				mService.tryClearViolations();
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(TargetActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
            try{
            	mService.unregisterCallBack(mBrokerProcess);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
            mService = null;
        }
    };
	public void startIsolatedProcessService() {
    	Intent intent = new Intent("broker.iser.ruc.edu.cn.IsolatedProcessService");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
	}

	private IBinder getmRemote() {
		IActivityManager BrokerProxy=(IActivityManager)Reflect.invokeMethod("android.app.ActivityManagerNative","getDefault",context);
		IBinder mRemote = (IBinder) Reflect.getField("android.app.ActivityManagerProxy", "mRemote", BrokerProxy);
		return mRemote;
	}
	private static void getCaller(){
   
	Object activityThread = Reflect.getField("android.app.ContextImpl", "mMainThread", context2);
	Object applicationThread = Reflect.invokeMethod("android.app.ActivityThread", "getApplicationThread", activityThread, null); 
    Log.d("CALLER", "called getApplicationThread() xx" +applicationThread);
    AppThreadBroker = applicationThread;
	}
	public IBrokerProcess mBrokerProcess = new IBrokerProcess.Stub() {

			@Override
			public IBinder getService(String name) throws RemoteException {
				// TODO Auto-generated method stub
				return tryServiceManager(name);
			}
//			private ContentProviderHolder getContentProvider(IBinder caller, String name, boolean stable) {
//	    		Parcel data = Parcel.obtain();
//	    		Parcel reply = Parcel.obtain();
//            caller=AppThreadBroker;
//	        data.writeInterfaceToken(IActivityManager.descriptor);
//	        data.writeStrongBinder(caller != null ? caller: null);
//	        data.writeString(name);
//	        data.writeInt(stable ? 1 : 0);
//	        IBinder ib = getmRemote();
//	        System.out.println("mRemote:"+ib);
//	        try {
//				ib.transact(29, data, reply, 0);
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        reply.readException();
//	        int res = reply.readInt();
//	        
//	        Log.d("GOODBYE","READ REPLY1 --"+res );
//	        ContentProviderHolder cph = null;
//	        if (res != 0) {
//	            cph = ContentProviderHolder.CREATOR.createFromParcel(reply);
//	        }
//	        data.recycle();
//	        reply.recycle();
//	        Log.d("GOODBYE","READ REPLY2 --"+ cph==null?null:cph.toString() );
//	        return cph;
//	    	}
			
			@Override
			public ContentProviderHolder getHolder(IBinder AppThread,String name, boolean stable) throws RemoteException {
				// TODO Auto-generated method stub
				ContentProviderHolder BrokerHolder;
				IActivityManager BrokerProxy=(IActivityManager)Reflect.invokeMethod("android.app.ActivityManagerNative","getDefault",context);
				BrokerHolder=(ContentProviderHolder) Reflect.invokeMethod("android.app.ActivityManagerProxy", "getContentProvider", BrokerProxy,AppThreadBroker,name,stable );
				//BrokerHolder = getContentProvider(AppThread, name, stable);
				Log.d("BYE", "BRoker get holder--"+BrokerHolder);
				return BrokerHolder;
			}
			
	  	  
	};
	
	private void getServiceManager(){
		sServiceManager = (IInterface) Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null); 
		System.out.println("sServiceManager of broker: "+sServiceManager);
	}
	private IBinder tryServiceManager(String name) {
		// TODO Auto-generated method stub
		System.out.println("I:change ServiceManager to real");
		Reflect.setField("android.os.ServiceManager", "broker", null, null);
		temp = (IBinder) Reflect.invokeMethod("android.os.ServiceManager", "getService", null, name);
		Log.d("SM","YOU GOT THE SERVICE"+temp);		
		return temp;
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("TargetActivity PID: " + Process.myPid());		
		setContentView(R.layout.targer_activity);
		TargetActivity.context = this;
		context2= (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, (Context)this);
		getCaller();
		Intent intent = getIntent();
		TARGET=intent.getStringExtra("TargetName");
		tv=(TextView)findViewById(R.id.textView1);
		tv.append(TARGET);	
		getServiceManager();
        Log.d("SM", "I:getServiceManagerOfBroker"); 
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
