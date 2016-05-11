package android.app;

import android.graphics.Bitmap;

/**
 * System private API for receiving updated thumbnails from a checkpoint.
 *
 * {@hide}
 */
oneway interface IThumbnailReceiver {
    void newThumbnail(int id, in Bitmap thumbnail, CharSequence description);
    void finished();
}
