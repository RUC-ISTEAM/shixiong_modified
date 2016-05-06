package broker.iser.ruc.edu.cn;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.BrokerSystemManager;
import android.os.IBinder;
import android.os.IServiceManager;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import  android.os.Process;



public class IsolatedProcessService extends Service {
	public static Context context;
	public static final String TAG = "Broker";
	private RemoteCallbackList<IServiceManager> mCallBacks = new RemoteCallbackList<IServiceManager>();
	
	private final IIsolatedProcessService.Stub mBinder = new IIsolatedProcessService.Stub() {
		
		@Override
        public IBinder getApplicationThread() throws RemoteException {
        	Object activityThread = Reflect.getField("android.app.ContextImpl", "mMainThread", context);
        	Object applicationThread = Reflect.invokeMethod("android.app.ActivityThread", "getApplicationThread", activityThread, null); 
            Log.d(TAG, "called getApplicationThread() xx" +applicationThread);
           return (IBinder) applicationThread;
        }

		/*@Override
		public void registerCallBack(IBroker mb) throws RemoteException {
			// TODO Auto-generated method stub
			mCallBacks.register(mb);
			
//			IBinder brokerSystemService = new Binder();
			Log.d("test", mb.toString());
			//mb.sayHello();
			IBinder brokerSystemService = mCallBacks.getBroadcastItem(0).asBinder();
			//Reflect.setField("android.os.ServiceManager", "broker", null, brokerSystemService);
		}

		@Override
		public void unregisterCallBack(IBroker mb) throws RemoteException {
			// TODO Auto-generated method stub
			mCallBacks.unregister(mb);
		}*/
		
		
 };

	
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("Target PID: " + Process.myPid());
		
		context = (Context) Reflect.invokeMethod("android.app.ContextImpl", "getImpl", null, (Context)this);
		// TODO Auto-generated method stub
		
//		Log.d("test", mb.toString());
		//mb.sayHello();
		//IBinder brokerSystemService = mCallBacks.getBroadcastItem(0).asBinder();
        //set Broker in 
		Object backup =  Reflect.getField("android.os.ServiceManager", "broker", null);
		Reflect.setField("android.os.ServiceManager", "broker", null, BrokerSystemManager.mBrokerSystemManager);
		
		System.out.println("II: " + Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null));
		
		System.out.println("III: " + BrokerSystemManager.mBrokerSystemManager);
		//Reflect.setField("android.os.ServiceManager", "broker", null, sServiceManager);
		
		System.out.println("Broker: " + Reflect.getField("android.os.ServiceManager", "broker", null));
		
		
		return mBinder;
	}

}
