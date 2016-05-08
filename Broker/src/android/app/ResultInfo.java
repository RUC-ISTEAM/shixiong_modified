package android.app;

import android.R.integer;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class ResultInfo implements Parcelable{

	public final String mResultWho;
	public final int mRequestCode;
	public final int mResultCode;
	public final Intent mData;
	
	public ResultInfo(String resultWho, int requestCode, int resultCode, Intent data) {
		mResultWho = resultWho;
		mRequestCode = requestCode;
		mResultCode = resultCode;
		mData = data;
	}
	
	public ResultInfo(Parcel in) {
		mResultWho = in.readString();
		mRequestCode = in.readInt();
		mResultCode = in.readInt();
		if (in.readInt() != 0) {
			mData = Intent.CREATOR.createFromParcel(in);
		} else {
			mData = null;
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(mResultWho);
		out.writeInt(mRequestCode);
		out.writeInt(mResultCode);
		if (mData != null) {
			out.writeInt(1);
			mData.writeToParcel(out, 0);
		} else {
			out.writeInt(0);
		}
	}
	
	public static final Parcelable.Creator<ResultInfo> CREATOR = new Creator<ResultInfo>() {
		
		@Override
		public ResultInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ResultInfo[size];
		}
		
		@Override
		public ResultInfo createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new ResultInfo(in);
		}
	};
	
	public String toString() {
		return "ResultInfo{who=" + mResultWho + ", request=" + mRequestCode +", result=" + mResultCode + ", data=" + mData +"}";
	}

}
