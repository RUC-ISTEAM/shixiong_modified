package android.os;

import android.util.Log;

public class BrokerBinderProxy {

	public static IBinderProxy mBrokerBinderProxy = new IBinderProxy.Stub() {

		@Override
		public void dump(String fd, String[] args) throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("BinderProxy", "dump");
		}

		@Override
		public void dumpAsync(String fd, String[] args) throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("BinderProxy", "dumpAsync");			
		}


		@Override
		public void sendDeathNotice(IBinder ricipient) throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("BinderProxy", "sendDeathMotice");			
		}
		
	};
}
