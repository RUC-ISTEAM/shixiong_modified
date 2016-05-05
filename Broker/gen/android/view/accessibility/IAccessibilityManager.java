/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\box\\Broker\\src\\android\\view\\accessibility\\IAccessibilityManager.aidl
 */
package android.view.accessibility;
public interface IAccessibilityManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.view.accessibility.IAccessibilityManager
{
private static final java.lang.String DESCRIPTOR = "android.view.accessibility.IAccessibilityManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.view.accessibility.IAccessibilityManager interface,
 * generating a proxy if needed.
 */
public static android.view.accessibility.IAccessibilityManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.view.accessibility.IAccessibilityManager))) {
return ((android.view.accessibility.IAccessibilityManager)iin);
}
return new android.view.accessibility.IAccessibilityManager.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_addClient:
{
data.enforceInterface(DESCRIPTOR);
android.view.accessibility.IAccessibilityManagerClient _arg0;
_arg0 = android.view.accessibility.IAccessibilityManagerClient.Stub.asInterface(data.readStrongBinder());
int _result = this.addClient(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_interrupt:
{
data.enforceInterface(DESCRIPTOR);
this.interrupt();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.view.accessibility.IAccessibilityManager
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int addClient(android.view.accessibility.IAccessibilityManagerClient client) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((client!=null))?(client.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addClient, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//	boolean sendAccessibilityEvent(in BrokerAccessibilityEvent uiEvent);
//	List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList();
//	List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int feedbackType);

@Override public void interrupt() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_interrupt, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_addClient = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_interrupt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public int addClient(android.view.accessibility.IAccessibilityManagerClient client) throws android.os.RemoteException;
//	boolean sendAccessibilityEvent(in BrokerAccessibilityEvent uiEvent);
//	List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList();
//	List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int feedbackType);

public void interrupt() throws android.os.RemoteException;
}
