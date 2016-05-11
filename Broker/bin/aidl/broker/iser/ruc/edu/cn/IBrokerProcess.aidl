package broker.iser.ruc.edu.cn;
import  android.app.ContentProviderHolder;
interface IBrokerProcess {
     IBinder getService(String name);
     ContentProviderHolder getHolder(IBinder AppThread,String name, boolean stable);
     int startActivity(IBinder resultTo, String action);
 }