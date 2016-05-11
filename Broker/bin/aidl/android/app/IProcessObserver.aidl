package android.app;

/** {@hide} */
oneway interface IProcessObserver {

    void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities);
    void onImportanceChanged(int pid, int uid, int importance);
    void onProcessDied(int pid, int uid);

}