/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Kainny/学习/安卓/shixiong_modified/Broker/src/android/view/accessibility/IAccessibilityManagerClient.aidl
 */
package android.view.accessibility;
public interface IAccessibilityManagerClient extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.view.accessibility.IAccessibilityManagerClient
{
private static final java.lang.String DESCRIPTOR = "android.view.accessibility.IAccessibilityManagerClient";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.view.accessibility.IAccessibilityManagerClient interface,
 * generating a proxy if needed.
 */
public static android.view.accessibility.IAccessibilityManagerClient asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.view.accessibility.IAccessibilityManagerClient))) {
return ((android.view.accessibility.IAccessibilityManagerClient)iin);
}
return new android.view.accessibility.IAccessibilityManagerClient.Stub.Proxy(obj);
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
case TRANSACTION_serState:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.serState(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.view.accessibility.IAccessibilityManagerClient
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
@Override public void serState(int stateFlags) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(stateFlags);
mRemote.transact(Stub.TRANSACTION_serState, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_serState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void serState(int stateFlags) throws android.os.RemoteException;
}
