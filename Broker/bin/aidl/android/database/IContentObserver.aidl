package android.database;

import android.net.Uri;
/**
 * @hide
 */
interface IContentObserver
{
    /**
     * This method is called when an update occurs to the cursor that is being
     * observed. selfUpdate is true if the update was caused by a call to
     * commit on the cursor that is being observed.
     */
    oneway void onChange(boolean selfUpdate, in Uri uri);
}