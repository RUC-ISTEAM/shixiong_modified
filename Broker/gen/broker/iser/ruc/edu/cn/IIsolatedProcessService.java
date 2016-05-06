/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Kainny/学习/安卓/shixiong_modified/Broker/src/broker/iser/ruc/edu/cn/IIsolatedProcessService.aidl
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
}
static final int TRANSACTION_getApplicationThread = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public android.os.IBinder getApplicationThread() throws android.os.RemoteException;
}
