package broker.iser.ruc.edu.cn;
import  broker.iser.ruc.edu.cn.IBrokerProcess;
import android.os.IServiceManager;
import android.app.ContentProviderHolder;

interface IIsolatedProcessService {
        IBinder getApplicationThread();
        void registerCallBack(IBrokerProcess mb);
        void unregisterCallBack(IBrokerProcess mb);
        IBinder getServiceFromBroker(String name);
        void setRealServiceManager(IBinder SM);
        //void tryClearViolations();
        void trySetPackageManager();
        void trySetServiceCache();
        void tryChangeAm();
        void transAMBinder(IBinder b);
        ContentProviderHolder getHolderFromBroker(IBinder AppThread,String name, boolean stable);
<<<<<<< HEAD
        void setBrokerCaller(IBinder caller);
=======
		int startActivityFromBroker(IBinder resultTo, String action);
>>>>>>> origin/master
}


