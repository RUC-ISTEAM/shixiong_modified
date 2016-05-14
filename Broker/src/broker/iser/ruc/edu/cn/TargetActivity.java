package broker.iser.ruc.edu.cn;

import android.R.integer;
import android.app.Activity;
import android.app.BrokerActivityManagerProxy;
import android.app.ContentProviderHolder;
import android.app.IActivityManager;
import android.app.IApplicationThread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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
    public static IBinder AppThreadIso=null;//isolated process applicationThread
    public static IInterface sServiceManager;
	private IIsolatedProcessService mService;
	private IBinder temp;//temp service 
	private static android.app.IApplicationThread AppThreadBroker;
	private static IBinder AppThreadBroker4Trans;
   	
	private IBinder getToken() {
		//Context context = (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, TargetActivity.this);
    	IBinder token = (IBinder) Reflect.getField("android.app.ContextImpl", "mActivityToken", context);    				
		System.out.println("token:"+token);
		return token;
	}
	private void bindApplication(IBinder applicationThread,String TARGET) {
		 
		System.out.println("bind Application ");
		
		try {
			Parcel data =Parcel.obtain();
			data.writeInterfaceToken(IApplicationThread.descriptor);
			data.writeString(TARGET);
			ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(TARGET, 0);
			appInfo.writeToParcel(data,0);
			List<ProviderInfo> providers = null;
			data.writeTypedList(providers);//providers  
			data.writeInt(0);//testname ==NULL
			String profileName =null;
			data.writeString(profileName);
			data.writeInt(0);//profileFd
			data.writeInt(0);//autoStopProfiler
			Bundle testArgs = new Bundle();
			data.writeBundle(testArgs);//testArgs
			data.writeStrongInterface(null);//testWatcher
			data.writeInt(0);//debugMode;
			data.writeInt(0);//openGLtrace
			data.writeInt(0);//restricted backup mode
			data.writeInt(0);//persistent   		    
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
			applicationThread.transact(13, data, null, IBinder.FLAG_ONEWAY);
			data.recycle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void scheduleLaunchActivity(IBinder applicationThread,String TARGET,String Activity) {
		IBinder token = (IBinder) BrokerBinderProxy.mBrokerBinderProxy;
		
		Parcel data = Parcel.obtain();
		data.writeInterfaceToken(IApplicationThread.descriptor); //descriptor
		Intent intent = new Intent();//intent
		intent.setComponent(new ComponentName(TARGET, TARGET+Activity));
		intent.writeToParcel(data, 0);
		data.writeStrongBinder(getToken()); //token
		System.out.println("token:"+token);
		data.writeInt(0); //ident    		
		try {
			ComponentName component =new ComponentName(TARGET, TARGET+Activity);
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
		
		Log.d("schedule","launch");
		
		try {
			applicationThread.transact(7, data, null, IBinder.FLAG_ONEWAY);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		data.recycle();
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
 
    	private Object getActivityThread() {
    		Object ActivityThread = Reflect.invokeMethod("android.app.ActivityThread", "currentActivityThread", null, null);
    		System.out.println("ActivityThread:"+ActivityThread);
    		Object ActivityClientRecord = Reflect.getField("android.app.ActivityThread", "mNewActivities", ActivityThread);
    		System.out.println("ActivityClientRecord:"+ActivityClientRecord);
    		ParcelFileDescriptor profileFd = (ParcelFileDescriptor) Reflect.getField("android.app.ActivityThread$ActivityClientRecord", "profileFd", ActivityClientRecord);
    		System.out.println("profileFd:"+profileFd);
    		return ActivityThread;
    	}
       	   
    	
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(TargetActivity.this, "Service connected", Toast.LENGTH_SHORT).show();          
            mService = IIsolatedProcessService.Stub.asInterface(service);            
            AppThreadIso = getApplicationThread();
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
            try {
          	  Log.d("NEWS FORM TARGET", "TargetActivity want to help trans its AppThread to FakedAMS!");
  			  mService.setBrokerCaller(AppThreadBroker4Trans);
  		    } catch (RemoteException e2) {
  			// TODO Auto-generated catch block
  			  e2.printStackTrace();
  	    	}
              
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
              
            Log.d("order", "onConnected:before bindApplication ");
            bindApplication(AppThreadIso,TARGET);
         //   getActivityThread();
            Log.d("order", "onConnected:before launchApplication ");
            scheduleLaunchActivity(AppThreadIso,TARGET,".MainActivity"); 
           
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
    public void startActivityIso(){
    	
    }
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
   
	Object activityThread = Reflect.getField("android.app.ContextImpl", "mMainThread", context);
	Object applicationThread = Reflect.invokeMethod("android.app.ActivityThread", "getApplicationThread", activityThread, null); 
    Log.d("CALLER", "called getApplicationThread() xx" +applicationThread);
    AppThreadBroker4Trans=(IBinder) applicationThread;
    AppThreadBroker = (IApplicationThread)applicationThread;
	}
	public IBrokerProcess mBrokerProcess = new IBrokerProcess.Stub() {

			@Override
			public IBinder getService(String name) throws RemoteException {
				// TODO Auto-generated method stub
				return tryServiceManager(name);
			}
			
			@Override
			public ContentProviderHolder getHolder(IBinder AppThread,String name, boolean stable) throws RemoteException {
				// TODO Auto-generated method stub
				ContentProviderHolder BrokerHolder = null;
				IActivityManager BrokerProxy=(IActivityManager)Reflect.invokeMethod("android.app.ActivityManagerNative","getDefault",context);
				Class<?> clazz;
				try {
					clazz = Class.forName("android.app.ActivityManagerProxy");
					System.out.println("--Class: " + clazz);
					Method[] methods = clazz.getDeclaredMethods();
					try {
						BrokerHolder=ContentProviderHolder.asOurContentProvider((android.app.IActivityManager.ContentProviderHolder) methods[32].invoke(BrokerProxy, AppThreadBroker,name,stable));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("BYE", "Broker get holder--"+BrokerHolder);
				return BrokerHolder;
			}


//			@Override
//			public IBinder BrokerAppThread() throws RemoteException {
//				// TODO Auto-generated method stub
//				return (IBinder)AppThreadBroker;
//			}
			
	  	  

			@Override
			public int startActivity(IBinder caller,IBinder resultTo, String component)
					throws RemoteException {
				// TODO Auto-generated method stub
			    Log.d("startActivity", "in Target!");
//			    
//		        Parcel data = Parcel.obtain();
//		        Parcel reply = Parcel.obtain();
//		        data.writeInterfaceToken(IActivityManager.descriptor);
//				//IActivityManager BrokerProxy=(IActivityManager)Reflect.invokeMethod("android.app.ActivityManagerNative","getDefault",context);				
//		        data.writeStrongBinder(caller!= null ? caller : null);
//		        Intent intent2 = new Intent();//"target.iser.ruc.edu.cn/target.iser.ruc.edu.cn.SecondActivity");
//		       // intent2.setAction(action);
//		        Log.d("fullname--",component);
		        StringTokenizer st = new StringTokenizer(component,"{=/} ");
		        String temp=st.nextToken()+st.nextToken();
		        String packageName = st.nextToken();
		        Log.d("packageName--",packageName);
		        String ActivityName = st.nextToken();
		        Log.d("ActivityName--",packageName+ActivityName);
//		       // intent2.setComponent(new ComponentName(packageName, packageName+ActivityName));
//		        intent2.setComponent(new ComponentName("target.iser.ruc.edu.cn","target.iser.ruc.edu.cn.SecondActivity"));
//		        intent2.writeToParcel(data, 0);
//		        data.writeString(null);
//		        data.writeStrongBinder(resultTo);
//		        data.writeString(null);
//		        data.writeInt(1);
//		        data.writeInt(0);
//		        data.writeString(null);
//		        data.writeInt(0);
//		        data.writeInt(0);
//
//		        IBinder ib = getmRemote();
//		        System.out.println("mRemote:"+ib);
//		        try {
//					ib.transact(3, data, reply, 0);
//				} catch (RemoteException e) {
//					e.printStackTrace();
//				}
////		        mRemote.transact(START_ACTIVITY_TRANSACTION, data, reply, 0);
//		        reply.readException();
//		        int result = reply.readInt();
//		        reply.recycle();
//		        data.recycle();
		        IBinder ib = getmRemote();
			    bindApplication(ib,packageName);
			    scheduleLaunchActivity(ib,packageName,ActivityName);
			    int result=0;
		        Log.d("ERR"," result--"+result);
		        return result;
			}
	};
	
	private void getServiceManager(){
		sServiceManager = (IInterface) Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null); 
		System.out.println("sServiceManager of broker: "+sServiceManager);
	}
	private IBinder tryServiceManager(String name) {
		// TODO Auto-generated method stub
		System.out.println("I:change ServiceManager to real");
		//Reflect.setField("android.os.ServiceManager", "broker", null, null);
		temp = (IBinder) Reflect.invokeMethod("android.os.ServiceManager", "getService", null, name);
		Log.d("SM","YOU GOT THE SERVICE"+temp);		
		return temp;
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("Broker PID: " + Process.myPid());		
		setContentView(R.layout.targer_activity);
		context= (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, (Context)this);
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
