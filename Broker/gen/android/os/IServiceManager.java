/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Kainny/学习/安卓/shixiong_modified/Broker/src/android/os/IServiceManager.aidl
 */
package android.os;
public interface IServiceManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.os.IServiceManager
{
private static final java.lang.String DESCRIPTOR = "android.os.IServiceManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.os.IServiceManager interface,
 * generating a proxy if needed.
 */
public static android.os.IServiceManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.os.IServiceManager))) {
return ((android.os.IServiceManager)iin);
}
return new android.os.IServiceManager.Stub.Proxy(obj);
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
case TRANSACTION_getService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.getService(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_checkService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.checkService(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_addService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
boolean _arg2;
_arg2 = (0!=data.readInt());
this.addService(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_listServices:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.listServices();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_setSM:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.setSM(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getSM:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _result = this.getSM();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.os.IServiceManager
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
@Override public android.os.IBinder getService(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_getService, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.IBinder checkService(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_checkService, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void addService(java.lang.String name, android.os.IBinder service, boolean allowIsolated) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeStrongBinder(service);
_data.writeInt(((allowIsolated)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_addService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String[] listServices() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_listServices, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setSM(android.os.IBinder SM) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(SM);
mRemote.transact(Stub.TRANSACTION_setSM, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.os.IBinder getSM() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSM, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_checkService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_addService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_listServices = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setSM = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getSM = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public android.os.IBinder getService(java.lang.String name) throws android.os.RemoteException;
public android.os.IBinder checkService(java.lang.String name) throws android.os.RemoteException;
public void addService(java.lang.String name, android.os.IBinder service, boolean allowIsolated) throws android.os.RemoteException;
public java.lang.String[] listServices() throws android.os.RemoteException;
public void setSM(android.os.IBinder SM) throws android.os.RemoteException;
public android.os.IBinder getSM() throws android.os.RemoteException;
}
