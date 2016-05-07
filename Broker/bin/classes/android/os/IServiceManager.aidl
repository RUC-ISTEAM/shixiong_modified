package android.os;

interface IServiceManager {
	IBinder getService(String name);
	IBinder checkService(String name);
	void addService(String name, IBinder service, boolean allowIsolated);
	String[] listServices();
	//String setSM(IBinder sServiceManager);

}