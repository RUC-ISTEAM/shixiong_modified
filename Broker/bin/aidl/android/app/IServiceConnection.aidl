package android.app;

import android.content.ComponentName;

/** @hide */
oneway interface IServiceConnection {
    void connected(in ComponentName name, IBinder service);
}