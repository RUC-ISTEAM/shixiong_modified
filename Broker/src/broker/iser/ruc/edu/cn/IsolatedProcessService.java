package broker.iser.ruc.edu.cn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import broker.iser.ruc.edu.cn.IBrokerProcess;
import android.app.BrokerActivityManagerProxy;
import android.app.ContentProviderHolder;
import android.app.IActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.BrokerSystemManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IServiceManager;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.util.Singleton;
import  android.os.Process;



public class IsolatedProcessService extends Service {
	private static Context context;
	public static IBinder BrokerAppThread=null;
	private static int flag=0;
	public static final String TAG = "Broker";
	public static final String AMN="android.app.ActivityManagerNative";
	private static IBinder service;
	private static IBinder AMBinder=null;
	private static RemoteCallbackList<IBrokerProcess> mCallBacks = new RemoteCallbackList<IBrokerProcess>();
	public static IBinder getService(String name){
		try {
			//tryClearViolations2(); 
			//trySetPackageManager();
			return mBinder.getServiceFromBroker(name);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    public static ContentProviderHolder getHolder(IBinder AppThread,String name, boolean stable){
    	try {
			Log.d("BYE", "NOW IN ISOLATED STATIC!");
    		return mBinder.getHolderFromBroker(AppThread, name, stable);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public static int startActivity(IBinder caller, IBinder resultTo, String action) {
		try {
			Log.d("BYE", "startActivity in iso");
			return mBinder.startActivityFromBroker(caller,resultTo, action);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
    
	private final static  IIsolatedProcessService.Stub mBinder = new IIsolatedProcessService.Stub() {
		
		@Override
        public IBinder getApplicationThread() throws RemoteException {
        	Object activityThread = Reflect.getField("android.app.ContextImpl", "mMainThread", context);
        	Object applicationThread = Reflect.invokeMethod("android.app.ActivityThread", "getApplicationThread", activityThread, null); 
            Log.d(TAG, "called getApplicationThread() xx" +applicationThread);
            return (IBinder) applicationThread;
        }
		
		@Override
		public IBinder getServiceFromBroker(String name){
        final int len = mCallBacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                 // 通知回调
                      service = mCallBacks.getBroadcastItem(i).getService(name);
                } catch (RemoteException e) {
                         e.printStackTrace();
                }
       }
       mCallBacks.finishBroadcast();
//       new Thread(new Runnable(){
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			while(true){
//		       tryClearViolations2();
//		       sleep(1000);
//			}
//		}  
//       }).start();
       return service;
	}
		@Override
		public void setRealServiceManager(IBinder SM) throws RemoteException {
			// TODO Auto-generated method stub
		    BrokerSystemManager.sServiceManager= SM;
		    Log.d("SM", "ISolatedProcess get the real SM, set it to BrokerSystemManager "+SM);
			
		}

//		@Override
//		public void setRealServiceManager(byte[] SM) throws RemoteException {
//			// TODO Auto-generated method stub
//			ByteArrayInputStream bais = new ByteArrayInputStream(SM);
//		    ObjectInputStream ois = null;
//			try {
//				ois = new ObjectInputStream( bais );
//			} catch (StreamCorruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    Object o = null;
//			try {
//				o = ois.readObject();
//			} catch (OptionalDataException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			BrokerSystemManager.sbServiceManager=(IServiceManager) o;
//		    Log.d("SM", "ISolatedProcess set the SM of BrokerSystemManager "+SM);
//			
//		}
		@Override
		public void registerCallBack(IBrokerProcess mb) throws RemoteException {
			// TODO Auto-generated method stub
			mCallBacks.register(mb);
			
//			IBinder brokerSystemService = new Binder();
			Log.d("test", mb.toString());
			//mb.sayHello();
			//IBinder brokerSystemService = mCallBacks.getBroadcastItem(0).asBinder();
			//Reflect.setField("android.os.ServiceManager", "broker", null, brokerSystemService);
		}
        @Override
		public void unregisterCallBack(IBrokerProcess mb) throws RemoteException {
			// TODO Auto-generated method stub
			mCallBacks.unregister(mb);
		}
        @Override
        public void trySetPackageManager(){
   		 Object backup=null;  	
   		 Context backup2=null;
   		 backup=Reflect.getField("android.app.ContextImpl", "mPackageManager",context);
   		 Log.d("NEW", "get mPackageManager"+backup);
   		 Reflect.setField("android.app.ContextImpl", "mPackageManager",context,null);
   		 Log.d("NEW","set  mPackageManager to null");
   		 
   		 }
		@SuppressWarnings("unchecked")
		@Override
		public void trySetServiceCache() throws RemoteException {
			// TODO Auto-generated method stub
			HashMap<String, IBinder> map = new HashMap<String, IBinder>();
			HashMap<String, IBinder> map2 = new HashMap<String, IBinder>();
			map = (HashMap<String, IBinder>)Reflect.getField("android.os.ServiceManager", "sCache", null);
			if(map!=null) Log.d("NEW SM:","HASH MAP IN SCACHE IS"+map.toString());
			Reflect.setField("android.os.ServiceManager", "sCache", null,map2);
			Log.d("NEW SM:","HASH MAP IN SCACHE SET NULL");		
			
		}
		
		@Override
		 public void tryChangeAm() {
				// TODO Auto-generated method stub			
		    
		   // IActivityManager backUpAmSingle=(IActivityManager)Reflect.getField(AMN,"gDefault",context);
		   // if(backUpAmSingle != null) Log.d("AM", "great:get the gDefault of isolatedProcess--"+backUpAmSingle.toString());	    
			if(flag==0 ){  
			final IActivityManager backUpAm=(IActivityManager)Reflect.invokeMethod(AMN,"getDefault",context);
		    if(backUpAm != null)Log.d("AM", "great:geTDefault of isolatedProcess--"+backUpAm.toString());	        	  
			Singleton<Object> gDefault =new Singleton<Object>() {
	         protected Object create() {
//	        	 IActivityManager am=(IActivityManager)Reflect.invokeMethod(AMN, "ActivityManagerNative.asInterface",context, AMBinder);
//	 		     if(am != null) Log.d("AM", "cool:set IBinder to IActivityManager--"+am.toString());	 
	        	 BrokerActivityManagerProxy AMP = new BrokerActivityManagerProxy(AMBinder);	        	 
	        	 return AMP;
	         }
	        };
		    Reflect.setField(AMN, "gDefault", context, gDefault);	    
	        if(gDefault != null) Log.d("AM", "fantastic:set to ours IActivityManager--"+gDefault.toString());
	        IActivityManager bm=(IActivityManager)Reflect.invokeMethod(AMN,"getDefault",context);
	        if(bm != null) Log.d("AM", "wonderful:check if geTDefault been changed --"+bm.toString());
			flag++;
			}}
		@Override
		public void transAMBinder(IBinder b) throws RemoteException {
			// TODO Auto-generated method stub
			AMBinder=b;
			Log.d("AM", "welcome: IBinder of activity--"+AMBinder.toString());			
			
		}

		@Override
		public ContentProviderHolder getHolderFromBroker(IBinder AppThread, String name, boolean stable)
				throws RemoteException {
			// TODO Auto-generated method stub
	        final int len = mCallBacks.beginBroadcast();
	        ContentProviderHolder h=null;
	        for (int i = 0; i < len; i++) {
	            try {
	                 // 通知回调
	                      h= mCallBacks.getBroadcastItem(i).getHolder(AppThread, name, stable);
	                } catch (RemoteException e) {
	                         e.printStackTrace();
	                }
	       }
	       mCallBacks.finishBroadcast();
		   Log.d("BYE", "in isolatedprocess stub");
	       return h;
		}

		@Override
		public void setBrokerCaller(IBinder caller) throws RemoteException {
			// TODO Auto-generated method stub
			BrokerAppThread=caller;
		}



		public int startActivityFromBroker(IBinder caller,IBinder resultTo, String action)
				throws RemoteException {

	        final int len = mCallBacks.beginBroadcast();
	        int result=0;
	        for (int i = 0; i < len; i++) {
	            try {
	                      result= mCallBacks.getBroadcastItem(i).startActivity(caller,resultTo, action);
	                } catch (RemoteException e) {
	                         e.printStackTrace();
	                }
	       }
	       mCallBacks.finishBroadcast();
		   Log.d("BYE", "startActivityFromBroker");
	       return result;
		}

	};	
//	@SuppressWarnings("unchecked")
//	public static void trySetServiceCache() throws RemoteException {
//		// TODO Auto-generated method stub
//		HashMap<String,IBinder>INIT=null;
//		INIT = (HashMap<String, IBinder>)Reflect.getField("android.os.ServiceManager", "sCache", null);
//		if(INIT!=null) Log.d("NEW SM:","HASH MAP IN SCACHE IS"+INIT.toString());
//		Reflect.setField("android.os.ServiceManager", "sCache", null, null);
//		Log.d("NEW SM:","HASH MAP IN SCACHE SET NULL");		
//	}
//	 public static void tryClearViolations2(){
//     	Reflect.invokeMethod("android.os.StrictMode", "clearGatheredViolations", null,null);
//     	Log.d("成功了就去看美队2", "clear了吗2");
//     	boolean clr = false;
//     	Reflect.invokeMethod("android.os.StrictMode", "hasGatheredViolations", clr,null);
//     	if(!clr) Log.d("成功了就去看美队3", "clear!!");
//     	
//     	
//	 }
//	 public static void trySetPackageManager(){
//		 Object backup=null;  	
//   		 //Context backup2=null;
//   		 backup=Reflect.getField("android.app.ContextImpl", "mPackageManager", context);
//   		 Log.d("NEW", "get mPackageManager"+backup);
//   		 Reflect.setField("android.app.ContextImpl", "mPackageManager",context,null);
//   		 Log.d("NEW","set  mPackageManager to null");
//		 
//		 }
	 //try to obtain PermissionIinfo
//	 public void tryPackageManager(){
//		 PermissionInfo pi = null;
//		 try {
//			pi=this.getPackageManager().getPermissionInfo(DOWNLOAD_SERVICE, START_FLAG_REDELIVERY);
//			
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Permission Info of download service is "+pi.toString()+" level:"+pi.protectionLevel);
//		 
//	 }
	
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("Target PID: " + Process.myPid());
		Log.d("order", "onbind:before setBroker ");
		context = (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, (Context)this);
		// TODO Auto-generated method stub

		Object backup =  Reflect.getField("android.os.ServiceManager", "broker", null);
		Reflect.setField("android.os.ServiceManager", "broker", null, BrokerSystemManager.mBrokerSystemManager);
		
		System.out.println("II: " + Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null));
		
		System.out.println("III: " + BrokerSystemManager.mBrokerSystemManager);
		System.out.println("Broker: " + Reflect.getField("android.os.ServiceManager", "broker", null));

		Log.d("order", "onbind:after setBroker ");
		//trySetPackageManager();
		//Log.d("order", "onbind and set PackageManager");
		 
		
		return mBinder;
	}
	
	
}
