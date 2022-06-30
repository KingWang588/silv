package com.yhy.ooslibrary.util;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by zhouzhuo on 12/3/15.
 */
public class PutObjectSamples {

	private OSS oss;
	private String objectKey;
	private String uploadFilePath;

	// 运行sample前需要配置以下字段为有效的值
	private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	private static final String accessKeySecret = "8nbbTxsjGTwH56bHuhtTB67zizW51W";
	private static final String bucketName = "hzzll-api-private";

	// private static final String accessKeyId = "";
	// private static final String uploadObject = "";
	// private static final String downloadObject = "";

	/**
	 * 阿里云OSS文件上传
	 * 
	 * @param context
	 *            上下文
	 * @param accessKeyId
	 *            KeyId
	 * @param accessKeySecret
	 *            KeySecret
	 * @param endpoint
	 *            http://oss-cn-shanghai.aliyuncs.com
	 * @param bucketName
	 *            服务器空间名称
	 * @param objectKey
	 *            储存到服务器上的地址
	 * @param uploadFilePath
	 *            本地文件路径
	 */
	public PutObjectSamples(Context context, String accessKeyId,
							String objectKey, String uploadFilePath) {

		OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(
				accessKeyId, accessKeySecret);

		ClientConfiguration conf = new ClientConfiguration();
		conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
		conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
		conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
		conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
		OSSLog.enableLog();

		oss = new OSSClient(context, endpoint, credentialProvider, conf);
		this.objectKey = objectKey;
		this.uploadFilePath = uploadFilePath;

	}

	/**
	 * 从本地文件上传，使用非阻塞的异步接口
	 * 
	 * @param onPregress
	 *            上传进度
	 * @param onComple
	 *            结果回调
	 */
	public void asyncPutObjectFromLocalFile(
			OSSProgressCallback<PutObjectRequest> onPregress,
			OSSCompletedCallback<PutObjectRequest, PutObjectResult> onComple) {
		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketName, objectKey,
				uploadFilePath);
		put.setProgressCallback(onPregress);

		// // 异步上传时可以设置进度回调
		// put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
		// @Override
		// public void onProgress(PutObjectRequest request, long currentSize,
		// long totalSize) {
		// Log.e("WP", "上传进度 currentSize: " + currentSize + " totalSize: "
		// + totalSize);
		// }
		// });
		// new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
		// @Override
		// public void onSuccess(PutObjectRequest request,
		// PutObjectResult result) {
		// Log.e("WP", "上传成功 result:" + result.getETag() + " "
		// + result.getRequestId());
		// }
		//
		// @Override
		// public void onFailure(PutObjectRequest request,
		// ClientException clientExcepion,
		// ServiceException serviceException) {
		// // 请求异常
		// if (clientExcepion != null) {
		// // 本地异常如网络异常等
		// clientExcepion.printStackTrace();
		// Log.e("WP", "上传失败 本地异常！");
		// }
		// if (serviceException != null) {
		// // 服务异常
		// Log.e("WP", "上传失败 服务器异常！");
		// Log.e("WP",
		// "ErrorCode="
		// + serviceException.getErrorCode());
		// Log.e("WP",
		// "RequestId="
		// + serviceException.getRequestId());
		// Log.e("WP",
		// "HostId=" + serviceException.getHostId());
		// Log.e("WP",
		// "RawMessage="
		// + serviceException.getRawMessage());
		// }
		// }
		// }

		OSSAsyncTask task = oss.asyncPutObject(put, onComple);
	}

	/**
	 * 直接上传二进制数据，使用阻塞的同步接口
	 */
	public void putObjectFromByteArray() {
		// 构造测试的上传数据
		byte[] uploadData = new byte[100 * 1024];
		new Random().nextBytes(uploadData);

		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketName, objectKey,
				uploadData);

		try {
			PutObjectResult putResult = oss.putObject(put);

			Log.d("PutObject", "UploadSuccess");

			Log.d("ETag", putResult.getETag());
			Log.d("RequestId", putResult.getRequestId());
		} catch (ClientException e) {
			// 本地异常如网络异常等
			e.printStackTrace();
		} catch (ServiceException e) {
			// 服务异常
			Log.e("RequestId", e.getRequestId());
			Log.e("ErrorCode", e.getErrorCode());
			Log.e("HostId", e.getHostId());
			Log.e("RawMessage", e.getRawMessage());
		}
	}

	/**
	 * 上传时设置ContentType等，也可以添加自定义meta信息
	 */
	public void putObjectWithMetadataSetting() {
		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketName, objectKey,
				uploadFilePath);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/octet-stream");
		metadata.addUserMetadata("x-oss-meta-name1", "value1");

		put.setMetadata(metadata);

		// 异步上传时可以设置进度回调
		put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
			@Override
			public void onProgress(PutObjectRequest request, long currentSize,
					long totalSize) {
				Log.d("PutObject", "currentSize: " + currentSize
						+ " totalSize: " + totalSize);
			}
		});

		OSSAsyncTask task = oss.asyncPutObject(put,
				new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
					@Override
					public void onSuccess(PutObjectRequest request,
							PutObjectResult result) {
						Log.d("PutObject", "UploadSuccess");

						Log.d("ETag", result.getETag());
						Log.d("RequestId", result.getRequestId());
					}

					@Override
					public void onFailure(PutObjectRequest request,
							ClientException clientExcepion,
							ServiceException serviceException) {
						// 请求异常
						if (clientExcepion != null) {
							// 本地异常如网络异常等
							clientExcepion.printStackTrace();
						}
						if (serviceException != null) {
							// 服务异常
							Log.e("ErrorCode", serviceException.getErrorCode());
							Log.e("RequestId", serviceException.getRequestId());
							Log.e("HostId", serviceException.getHostId());
							Log.e("RawMessage",
									serviceException.getRawMessage());
						}
					}
				});
	}

	/**
	 * 上传文件可以设置server回调
	 */
	public void asyncPutObjectWithServerCallback() {
		// 构造上传请求
		final PutObjectRequest put = new PutObjectRequest(bucketName,
				objectKey, uploadFilePath);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/octet-stream");

		put.setMetadata(metadata);

		put.setCallbackParam(new HashMap<String, String>() {
			{
				put("callbackUrl", "110.75.82.106/mbaas/callback");
				put("callbackBody", "test");
			}
		});

		// 异步上传时可以设置进度回调
		put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
			@Override
			public void onProgress(PutObjectRequest request, long currentSize,
					long totalSize) {
				Log.d("PutObject", "currentSize: " + currentSize
						+ " totalSize: " + totalSize);
			}
		});

		OSSAsyncTask task = oss.asyncPutObject(put,
				new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
					@Override
					public void onSuccess(PutObjectRequest request,
							PutObjectResult result) {
						Log.d("PutObject", "UploadSuccess");

						// 只有设置了servercallback，这个值才有数据
						String serverCallbackReturnJson = result
								.getServerCallbackReturnBody();

						Log.d("servercallback", serverCallbackReturnJson);
					}

					@Override
					public void onFailure(PutObjectRequest request,
							ClientException clientExcepion,
							ServiceException serviceException) {
						// 请求异常
						if (clientExcepion != null) {
							// 本地异常如网络异常等
							clientExcepion.printStackTrace();
						}
						if (serviceException != null) {
							// 服务异常
							Log.e("ErrorCode", serviceException.getErrorCode());
							Log.e("RequestId", serviceException.getRequestId());
							Log.e("HostId", serviceException.getHostId());
							Log.e("RawMessage",
									serviceException.getRawMessage());
						}
					}
				});
	}

	public void asyncPutObjectWithMD5Verify() {
		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketName, objectKey,
				uploadFilePath);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/octet-stream");
		try {
			// 设置Md5以便校验
			metadata.setContentMD5(BinaryUtil
					.calculateBase64Md5(uploadFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		put.setMetadata(metadata);

		// 异步上传时可以设置进度回调
		put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
			@Override
			public void onProgress(PutObjectRequest request, long currentSize,
					long totalSize) {
				Log.d("PutObject", "currentSize: " + currentSize
						+ " totalSize: " + totalSize);
			}
		});

		OSSAsyncTask task = oss.asyncPutObject(put,
				new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
					@Override
					public void onSuccess(PutObjectRequest request,
							PutObjectResult result) {
						Log.d("PutObject", "UploadSuccess");

						Log.d("ETag", result.getETag());
						Log.d("RequestId", result.getRequestId());
					}

					@Override
					public void onFailure(PutObjectRequest request,
							ClientException clientExcepion,
							ServiceException serviceException) {
						// 请求异常
						if (clientExcepion != null) {
							// 本地异常如网络异常等
							clientExcepion.printStackTrace();
						}
						if (serviceException != null) {
							// 服务异常
							Log.e("ErrorCode", serviceException.getErrorCode());
							Log.e("RequestId", serviceException.getRequestId());
							Log.e("HostId", serviceException.getHostId());
							Log.e("RawMessage",
									serviceException.getRawMessage());
						}
					}
				});
	}

	// 追加文件
	public void appendObject() {
		// 如果bucket中objectKey存在，将其删除
		try {
			DeleteObjectRequest delete = new DeleteObjectRequest(bucketName,
					objectKey);
			DeleteObjectResult result = oss.deleteObject(delete);
		} catch (ClientException clientException) {
			clientException.printStackTrace();
		} catch (ServiceException serviceException) {
			Log.e("ErrorCode", serviceException.getErrorCode());
			Log.e("RequestId", serviceException.getRequestId());
			Log.e("HostId", serviceException.getHostId());
			Log.e("RawMessage", serviceException.getRawMessage());
		}
		AppendObjectRequest append = new AppendObjectRequest(bucketName,
				objectKey, uploadFilePath);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/octet-stream");
		append.setMetadata(metadata);

		// 设置追加位置，只能从文件末尾开始追加，如果是新文件，从0开始
		append.setPosition(0);

		append.setProgressCallback(new OSSProgressCallback<AppendObjectRequest>() {
			@Override
			public void onProgress(AppendObjectRequest request,
					long currentSize, long totalSize) {
				Log.d("AppendObject", "currentSize: " + currentSize
						+ " totalSize: " + totalSize);
			}
		});

		OSSAsyncTask task = oss
				.asyncAppendObject(
						append,
						new OSSCompletedCallback<AppendObjectRequest, AppendObjectResult>() {
							@Override
							public void onSuccess(AppendObjectRequest request,
									AppendObjectResult result) {
								Log.d("AppendObject", "AppendSuccess");
								Log.d("NextPosition",
										"" + result.getNextPosition());
							}

							@Override
							public void onFailure(AppendObjectRequest request,
									ClientException clientExcepion,
									ServiceException serviceException) {
								// 请求异常
								if (clientExcepion != null) {
									// 本地异常如网络异常等
									clientExcepion.printStackTrace();
								}
								if (serviceException != null) {
									// 服务异常
									Log.e("ErrorCode",
											serviceException.getErrorCode());
									Log.e("RequestId",
											serviceException.getRequestId());
									Log.e("HostId",
											serviceException.getHostId());
									Log.e("RawMessage",
											serviceException.getRawMessage());
								}
							}
						});
	}

}
