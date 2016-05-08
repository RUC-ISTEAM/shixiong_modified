package broker.iser.ruc.edu.cn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;

import broker.iser.ruc.edu.cn.IBrokerProcess;

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
	private static IBinder service;
	private static RemoteCallbackList<IBrokerProcess> mCallBacks = new RemoteCallbackList<IBrokerProcess>();
	public static IBinder getService(String name){
		try {
			return mBinder.getServiceFromBroker(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
       return service;
	}
		@Override
		public void setRealServiceManager(IBinder SM) throws RemoteException {
			// TODO Auto-generated method stub
		    BrokerSystemManager.sServiceManager= SM;
		    Log.d("SM", "ISolatedProcess get the read SM set to BrokerSystemManager "+SM);
			
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
