package android.os;

/**
 * @hide
 */
interface ICancellationSignal {
    oneway void cancel();
}