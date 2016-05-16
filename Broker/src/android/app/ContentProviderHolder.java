package android.app;

//import android.app.IActivityManager.ContentProviderHolder;
import android.content.ContentProviderNative;
import android.content.IContentProvider;
import android.content.pm.ProviderInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class ContentProviderHolder implements Parcelable {
    public final ProviderInfo info;
    public IContentProvider provider;
    public IBinder connection;
    public boolean noReleaseNeeded;

    public ContentProviderHolder(ProviderInfo _info) {
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
    	info = ProviderInfo.CREATOR.createFromParcel(source);
        provider = ContentProviderNative.asInterface(
            source.readStrongBinder());
        connection = source.readStrongBinder();
        noReleaseNeeded = source.readInt() != 0;
    }
    public static android.app.IActivityManager.ContentProviderHolder asRealContentProvider(ContentProviderHolder cph){
    	Log.d("contentHolder", "Real:"+cph);
    	android.app.IActivityManager.ContentProviderHolder cph2= new android.app.IActivityManager.ContentProviderHolder(cph.info);
    	cph2.provider = cph.provider;
    	cph2.connection = cph.connection;
    	cph2.noReleaseNeeded = cph.noReleaseNeeded;
    	Log.d("contentHolder", "asReal:"+cph);
    	return cph2;
    	
    }
    public static ContentProviderHolder asOurContentProvider(android.app.IActivityManager.ContentProviderHolder cph){
    	Log.d("contentHolder", "Our:"+cph);
    	ContentProviderHolder cph2= new ContentProviderHolder(cph.info);
    	cph2.provider = cph.provider;
    	cph2.connection = cph.connection;
    	cph2.noReleaseNeeded = cph.noReleaseNeeded;
    	Log.d("contentHolder", "asOur:"+cph);    	
    	return cph2;
    }
}

