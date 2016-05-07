package android.os;

import java.io.FileDescriptor;

import android.os.IBinder;
import android.os.IServiceManager;
import android.os.RemoteException;
import android.util.Log;
import broker.iser.ruc.edu.cn.*;
public class BrokerSystemManager {
	public static IServiceManager sbServiceManager;

    
	public static IServiceManager mBrokerSystemManager = new IServiceManager.Stub() {
		int GET_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
		int CHECK_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+1;
		int ADD_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+2;
		int LIST_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+3;
		int CHECK_SERVICES_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+4;
		IBinder sService=null;

		@Override
		public IBinder getService(String name) throws RemoteException {
			Log.d("IBrokerSystemManager", "getService " + name);		
			//System.out.println("now the sServiceManager is "+BrokerSystemManager.sbServiceManager);
			//System.out.println("now the sServiceManager of Main  is "+MainActivity.sServiceManager);
			//Reflect.setField("android.os.ServiceManager", "real", null, BrokerSystemManager.sbServiceManager);//set to real
			//System.out.println("I:change ServiceManager to real");
			//Reflect.setField("android.os.ServiceManager", "broker", null,null);
			//System.out.println("realbroker: " + Reflect.getField("android.os.ServiceManager", "broker", null));
			//System.out.println("returnOfGetIServiceManager: " + Reflect.invokeMethod("android.os.ServiceManager", "getIServiceManager", null, null));
			//sService = (IBinder) Reflect.invokeMethod("android.os.ServiceManager", "getService", null, name);//return sService		
			sService = IsolatedProcessService.getService(name);
			System.out.println("II:SUCCESS to getServise "+sService);
			Reflect.setField("android.os.ServiceManager", "broker", null,BrokerSystemManager.mBrokerSystemManager);//set to wrong
			return sService;
		}

		@Override
		public IBinder checkService(String name) throws RemoteException {
			Log.d("IBrokerSystemManager", "checkService");
			return null;
		}

		@Override
		public void addService(String name, IBinder service,
				boolean allowIsolated) throws RemoteException {
			Log.d("IBrokerSystemManager", "addService");
			
		}

		@Override
		public String[] listServices() throws RemoteException {
			Log.d("IBrokerSystemManager", "listServices");
			return null;
		}
//
//		@Override
//		public String setSM(IBinder sServiceManager) throws RemoteException {
//			// TODO Auto-generated method stub
//			sbServiceManager=sServiceManager;
//			Log.d("SM", "SM IN BROKERSYSTEMMANAGER BEEN SET to :"+ sbServiceManager);
//			return sbServiceManager.toString();
//			
//		}


//		@Override
//		public void setSM(Binder sServiceManager) throws RemoteException {
//			// TODO Auto-generated method stub
//			sbServiceManager =sServiceManager; 
//	        Log.d("SM", "SM IN BROKERSYSTEMMANAGER BEEN SET to :"+ sServiceManager);
//			
//		}





		
	};

}
