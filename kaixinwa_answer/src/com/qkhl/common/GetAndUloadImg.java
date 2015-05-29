package com.qkhl.common;

import java.io.FileNotFoundException;

import android.util.Log;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ResumableTaskOption;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.qkhl.activity.setup_meActivity;

public class GetAndUloadImg {

	private static String TAG = "GetAndUploadFileImg";

	private OSSService ossService;
	private OSSBucket bucket;

	public void show(String imgpath, String imgname) {
		ossService = OSSServiceProvider.getService();
		bucket = ossService.getOssBucket("kaixinwaavatar");

		// 文件的常规操作如普通上传、下载、拷贝、删除等，与Data类一致，故这里只给出断点下载和断点上传的demo
		// resumableDownloadWithSpecConfig();
		// delay();
		resumableUpload(imgpath, imgname);
		delay();
		// resumableDownload();
		// delay();
	}

	public void delay() {
		try {
			Thread.sleep(30 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 断点上传
	public void resumableUpload(String imgpath, String imgname) {
		OSSFile bigfFile = ossService.getOssFile(bucket, imgname);
		try {
			bigfFile.setUploadFilePath(imgpath, "png/jpg");
			bigfFile.ResumableUploadInBackground(new SaveCallback() {

				@Override
				public void onSuccess(String objectKey) {
					Log.d(TAG, "[onSuccess] - " + objectKey+ " upload success!上传成功"); 
					setup_meActivity.handler.sendEmptyMessage(1);

				}

				@Override
				public void onProgress(String objectKey, int byteCount,
						int totalSize) {
					Log.d(TAG, "[onProgress] - current upload " + objectKey+ " bytes: " + byteCount + " in total: "+ totalSize);
				}

				@Override
				public void onFailure(String objectKey,
						OSSException ossException) {
					Log.e(TAG, "[onFailure] - upload " + objectKey
							+ " failed!\n" + ossException.toString());
					ossException.printStackTrace();
					ossException.getException().printStackTrace();
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 断点下载
	public void resumableDownload(String imgpath, String imgname) {
		OSSFile bigFile = ossService.getOssFile(bucket, imgname);
		bigFile.ResumableDownloadToInBackground(
				imgpath, new GetFileCallback() {

					@Override
					public void onSuccess(String objectKey, String filePath) {
						Log.d(TAG, "[onSuccess] - " + objectKey
								+ " storage path: " + filePath);
					}

					@Override
					public void onProgress(String objectKey, int byteCount,
							int totalSize) {
						Log.d(TAG, "[onProgress] - current download: "
								+ objectKey + " bytes:" + byteCount
								+ " in total:" + totalSize);
					}

					@Override
					public void onFailure(String objectKey,
							OSSException ossException) {
						Log.e(TAG, "[onFailure] - download " + objectKey
								+ " failed!\n" + ossException.toString());
						ossException.printStackTrace();
					}
				});
	}

	// 设置相关参数的断点续传
	public void resumableDownloadWithSpecConfig() {
		OSSFile bigFile = ossService.getOssFile(bucket, "bigFile.dat");
		ResumableTaskOption option = new ResumableTaskOption();
		option.setAutoRetryTime(2); // 默认为2次，最大3次
		option.setThreadNum(2); // 默认并发3个线程，最大5个
		bigFile.ResumableDownloadToInBackground(
				"/storage/sdcard0/src_file/bigFile.dat", new GetFileCallback() {

					@Override
					public void onSuccess(String objectKey, String filePath) {
						Log.d(TAG, "[onSuccess] - " + objectKey
								+ " storage path: " + filePath);
					}

					@Override
					public void onProgress(String objectKey, int byteCount,
							int totalSize) {
						Log.d(TAG, "[onProgress] - current download: "
								+ objectKey + " bytes:" + byteCount
								+ " in total:" + totalSize);
					}

					@Override
					public void onFailure(String objectKey,
							OSSException ossException) {
						Log.e(TAG, "[onFailure] - download " + objectKey
								+ " failed!\n" + ossException.toString());
						ossException.printStackTrace();
					}
				});
	}
}
