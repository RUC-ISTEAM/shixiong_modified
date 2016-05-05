package android.os;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BrokerSystemManager {
	
	public static IServiceManager mBrokerSystemManager = new IServiceManager.Stub() {
		int GET_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
		int CHECK_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+1;
		int ADD_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+2;
		int LIST_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+3;
		int CHECK_SERVICES_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+4;

		@Override
		public IBinder getService(String name) throws RemoteException {
			Log.d("IBrokerSystemManager", "getService" + name);
			return null;
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

		
	};

}
