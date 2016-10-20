package com.yu.surprise.test;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableTest implements Parcelable{
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString("The Parcelable String");
		
	}
	public static final Parcelable.Creator<String> CREATOR = new Creator<String>() {
		@Override
		public String[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
