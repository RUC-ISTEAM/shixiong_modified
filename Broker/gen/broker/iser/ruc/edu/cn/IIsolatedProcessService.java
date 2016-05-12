/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\secure_competition\\box1\\Broker\\src\\broker\\iser\\ruc\\edu\\cn\\IIsolatedProcessService.aidl
 */
package broker.iser.ruc.edu.cn;
public interface IIsolatedProcessService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements broker.iser.ruc.edu.cn.IIsolatedProcessService
{
private static final java.lang.String DESCRIPTOR = "broker.iser.ruc.edu.cn.IIsolatedProcessService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an broker.iser.ruc.edu.cn.IIsolatedProcessService interface,
 * generating a proxy if needed.
 */
public static broker.iser.ruc.edu.cn.IIsolatedProcessService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof broker.iser.ruc.edu.cn.IIsolatedProcessService))) {
return ((broker.iser.ruc.edu.cn.IIsolatedProcessService)iin);
}
return new broker.iser.ruc.edu.cn.IIsolatedProcessService.Stub.Proxy(obj);
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
case TRANSACTION_getApplicationThread:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _result = this.getApplicationThread();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_registerCallBack:
{
data.enforceInterface(DESCRIPTOR);
broker.iser.ruc.edu.cn.IBrokerProcess _arg0;
_arg0 = broker.iser.ruc.edu.cn.IBrokerProcess.Stub.asInterface(data.readStrongBinder());
this.registerCallBack(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallBack:
{
data.enforceInterface(DESCRIPTOR);
broker.iser.ruc.edu.cn.IBrokerProcess _arg0;
_arg0 = broker.iser.ruc.edu.cn.IBrokerProcess.Stub.asInterface(data.readStrongBinder());
this.unregisterCallBack(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getServiceFromBroker:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.getServiceFromBroker(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_setRealServiceManager:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.setRealServiceManager(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_trySetPackageManager:
{
data.enforceInterface(DESCRIPTOR);
this.trySetPackageManager();
reply.writeNoException();
return true;
}
case TRANSACTION_trySetServiceCache:
{
data.enforceInterface(DESCRIPTOR);
this.trySetServiceCache();
reply.writeNoException();
return true;
}
case TRANSACTION_tryChangeAm:
{
data.enforceInterface(DESCRIPTOR);
this.tryChangeAm();
reply.writeNoException();
return true;
}
case TRANSACTION_transAMBinder:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.transAMBinder(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getHolderFromBroker:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
android.app.ContentProviderHolder _result = this.getHolderFromBroker(_arg0, _arg1, _arg2);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
<<<<<<< HEAD
case TRANSACTION_setBrokerCaller:
=======
case TRANSACTION_startActivityFromBroker:
>>>>>>> origin/master
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
<<<<<<< HEAD
this.setBrokerCaller(_arg0);
reply.writeNoException();
=======
java.lang.String _arg1;
_arg1 = data.readString();
int _result = this.startActivityFromBroker(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
>>>>>>> origin/master
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements broker.iser.ruc.edu.cn.IIsolatedProcessService
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
@Override public android.os.IBinder getApplicationThread() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getApplicationThread, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void registerCallBack(broker.iser.ruc.edu.cn.IBrokerProcess mb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((mb!=null))?(mb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallBack(broker.iser.ruc.edu.cn.IBrokerProcess mb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((mb!=null))?(mb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.os.IBinder getServiceFromBroker(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_getServiceFromBroker, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setRealServiceManager(android.os.IBinder SM) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(SM);
mRemote.transact(Stub.TRANSACTION_setRealServiceManager, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//void tryClearViolations();

@Override public void trySetPackageManager() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_trySetPackageManager, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void trySetServiceCache() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_trySetServiceCache, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void tryChangeAm() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_tryChangeAm, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void transAMBinder(android.os.IBinder b) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(b);
mRemote.transact(Stub.TRANSACTION_transAMBinder, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.app.ContentProviderHolder getHolderFromBroker(android.os.IBinder AppThread, java.lang.String name, boolean stable) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.app.ContentProviderHolder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(AppThread);
_data.writeString(name);
_data.writeInt(((stable)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_getHolderFromBroker, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.app.ContentProviderHolder.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
<<<<<<< HEAD
@Override public void setBrokerCaller(android.os.IBinder caller) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(caller);
mRemote.transact(Stub.TRANSACTION_setBrokerCaller, _data, _reply, 0);
_reply.readException();
=======
@Override public int startActivityFromBroker(android.os.IBinder resultTo, java.lang.String action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(resultTo);
_data.writeString(action);
mRemote.transact(Stub.TRANSACTION_startActivityFromBroker, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
>>>>>>> origin/master
}
finally {
_reply.recycle();
_data.recycle();
}
<<<<<<< HEAD
=======
return _result;
>>>>>>> origin/master
}
}
static final int TRANSACTION_getApplicationThread = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getServiceFromBroker = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setRealServiceManager = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_trySetPackageManager = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_trySetServiceCache = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_tryChangeAm = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_transAMBinder = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getHolderFromBroker = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
<<<<<<< HEAD
static final int TRANSACTION_setBrokerCaller = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
=======
static final int TRANSACTION_startActivityFromBroker = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
>>>>>>> origin/master
}
public android.os.IBinder getApplicationThread() throws android.os.RemoteException;
public void registerCallBack(broker.iser.ruc.edu.cn.IBrokerProcess mb) throws android.os.RemoteException;
public void unregisterCallBack(broker.iser.ruc.edu.cn.IBrokerProcess mb) throws android.os.RemoteException;
public android.os.IBinder getServiceFromBroker(java.lang.String name) throws android.os.RemoteException;
public void setRealServiceManager(android.os.IBinder SM) throws android.os.RemoteException;
//void tryClearViolations();

public void trySetPackageManager() throws android.os.RemoteException;
public void trySetServiceCache() throws android.os.RemoteException;
public void tryChangeAm() throws android.os.RemoteException;
public void transAMBinder(android.os.IBinder b) throws android.os.RemoteException;
public android.app.ContentProviderHolder getHolderFromBroker(android.os.IBinder AppThread, java.lang.String name, boolean stable) throws android.os.RemoteException;
<<<<<<< HEAD
public void setBrokerCaller(android.os.IBinder caller) throws android.os.RemoteException;
=======
public int startActivityFromBroker(android.os.IBinder resultTo, java.lang.String action) throws android.os.RemoteException;
>>>>>>> origin/master
}
