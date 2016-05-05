package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;

public class CompatibilityInfo implements Parcelable{
	public static final CompatibilityInfo DEFAULT_COMPATIBILITY_INFO = new CompatibilityInfo() {};
	
	private static final int NEVER_NEEDS_COMPAT = 4;
	private static int mCompatibilityFlags = 0;
	private static int applicationDensity = 0;
	private static float applicationScale = 0;
	private static float applicationInvertedScale = 0;

	private CompatibilityInfo(int compFlags, int  dens, float scales, float invertedScale ) {
		mCompatibilityFlags = compFlags;
		applicationDensity = dens;
		applicationScale = scales;
		applicationInvertedScale = invertedScale;
	}
	
	private CompatibilityInfo() {
		this(NEVER_NEEDS_COMPAT, DisplayMetrics.DENSITY_DEFAULT, 1.0f, 1.0f);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mCompatibilityFlags);
		dest.writeInt(applicationDensity);
		dest.writeFloat(applicationScale);
		dest.writeFloat(applicationInvertedScale);
	}

}
