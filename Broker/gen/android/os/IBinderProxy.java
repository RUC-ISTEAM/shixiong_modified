/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\secure_competition\\box1\\Broker\\src\\android\\os\\IBinderProxy.aidl
 */
package android.os;
public interface IBinderProxy extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.os.IBinderProxy
{
private static final java.lang.String DESCRIPTOR = "android.os.IBinderProxy";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.os.IBinderProxy interface,
 * generating a proxy if needed.
 */
public static android.os.IBinderProxy asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.os.IBinderProxy))) {
return ((android.os.IBinderProxy)iin);
}
return new android.os.IBinderProxy.Stub.Proxy(obj);
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
case TRANSACTION_dump:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
this.dump(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_dumpAsync:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
_arg1 = data.createStringArray();
this.dumpAsync(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_sendDeathNotice:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.sendDeathNotice(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.os.IBinderProxy
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
@Override public void dump(java.lang.String fd, java.lang.String[] args) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(fd);
_data.writeStringArray(args);
mRemote.transact(Stub.TRANSACTION_dump, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void dumpAsync(java.lang.String fd, java.lang.String[] args) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(fd);
_data.writeStringArray(args);
mRemote.transact(Stub.TRANSACTION_dumpAsync, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void sendDeathNotice(android.os.IBinder ricipient) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(ricipient);
mRemote.transact(Stub.TRANSACTION_sendDeathNotice, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_dump = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_dumpAsync = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_sendDeathNotice = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void dump(java.lang.String fd, java.lang.String[] args) throws android.os.RemoteException;
public void dumpAsync(java.lang.String fd, java.lang.String[] args) throws android.os.RemoteException;
public void sendDeathNotice(android.os.IBinder ricipient) throws android.os.RemoteException;
}
