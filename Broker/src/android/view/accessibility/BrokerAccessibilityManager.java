package android.view.accessibility;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BrokerAccessibilityManager {
	
	static final Object sInstanceSync = new Object();
    public static BrokerAccessibilityManager sInstance = new BrokerAccessibilityManager();
    
	
    public static BrokerAccessibilityManager getInstance(Context context) {
//        synchronized (sInstanceSync) {
//            if (sInstance == null) {
//                IBinder iBinder = ServiceManager.getService(Context.ACCESSIBILITY_SERVICE);
//                IAccessibilityManager service = IAccessibilityManager.Stub.asInterface(iBinder);
//                sInstance = new BrokerAccessibilityManager(context, service);
//            }
//        }
    	Log.d("BrokerAccessibility", "getInstance");
        return sInstance;
    }
    
	public static IAccessibilityManager mAccessibilityManager = new IAccessibilityManager() {
		
		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
	    	Log.d("BrokerAccessibility", "asBinder");
			return null;
		}
		
//		@Override
//		public boolean sendAccessibilityEvent(BrokerAccessibilityEvent uiEvent)
//				throws RemoteException {
//			// TODO Auto-generated method stub
//	    	Log.d("BrokerAccessibility", "sendAccessibilityEvent");
//			return false;
//		}
		
		@Override
		public void interrupt() throws RemoteException {
			// TODO Auto-generated method stub
	    	Log.d("BrokerAccessibility", "interrupt");			
		}
		
		@Override
		public int addClient(IAccessibilityManagerClient client)
				throws RemoteException {
			// TODO Auto-generated method stub
	    	Log.d("BrokerAccessibility", "addClient");
			return 0;
		}
	};
}
