package android.app;

//import android.app.IActivityManager.ContentProviderHolder;
import android.content.ContentProviderNative;
import android.content.IContentProvider;
import android.content.pm.ProviderInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;


public class ContentProviderHolder extends android.app.IActivityManager.ContentProviderHolder implements Parcelable {
    public final ProviderInfo info;
    public IContentProvider provider;
    public IBinder connection;
    public boolean noReleaseNeeded;

    public ContentProviderHolder(ProviderInfo _info) {
        super(_info);
        info=_info;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        info.writeToParcel(dest, 0);
        if (provider != null) {
            dest.writeStrongBinder(provider.asBinder());
        } else {
            dest.writeStrongBinder(null);
        }
        dest.writeStrongBinder(connection);
        dest.writeInt(noReleaseNeeded ? 1:0);
    }

    public static final Parcelable.Creator<ContentProviderHolder> CREATOR
            = new Parcelable.Creator<ContentProviderHolder>() {
        public ContentProviderHolder createFromParcel(Parcel source) {
            return new ContentProviderHolder(source);
        }

        public ContentProviderHolder[] newArray(int size) {
            return new ContentProviderHolder[size];
        }
    };

    private ContentProviderHolder(Parcel source) {
        super(source);
    	info = ProviderInfo.CREATOR.createFromParcel(source);
        provider = ContentProviderNative.asInterface(
            source.readStrongBinder());
        connection = source.readStrongBinder();
        noReleaseNeeded = source.readInt() != 0;
    }
}

