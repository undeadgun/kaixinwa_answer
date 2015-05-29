package com.qkhl.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 
 * @ClassName:ConnectionChangeReceiver
 * @Description:网络监听
 * @author:guoqingyu
 * 
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {

	public static boolean netconnection;
	//private static final String TAG = ConnectionChangeReceiver.class.getSimpleName();

	public static boolean getNetconnection() {
		return netconnection;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		ConnectivityManager connectMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mobNetInfo != null) {
			if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
				netconnection = false;
				Toast.makeText(context,"网络未连接", Toast.LENGTH_LONG).show();
			} else {
				netconnection = true;

			}

		} else {
			if (!wifiNetInfo.isConnected()) {
				netconnection = false;
				Toast.makeText(context,"网络未连接", Toast.LENGTH_LONG).show();
			} else {
				netconnection = true;

			}
		}

	}
}
