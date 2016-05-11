
package android.content.pm;

/**
 * Callback for moving package resources from the Package Manager.
 * @hide
 */
oneway interface IPackageMoveObserver {
    void packageMoved(in String packageName, int returnCode);
}
