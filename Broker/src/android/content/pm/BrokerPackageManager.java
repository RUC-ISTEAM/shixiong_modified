package android.content.pm;

import android.content.ComponentName;
import android.content.IntentSender;
import android.content.SyncStatusObserver;
import android.os.RemoteException;
import android.util.Log;

public class BrokerPackageManager {

	
	public static IPackageManager bPackageManager = new IPackageManager.Stub() {
		@Override
		public String[] currentToCanonicalPackageNames(String[] names)
				throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] canonicalToCurrentPackageNames(String[] names)
				throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int checkPermission(String permName, String pkgName)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int checkUidPermission(String permName, int uid)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void removePermission(String name) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void grantPermission(String packageName, String permissionName)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void revokePermission(String packageName, String permissionName)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isProtectedBroadcast(String actionName)
				throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int checkSignatures(String pkg1, String pkg2)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int checkUidSignatures(int uid1, int uid2)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String[] getPackagesForUid(int uid) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNameForUid(int uid) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getUidForSharedUser(String sharedUserName)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void finishPackageInstall(int token) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setInstallerPackageName(String targetPackage,
				String installerPackageName) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getInstallerPackageName(String packageName)
				throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addPackageToPreferred(String packageName)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removePackageFromPreferred(String packageName)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void clearPackagePreferredActivities(String packageName)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setComponentEnabledSetting(ComponentName componentName,
				int newState, int flags, int userId) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getComponentEnabledSetting(ComponentName componentName,
				int userId) throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setApplicationEnabledSetting(String packageName,
				int newState, int flags, int userId) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getApplicationEnabledSetting(String packageName, int userId)
				throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setPackageStoppedState(String packageName, boolean stopped,
				int userId) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void freeStorage(long freeStorageSize, IntentSender pi)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String[] getSystemSharedLibraryNames() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasSystemFeature(String name) throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void enterSafeMode() throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isSafeMode() throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void systemReady() throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasSystemUidErrors() throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void performBootDexOpt() throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean performDexOpt(String packageName) throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateExternalMediaStatus(boolean mounted,
				boolean reportStatus) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String nextPackageToClean(String lastPackage)
				throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean setInstallLocation(int loc) throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getInstallLocation() throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean removeUser(int userId) throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateUserName(int userId, String name)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void verifyPendingInstall(int id, int verificationCode)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isFirstBoot() throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setPermissionEnforced(String permission, boolean enforced)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isPermissionEnforced(String permission)
				throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}
		
	};
}
