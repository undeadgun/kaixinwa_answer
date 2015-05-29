package com.qkhl.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtil {
	private Context context;

	public ConnectionUtil(Context context) {
		this.context = context;

	}

	public boolean isConnection() {
		ConnectivityManager connectMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobNetInfo = connectMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiNetInfo = connectMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mobNetInfo != null) {
			if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
				return false;
			} else {
				return true;
			}
		} else {
			if (!wifiNetInfo.isConnected()) {
				return false;
			} else {
				return true;
			}
		}
	}
}
