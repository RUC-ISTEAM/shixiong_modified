package android.os;

interface IBinderProxy {
	void dump(String fd, in String[] args);
	void dumpAsync(String fd, in String[] args);
	void sendDeathNotice(IBinder ricipient);
} 