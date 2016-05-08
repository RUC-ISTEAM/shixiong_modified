package broker.iser.ruc.edu.cn;
import  broker.iser.ruc.edu.cn.IBrokerProcess;
import android.os.IServiceManager;

interface IIsolatedProcessService {
        IBinder getApplicationThread();
        void registerCallBack(IBrokerProcess mb);
        void unregisterCallBack(IBrokerProcess mb);
        IBinder getServiceFromBroker(String name);
        void setRealServiceManager(IBinder SM);
}


