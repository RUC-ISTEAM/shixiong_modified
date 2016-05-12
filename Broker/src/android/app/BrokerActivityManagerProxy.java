package android.app;
import broker.iser.ruc.edu.cn.IsolatedProcessService;
import broker.iser.ruc.edu.cn.TargetActivity;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.BrokerSystemManager;//ServiceManager
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Singleton;

import java.util.ArrayList;
import java.util.List;

public class BrokerActivityManagerProxy implements IActivityManager
	{
	    public BrokerActivityManagerProxy(IBinder remote)
	    {
	        mRemote = remote;
	        Log.d("BrokerAMP","created!");
	    }

	    public IBinder asBinder()
	    {
	        return mRemote;
	    }

	    public int startActivity(IApplicationThread caller, Intent intent,
	            String resolvedType, IBinder resultTo, String resultWho, int requestCode,
	            int startFlags, String profileFile,
	            ParcelFileDescriptor profileFd, Bundle options) throws RemoteException {

	    	Log.d("BrokerAMP","startActivity been faked!");
//	    	Parcel data = Parcel.obtain();
//	        Parcel reply = Parcel.obtain();
//	        data.writeInterfaceToken(IActivityManager.descriptor);
//	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
//	        intent.writeToParcel(data, 0);
//	        data.writeString(resolvedType);
//	        data.writeStrongBinder(resultTo);
//	        data.writeString(resultWho);
//	        data.writeInt(requestCode);
//	        data.writeInt(startFlags);
//	        data.writeString(profileFile);
//	        if (profileFd != null) {
//	            data.writeInt(1);
//	            profileFd.writeToParcel(data, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
//	        } else {
//	            data.writeInt(0);
//	        }
//	        if (options != null) {
//	            data.writeInt(1);
//	            options.writeToParcel(data, 0);
//	        } else {
//	            data.writeInt(0);
//	        }
//	        mRemote.transact(START_ACTIVITY_TRANSACTION, data, reply, 0);
//	        reply.readException();
//	        int result = reply.readInt();
//	        reply.recycle();
//	        data.recycle();
//	    	String action = intent.getAction();
	    	Log.d("action:", intent.getPackage()+" class:"+intent.getClass()+" intent:"+intent.toString());
	    	int result = IsolatedProcessService.startActivity(resultTo, intent.toString());
	        return result;
	    }
	    public WaitResult startActivityAndWait(IApplicationThread caller, Intent intent,
	            String resolvedType, IBinder resultTo, String resultWho,
	            int requestCode, int startFlags, String profileFile,
	            ParcelFileDescriptor profileFd, Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        intent.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        data.writeStrongBinder(resultTo);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        data.writeInt(startFlags);
	        data.writeString(profileFile);
	        if (profileFd != null) {
	            data.writeInt(1);
	            profileFd.writeToParcel(data, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
	        } else {
	            data.writeInt(0);
	        }
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITY_AND_WAIT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        WaitResult result = WaitResult.CREATOR.createFromParcel(reply);
	        reply.recycle();
	        data.recycle();
	        return result;
	    }
	    public int startActivityWithConfig(IApplicationThread caller, Intent intent,
	            String resolvedType, IBinder resultTo, String resultWho,
	            int requestCode, int startFlags, Configuration config,
	            Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        intent.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        data.writeStrongBinder(resultTo);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        data.writeInt(startFlags);
	        config.writeToParcel(data, 0);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result;
	    }
	    public int startActivityIntentSender(IApplicationThread caller,
	            IntentSender intent, Intent fillInIntent, String resolvedType,
	            IBinder resultTo, String resultWho, int requestCode,
	            int flagsMask, int flagsValues, Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        intent.writeToParcel(data, 0);
	        if (fillInIntent != null) {
	            data.writeInt(1);
	            fillInIntent.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        data.writeString(resolvedType);
	        data.writeStrongBinder(resultTo);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        data.writeInt(flagsMask);
	        data.writeInt(flagsValues);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITY_INTENT_SENDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result;
	    }
	    public boolean startNextMatchingActivity(IBinder callingActivity,
	            Intent intent, Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(callingActivity);
	        intent.writeToParcel(data, 0);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_NEXT_MATCHING_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result != 0;
	    }
	    public boolean finishActivity(IBinder token, int resultCode, Intent resultData)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(resultCode);
	        if (resultData != null) {
	            data.writeInt(1);
	            resultData.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(FINISH_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void finishSubActivity(IBinder token, String resultWho, int requestCode) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        mRemote.transact(FINISH_SUB_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public boolean finishActivityAffinity(IBinder token) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(FINISH_ACTIVITY_AFFINITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public boolean willActivityBeVisible(IBinder token) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(WILL_ACTIVITY_BE_VISIBLE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public Intent registerReceiver(IApplicationThread caller, String packageName,
	            IIntentReceiver receiver,
	            IntentFilter filter, String perm) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        data.writeString(packageName);
	        data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
	        filter.writeToParcel(data, 0);
	        data.writeString(perm);
	        mRemote.transact(REGISTER_RECEIVER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        Intent intent = null;
	        int haveIntent = reply.readInt();
	        if (haveIntent != 0) {
	            intent = Intent.CREATOR.createFromParcel(reply);
	        }
	        reply.recycle();
	        data.recycle();
	        return intent;
	    }
	    public void unregisterReceiver(IIntentReceiver receiver) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(receiver.asBinder());
	        mRemote.transact(UNREGISTER_RECEIVER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int broadcastIntent(IApplicationThread caller,
	            Intent intent, String resolvedType,  IIntentReceiver resultTo,
	            int resultCode, String resultData, Bundle map,
	            String requiredPermission, boolean serialized,
	            boolean sticky, int userId) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        intent.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        data.writeStrongBinder(resultTo != null ? resultTo.asBinder() : null);
	        data.writeInt(resultCode);
	        data.writeString(resultData);
	        data.writeBundle(map);
	        data.writeString(requiredPermission);
	        data.writeInt(serialized ? 1 : 0);
	        data.writeInt(sticky ? 1 : 0);
	        data.writeInt(userId);
	        mRemote.transact(BROADCAST_INTENT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    public void unbroadcastIntent(IApplicationThread caller, Intent intent, int userId)
	            throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        intent.writeToParcel(data, 0);
	        data.writeInt(userId);
	        mRemote.transact(UNBROADCAST_INTENT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void finishReceiver(IBinder who, int resultCode, String resultData, Bundle map, boolean abortBroadcast) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(who);
	        data.writeInt(resultCode);
	        data.writeString(resultData);
	        data.writeBundle(map);
	        data.writeInt(abortBroadcast ? 1 : 0);
	        mRemote.transact(FINISH_RECEIVER_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void attachApplication(IApplicationThread app) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(app.asBinder());
	        mRemote.transact(ATTACH_APPLICATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void activityIdle(IBinder token, Configuration config, boolean stopProfiling)
	            throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        if (config != null) {
	            data.writeInt(1);
	            config.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        data.writeInt(stopProfiling ? 1 : 0);
	        mRemote.transact(ACTIVITY_IDLE_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void activityPaused(IBinder token) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(ACTIVITY_PAUSED_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void activityStopped(IBinder token, Bundle state,
	            Bitmap thumbnail, CharSequence description) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeBundle(state);
	        if (thumbnail != null) {
	            data.writeInt(1);
	            thumbnail.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        TextUtils.writeToParcel(description, data, 0);
	        mRemote.transact(ACTIVITY_STOPPED_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void activitySlept(IBinder token) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(ACTIVITY_SLEPT_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void activityDestroyed(IBinder token) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(ACTIVITY_DESTROYED_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public String getCallingPackage(IBinder token) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_CALLING_PACKAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        String res = reply.readString();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public ComponentName getCallingActivity(IBinder token)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_CALLING_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ComponentName res = ComponentName.readFromParcel(reply);
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public List getTasks(int maxNum, int flags,
	            IThumbnailReceiver receiver) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(maxNum);
	        data.writeInt(flags);
	        data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
	        mRemote.transact(GET_TASKS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList list = null;
	        int N = reply.readInt();
	        if (N >= 0) {
	            list = new ArrayList();
	            while (N > 0) {
	                ActivityManager.RunningTaskInfo info =
	                        ActivityManager.RunningTaskInfo.CREATOR
	                        .createFromParcel(reply);
	                list.add(info);
	                N--;
	            }
	        }
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
	    public List<ActivityManager.RecentTaskInfo> getRecentTasks(int maxNum,
	            int flags) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(maxNum);
	        data.writeInt(flags);
	        mRemote.transact(GET_RECENT_TASKS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList<ActivityManager.RecentTaskInfo> list
	            = reply.createTypedArrayList(ActivityManager.RecentTaskInfo.CREATOR);
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
//	    public ActivityManager.TaskThumbnails getTaskThumbnails(int id) throws RemoteException {
//	        Parcel data = Parcel.obtain();
//	        Parcel reply = Parcel.obtain();
//	        data.writeInterfaceToken(IActivityManager.descriptor);
//	        data.writeInt(id);
//	        mRemote.transact(GET_TASK_THUMBNAILS_TRANSACTION, data, reply, 0);
//	        reply.readException();
//	        ActivityManager.TaskThumbnails bm = null;
//	        if (reply.readInt() != 0) {
//	            bm = ActivityManager.TaskThumbnails.CREATOR.createFromParcel(reply);
//	        }
//	        data.recycle();
//	        reply.recycle();
//	        return bm;
//	    }
	    public List getServices(int maxNum, int flags) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(maxNum);
	        data.writeInt(flags);
	        mRemote.transact(GET_SERVICES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList list = null;
	        int N = reply.readInt();
	        if (N >= 0) {
	            list = new ArrayList();
	            while (N > 0) {
	                ActivityManager.RunningServiceInfo info =
	                        ActivityManager.RunningServiceInfo.CREATOR
	                        .createFromParcel(reply);
	                list.add(info);
	                N--;
	            }
	        }
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
	    public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState()
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_PROCESSES_IN_ERROR_STATE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList<ActivityManager.ProcessErrorStateInfo> list
	            = reply.createTypedArrayList(ActivityManager.ProcessErrorStateInfo.CREATOR);
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
	    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses()
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_RUNNING_APP_PROCESSES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList<ActivityManager.RunningAppProcessInfo> list
	        = reply.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
	    public List<ApplicationInfo> getRunningExternalApplications()
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_RUNNING_EXTERNAL_APPLICATIONS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ArrayList<ApplicationInfo> list
	        = reply.createTypedArrayList(ApplicationInfo.CREATOR);
	        data.recycle();
	        reply.recycle();
	        return list;
	    }
	    public void moveTaskToFront(int task, int flags, Bundle options) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(task);
	        data.writeInt(flags);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(MOVE_TASK_TO_FRONT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void moveTaskToBack(int task) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(task);
	        mRemote.transact(MOVE_TASK_TO_BACK_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public boolean moveActivityTaskToBack(IBinder token, boolean nonRoot)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(nonRoot ? 1 : 0);
	        mRemote.transact(MOVE_ACTIVITY_TASK_TO_BACK_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void moveTaskBackwards(int task) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(task);
	        mRemote.transact(MOVE_TASK_BACKWARDS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int getTaskForActivity(IBinder token, boolean onlyRoot) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(onlyRoot ? 1 : 0);
	        mRemote.transact(GET_TASK_FOR_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void reportThumbnail(IBinder token,
	                                Bitmap thumbnail, CharSequence description) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        if (thumbnail != null) {
	            data.writeInt(1);
	            thumbnail.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        TextUtils.writeToParcel(description, data, 0);
	        mRemote.transact(REPORT_THUMBNAIL_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public ContentProviderHolder getContentProvider(IApplicationThread caller,
	            String name, boolean stable) throws RemoteException {
//	        
	    	Log.d("BrokerAMP","getContentProvider been faked!");
//	    	
//	    	Parcel data = Parcel.obtain();
//	        Parcel reply = Parcel.obtain();
//	        data.writeInterfaceToken(IActivityManager.descriptor);
//	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
//	        data.writeString(name);
//	        data.writeInt(stable ? 1 : 0);
//	        mRemote.transact(GET_CONTENT_PROVIDER_TRANSACTION, data, reply, 0);
//	        reply.readException();
//	        int res = reply.readInt();
//	        ContentProviderHolder cph = null;
//	        if (res != 0) {
//	            cph = ContentProviderHolder.CREATOR.createFromParcel(reply);
//	        }
//	        data.recycle();
//	        reply.recycle();
	    	ContentProviderHolder cph=null;
	    	if(caller!=null)  cph=(ContentProviderHolder)IsolatedProcessService.getHolder(caller.asBinder(), name, stable);
	        return cph;
	    }
	    public ContentProviderHolder getContentProviderExternal(String name, IBinder token)
	            throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(name);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_CONTENT_PROVIDER_EXTERNAL_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        ContentProviderHolder cph = null;
	        if (res != 0) {
	            cph = ContentProviderHolder.CREATOR.createFromParcel(reply);
	        }
	        data.recycle();
	        reply.recycle();
	        return cph;
	    }
	    public void publishContentProviders(IApplicationThread caller,
	            List<ContentProviderHolder> providers) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        data.writeTypedList(providers);
	        mRemote.transact(PUBLISH_CONTENT_PROVIDERS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public boolean refContentProvider(IBinder connection, int stable, int unstable)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(connection);
	        data.writeInt(stable);
	        data.writeInt(unstable);
	        mRemote.transact(REF_CONTENT_PROVIDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void unstableProviderDied(IBinder connection) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(connection);
	        mRemote.transact(UNSTABLE_PROVIDER_DIED_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void removeContentProvider(IBinder connection, boolean stable) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(connection);
	        data.writeInt(stable ? 1 : 0);
	        mRemote.transact(REMOVE_CONTENT_PROVIDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void removeContentProviderExternal(String name, IBinder token) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(name);
	        data.writeStrongBinder(token);
	        mRemote.transact(REMOVE_CONTENT_PROVIDER_EXTERNAL_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public PendingIntent getRunningServiceControlPanel(ComponentName service)
	            throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        service.writeToParcel(data, 0);
	        mRemote.transact(GET_RUNNING_SERVICE_CONTROL_PANEL_TRANSACTION, data, reply, 0);
	        reply.readException();
	        PendingIntent res = PendingIntent.readPendingIntentOrNullFromParcel(reply);
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    
	    public ComponentName startService(IApplicationThread caller, Intent service,
	            String resolvedType) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        service.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        mRemote.transact(START_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ComponentName res = ComponentName.readFromParcel(reply);
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public int stopService(IApplicationThread caller, Intent service,
	            String resolvedType) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        service.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        mRemote.transact(STOP_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    public boolean stopServiceToken(ComponentName className, IBinder token,
	            int startId) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        ComponentName.writeToParcel(className, data);
	        data.writeStrongBinder(token);
	        data.writeInt(startId);
	        mRemote.transact(STOP_SERVICE_TOKEN_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void setServiceForeground(ComponentName className, IBinder token,
	            int id, Notification notification, boolean removeNotification) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        ComponentName.writeToParcel(className, data);
	        data.writeStrongBinder(token);
	        data.writeInt(id);
	        if (notification != null) {
	            data.writeInt(1);
	            notification.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        data.writeInt(removeNotification ? 1 : 0);
	        mRemote.transact(SET_SERVICE_FOREGROUND_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int bindService(IApplicationThread caller, IBinder token,
	            Intent service, String resolvedType, IServiceConnection connection,
	            int flags, int userId) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        data.writeStrongBinder(token);
	        service.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        data.writeStrongBinder(connection.asBinder());
	        data.writeInt(flags);
	        data.writeInt(userId);
	        mRemote.transact(BIND_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public boolean unbindService(IServiceConnection connection) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(connection.asBinder());
	        mRemote.transact(UNBIND_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    
	    public void publishService(IBinder token,
	            Intent intent, IBinder service) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        intent.writeToParcel(data, 0);
	        data.writeStrongBinder(service);
	        mRemote.transact(PUBLISH_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void unbindFinished(IBinder token, Intent intent, boolean doRebind)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        intent.writeToParcel(data, 0);
	        data.writeInt(doRebind ? 1 : 0);
	        mRemote.transact(UNBIND_FINISHED_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void serviceDoneExecuting(IBinder token, int type, int startId,
	            int res) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(type);
	        data.writeInt(startId);
	        data.writeInt(res);
	        mRemote.transact(SERVICE_DONE_EXECUTING_TRANSACTION, data, reply, IBinder.FLAG_ONEWAY);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public IBinder peekService(Intent service, String resolvedType) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        service.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        mRemote.transact(PEEK_SERVICE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        IBinder binder = reply.readStrongBinder();
	        reply.recycle();
	        data.recycle();
	        return binder;
	    }

	    public boolean bindBackupAgent(ApplicationInfo app, int backupRestoreMode)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        app.writeToParcel(data, 0);
	        data.writeInt(backupRestoreMode);
	        mRemote.transact(START_BACKUP_AGENT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean success = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return success;
	    }

	    public void backupAgentCreated(String packageName, IBinder agent) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        data.writeStrongBinder(agent);
	        mRemote.transact(BACKUP_AGENT_CREATED_TRANSACTION, data, reply, 0);
	        reply.recycle();
	        data.recycle();
	    }

	    public void unbindBackupAgent(ApplicationInfo app) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        app.writeToParcel(data, 0);
	        mRemote.transact(UNBIND_BACKUP_AGENT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }

	    public boolean startInstrumentation(ComponentName className, String profileFile,
	            int flags, Bundle arguments, IInstrumentationWatcher watcher)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        ComponentName.writeToParcel(className, data);
	        data.writeString(profileFile);
	        data.writeInt(flags);
	        data.writeBundle(arguments);
	        data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
	        mRemote.transact(START_INSTRUMENTATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return res;
	    }

	    public void finishInstrumentation(IApplicationThread target,
	            int resultCode, Bundle results) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(target != null ? target.asBinder() : null);
	        data.writeInt(resultCode);
	        data.writeBundle(results);
	        mRemote.transact(FINISH_INSTRUMENTATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public Configuration getConfiguration() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_CONFIGURATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        Configuration res = Configuration.CREATOR.createFromParcel(reply);
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    public void updateConfiguration(Configuration values) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        values.writeToParcel(data, 0);
	        mRemote.transact(UPDATE_CONFIGURATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void setRequestedOrientation(IBinder token, int requestedOrientation)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(requestedOrientation);
	        mRemote.transact(SET_REQUESTED_ORIENTATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int getRequestedOrientation(IBinder token) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_REQUESTED_ORIENTATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public ComponentName getActivityClassForToken(IBinder token)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_ACTIVITY_CLASS_FOR_TOKEN_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ComponentName res = ComponentName.readFromParcel(reply);
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public String getPackageForToken(IBinder token) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(GET_PACKAGE_FOR_TOKEN_TRANSACTION, data, reply, 0);
	        reply.readException();
	        String res = reply.readString();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public IIntentSender getIntentSender(int type,
	            String packageName, IBinder token, String resultWho,
	            int requestCode, Intent[] intents, String[] resolvedTypes, int flags,
	            Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(type);
	        data.writeString(packageName);
	        data.writeStrongBinder(token);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        if (intents != null) {
	            data.writeInt(1);
	            data.writeTypedArray(intents, 0);
	            data.writeStringArray(resolvedTypes);
	        } else {
	            data.writeInt(0);
	        }
	        data.writeInt(flags);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(GET_INTENT_SENDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        IIntentSender res = IIntentSender.Stub.asInterface(
	            reply.readStrongBinder());
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void cancelIntentSender(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(sender.asBinder());
	        mRemote.transact(CANCEL_INTENT_SENDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public String getPackageForIntentSender(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(sender.asBinder());
	        mRemote.transact(GET_PACKAGE_FOR_INTENT_SENDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        String res = reply.readString();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public int getUidForIntentSender(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(sender.asBinder());
	        mRemote.transact(GET_UID_FOR_INTENT_SENDER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void setProcessLimit(int max) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(max);
	        mRemote.transact(SET_PROCESS_LIMIT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int getProcessLimit() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_PROCESS_LIMIT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void setProcessForeground(IBinder token, int pid,
	            boolean isForeground) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(pid);
	        data.writeInt(isForeground ? 1 : 0);
	        mRemote.transact(SET_PROCESS_FOREGROUND_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public int checkPermission(String permission, int pid, int uid)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(permission);
	        data.writeInt(pid);
	        data.writeInt(uid);
	        mRemote.transact(CHECK_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public boolean clearApplicationUserData(final String packageName,
	            final IPackageDataObserver observer, final int userId) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        data.writeStrongBinder(observer.asBinder());
	        data.writeInt(userId);
	        mRemote.transact(CLEAR_APP_DATA_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public int checkUriPermission(Uri uri, int pid, int uid, int mode) 
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        uri.writeToParcel(data, 0);
	        data.writeInt(pid);
	        data.writeInt(uid);
	        data.writeInt(mode);
	        mRemote.transact(CHECK_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void grantUriPermission(IApplicationThread caller, String targetPkg,
	            Uri uri, int mode) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller.asBinder());
	        data.writeString(targetPkg);
	        uri.writeToParcel(data, 0);
	        data.writeInt(mode);
	        mRemote.transact(GRANT_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void revokeUriPermission(IApplicationThread caller, Uri uri,
	            int mode) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller.asBinder());
	        uri.writeToParcel(data, 0);
	        data.writeInt(mode);
	        mRemote.transact(REVOKE_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void showWaitingForDebugger(IApplicationThread who, boolean waiting)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(who.asBinder());
	        data.writeInt(waiting ? 1 : 0);
	        mRemote.transact(SHOW_WAITING_FOR_DEBUGGER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void getMemoryInfo(ActivityManager.MemoryInfo outInfo) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_MEMORY_INFO_TRANSACTION, data, reply, 0);
	        reply.readException();
	        outInfo.readFromParcel(reply);
	        data.recycle();
	        reply.recycle();
	    }
	    public void unhandledBack() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(UNHANDLED_BACK_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public ParcelFileDescriptor openContentUri(Uri uri) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(OPEN_CONTENT_URI_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ParcelFileDescriptor pfd = null;
	        if (reply.readInt() != 0) {
	            pfd = ParcelFileDescriptor.CREATOR.createFromParcel(reply);
	        }
	        data.recycle();
	        reply.recycle();
	        return pfd;
	    }
	    public void goingToSleep() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GOING_TO_SLEEP_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void wakingUp() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(WAKING_UP_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void setLockScreenShown(boolean shown) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(shown ? 1 : 0);
	        mRemote.transact(SET_LOCK_SCREEN_SHOWN_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void setDebugApp(
	        String packageName, boolean waitForDebugger, boolean persistent)
	        throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        data.writeInt(waitForDebugger ? 1 : 0);
	        data.writeInt(persistent ? 1 : 0);
	        mRemote.transact(SET_DEBUG_APP_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void setAlwaysFinish(boolean enabled) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(enabled ? 1 : 0);
	        mRemote.transact(SET_ALWAYS_FINISH_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void setActivityController(IActivityController watcher) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
	        mRemote.transact(SET_ACTIVITY_CONTROLLER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public void enterSafeMode() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(ENTER_SAFE_MODE_TRANSACTION, data, null, 0);
	        data.recycle();
	    }
	    public void noteWakeupAlarm(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        data.writeStrongBinder(sender.asBinder());
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(NOTE_WAKEUP_ALARM_TRANSACTION, data, null, 0);
	        data.recycle();
	    }
	    public boolean killPids(int[] pids, String reason, boolean secure) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeIntArray(pids);
	        data.writeString(reason);
	        data.writeInt(secure ? 1 : 0);
	        mRemote.transact(KILL_PIDS_TRANSACTION, data, reply, 0);
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    @Override
	    public boolean killProcessesBelowForeground(String reason) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(reason);
	        mRemote.transact(KILL_PROCESSES_BELOW_FOREGROUND_TRANSACTION, data, reply, 0);
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    public void startRunning(String pkg, String cls, String action,
	            String indata) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(pkg);
	        data.writeString(cls);
	        data.writeString(action);
	        data.writeString(indata);
	        mRemote.transact(START_RUNNING_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    public boolean testIsSystemReady()
	    {
	        /* this base class version is never called */
	        return true;
	    }
	    public void handleApplicationCrash(IBinder app,
	            ApplicationErrorReport.CrashInfo crashInfo) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(app);
	        crashInfo.writeToParcel(data, 0);
	        mRemote.transact(HANDLE_APPLICATION_CRASH_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }

	    public boolean handleApplicationWtf(IBinder app, String tag,
	            ApplicationErrorReport.CrashInfo crashInfo) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(app);
	        data.writeString(tag);
	        crashInfo.writeToParcel(data, 0);
	        mRemote.transact(HANDLE_APPLICATION_WTF_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return res;
	    }

//	    public void handleApplicationStrictModeViolation(IBinder app,
//	            int violationMask,
//	            StrictMode.ViolationInfo info) throws RemoteException
//	    {
//	        Parcel data = Parcel.obtain();
//	        Parcel reply = Parcel.obtain();
//	        data.writeInterfaceToken(IActivityManager.descriptor);
//	        data.writeStrongBinder(app);
//	        data.writeInt(violationMask);
//	        info.writeToParcel(data, 0);
//	        mRemote.transact(HANDLE_APPLICATION_STRICT_MODE_VIOLATION_TRANSACTION, data, reply, 0);
//	        reply.readException();
//	        reply.recycle();
//	        data.recycle();
//	    }

	    public void signalPersistentProcesses(int sig) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(sig);
	        mRemote.transact(SIGNAL_PERSISTENT_PROCESSES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void killBackgroundProcesses(String packageName) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        mRemote.transact(KILL_BACKGROUND_PROCESSES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void killAllBackgroundProcesses() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(KILL_ALL_BACKGROUND_PROCESSES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void forceStopPackage(String packageName) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        mRemote.transact(FORCE_STOP_PACKAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public void getMyMemoryState(ActivityManager.RunningAppProcessInfo outInfo)
	            throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_MY_MEMORY_STATE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        outInfo.readFromParcel(reply);
	        reply.recycle();
	        data.recycle();
	    }

	    public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_DEVICE_CONFIGURATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        ConfigurationInfo res = ConfigurationInfo.CREATOR.createFromParcel(reply);
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    
	    public boolean profileControl(String process, boolean start,
	            String path, ParcelFileDescriptor fd, int profileType) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(process);
	        data.writeInt(start ? 1 : 0);
	        data.writeInt(profileType);
	        data.writeString(path);
	        if (fd != null) {
	            data.writeInt(1);
	            fd.writeToParcel(data, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(PROFILE_CONTROL_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    
	    public boolean shutdown(int timeout) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(timeout);
	        mRemote.transact(SHUTDOWN_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    
	    public void stopAppSwitches() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(STOP_APP_SWITCHES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }
	    
	    public void resumeAppSwitches() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(RESUME_APP_SWITCHES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }
	    
	    public int startActivityInPackage(int uid,
	            Intent intent, String resolvedType, IBinder resultTo,
	            String resultWho, int requestCode, int startFlags, Bundle options)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(uid);
	        intent.writeToParcel(data, 0);
	        data.writeString(resolvedType);
	        data.writeStrongBinder(resultTo);
	        data.writeString(resultWho);
	        data.writeInt(requestCode);
	        data.writeInt(startFlags);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITY_IN_PACKAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result;
	    }
	    
	    public void killApplicationWithUid(String pkg, int uid) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(pkg);
	        data.writeInt(uid);
	        mRemote.transact(KILL_APPLICATION_WITH_UID_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public void closeSystemDialogs(String reason) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(reason);
	        mRemote.transact(CLOSE_SYSTEM_DIALOGS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public Debug.MemoryInfo[] getProcessMemoryInfo(int[] pids)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeIntArray(pids);
	        mRemote.transact(GET_PROCESS_MEMORY_INFO_TRANSACTION, data, reply, 0);
	        reply.readException();
	        Debug.MemoryInfo[] res = reply.createTypedArray(Debug.MemoryInfo.CREATOR);
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public void killApplicationProcess(String processName, int uid) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(processName);
	        data.writeInt(uid);
	        mRemote.transact(KILL_APPLICATION_PROCESS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	        
	    public void overridePendingTransition(IBinder token, String packageName,
	            int enterAnim, int exitAnim) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeString(packageName);
	        data.writeInt(enterAnim);
	        data.writeInt(exitAnim);
	        mRemote.transact(OVERRIDE_PENDING_TRANSITION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public boolean isUserAMonkey() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(IS_USER_A_MONKEY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }
	    
	    public void finishHeavyWeightApp() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(FINISH_HEAVY_WEIGHT_APP_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }
	    
	    public void setImmersive(IBinder token, boolean immersive)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeInt(immersive ? 1 : 0);
	        mRemote.transact(SET_IMMERSIVE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public boolean isImmersive(IBinder token)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        mRemote.transact(IS_IMMERSIVE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() == 1;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public boolean isTopActivityImmersive()
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(IS_TOP_ACTIVITY_IMMERSIVE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() == 1;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public void crashApplication(int uid, int initialPid, String packageName,
	            String message) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(uid);
	        data.writeInt(initialPid);
	        data.writeString(packageName);
	        data.writeString(message);
	        mRemote.transact(CRASH_APPLICATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public String getProviderMimeType(Uri uri)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        uri.writeToParcel(data, 0);
	        mRemote.transact(GET_PROVIDER_MIME_TYPE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        String res = reply.readString();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public IBinder newUriPermissionOwner(String name)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(name);
	        mRemote.transact(NEW_URI_PERMISSION_OWNER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        IBinder res = reply.readStrongBinder();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public void grantUriPermissionFromOwner(IBinder owner, int fromUid, String targetPkg,
	            Uri uri, int mode) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(owner);
	        data.writeInt(fromUid);
	        data.writeString(targetPkg);
	        uri.writeToParcel(data, 0);
	        data.writeInt(mode);
	        mRemote.transact(GRANT_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void revokeUriPermissionFromOwner(IBinder owner, Uri uri,
	            int mode) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(owner);
	        if (uri != null) {
	            data.writeInt(1);
	            uri.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        data.writeInt(mode);
	        mRemote.transact(REVOKE_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public int checkGrantUriPermission(int callingUid, String targetPkg,
	            Uri uri, int modeFlags) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(callingUid);
	        data.writeString(targetPkg);
	        uri.writeToParcel(data, 0);
	        data.writeInt(modeFlags);
	        mRemote.transact(CHECK_GRANT_URI_PERMISSION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int res = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public boolean dumpHeap(String process, boolean managed,
	            String path, ParcelFileDescriptor fd) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(process);
	        data.writeInt(managed ? 1 : 0);
	        data.writeString(path);
	        if (fd != null) {
	            data.writeInt(1);
	            fd.writeToParcel(data, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(DUMP_HEAP_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return res;
	    }
	    
	    public int startActivities(IApplicationThread caller,
	            Intent[] intents, String[] resolvedTypes, IBinder resultTo,
	            Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(caller != null ? caller.asBinder() : null);
	        data.writeTypedArray(intents, 0);
	        data.writeStringArray(resolvedTypes);
	        data.writeStrongBinder(resultTo);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITIES_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result;
	    }

	    public int startActivitiesInPackage(int uid,
	            Intent[] intents, String[] resolvedTypes, IBinder resultTo,
	            Bundle options) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(uid);
	        data.writeTypedArray(intents, 0);
	        data.writeStringArray(resolvedTypes);
	        data.writeStrongBinder(resultTo);
	        if (options != null) {
	            data.writeInt(1);
	            options.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(START_ACTIVITIES_IN_PACKAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return result;
	    }

	    public int getFrontActivityScreenCompatMode() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(GET_FRONT_ACTIVITY_SCREEN_COMPAT_MODE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int mode = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return mode;
	    }

	    public void setFrontActivityScreenCompatMode(int mode) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(mode);
	        mRemote.transact(SET_FRONT_ACTIVITY_SCREEN_COMPAT_MODE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }

	    public int getPackageScreenCompatMode(String packageName) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        mRemote.transact(GET_PACKAGE_SCREEN_COMPAT_MODE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int mode = reply.readInt();
	        reply.recycle();
	        data.recycle();
	        return mode;
	    }

	    public void setPackageScreenCompatMode(String packageName, int mode)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        data.writeInt(mode);
	        mRemote.transact(SET_PACKAGE_SCREEN_COMPAT_MODE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }

	    public boolean getPackageAskScreenCompat(String packageName) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        mRemote.transact(GET_PACKAGE_ASK_SCREEN_COMPAT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean ask = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return ask;
	    }

	    public void setPackageAskScreenCompat(String packageName, boolean ask)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeString(packageName);
	        data.writeInt(ask ? 1 : 0);
	        mRemote.transact(SET_PACKAGE_ASK_SCREEN_COMPAT_TRANSACTION, data, reply, 0);
	        reply.readException();
	        reply.recycle();
	        data.recycle();
	    }

	    public boolean switchUser(int userid) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(userid);
	        mRemote.transact(SWITCH_USER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean result = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return result;
	    }

	    public UserInfo getCurrentUser() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(SWITCH_USER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        UserInfo userInfo = UserInfo.CREATOR.createFromParcel(reply);
	        reply.recycle();
	        data.recycle();
	        return userInfo;
	    }

	    public boolean removeSubTask(int taskId, int subTaskIndex) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(taskId);
	        data.writeInt(subTaskIndex);
	        mRemote.transact(REMOVE_SUB_TASK_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean result = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return result;
	    }

	    public boolean removeTask(int taskId, int flags) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeInt(taskId);
	        data.writeInt(flags);
	        mRemote.transact(REMOVE_TASK_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean result = reply.readInt() != 0;
	        reply.recycle();
	        data.recycle();
	        return result;
	    }

	    public void registerProcessObserver(IProcessObserver observer) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(observer != null ? observer.asBinder() : null);
	        mRemote.transact(REGISTER_PROCESS_OBSERVER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void unregisterProcessObserver(IProcessObserver observer) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(observer != null ? observer.asBinder() : null);
	        mRemote.transact(UNREGISTER_PROCESS_OBSERVER_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public boolean isIntentSenderTargetedToPackage(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(sender.asBinder());
	        mRemote.transact(IS_INTENT_SENDER_TARGETED_TO_PACKAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public boolean isIntentSenderAnActivity(IIntentSender sender) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(sender.asBinder());
	        mRemote.transact(IS_INTENT_SENDER_AN_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean res = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public void updatePersistentConfiguration(Configuration values) throws RemoteException
	    {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        values.writeToParcel(data, 0);
	        mRemote.transact(UPDATE_PERSISTENT_CONFIGURATION_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public long[] getProcessPss(int[] pids) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeIntArray(pids);
	        mRemote.transact(GET_PROCESS_PSS_TRANSACTION, data, reply, 0);
	        reply.readException();
	        long[] res = reply.createLongArray();
	        data.recycle();
	        reply.recycle();
	        return res;
	    }

	    public void showBootMessage(CharSequence msg, boolean always) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        TextUtils.writeToParcel(msg, data, 0);
	        data.writeInt(always ? 1 : 0);
	        mRemote.transact(SHOW_BOOT_MESSAGE_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public void dismissKeyguardOnNextActivity() throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        mRemote.transact(DISMISS_KEYGUARD_ON_NEXT_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        data.recycle();
	        reply.recycle();
	    }

	    public boolean targetTaskAffinityMatchesActivity(IBinder token, String destAffinity)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        data.writeString(destAffinity);
	        mRemote.transact(TARGET_TASK_AFFINITY_MATCHES_ACTIVITY_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean result = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return result;
	    }

	    public boolean navigateUpTo(IBinder token, Intent target, int resultCode, Intent resultData)
	            throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(token);
	        target.writeToParcel(data, 0);
	        data.writeInt(resultCode);
	        if (resultData != null) {
	            data.writeInt(1);
	            resultData.writeToParcel(data, 0);
	        } else {
	            data.writeInt(0);
	        }
	        mRemote.transact(NAVIGATE_UP_TO_TRANSACTION, data, reply, 0);
	        reply.readException();
	        boolean result = reply.readInt() != 0;
	        data.recycle();
	        reply.recycle();
	        return result;
	    }

	    public int getLaunchedFromUid(IBinder activityToken) throws RemoteException {
	        Parcel data = Parcel.obtain();
	        Parcel reply = Parcel.obtain();
	        data.writeInterfaceToken(IActivityManager.descriptor);
	        data.writeStrongBinder(activityToken);
	        mRemote.transact(GET_LAUNCHED_FROM_UID_TRANSACTION, data, reply, 0);
	        reply.readException();
	        int result = reply.readInt();
	        data.recycle();
	        reply.recycle();
	        return result;
	    }

	    private IBinder mRemote;

}
