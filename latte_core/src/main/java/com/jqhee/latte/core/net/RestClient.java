package com.jqhee.latte.core.net;

import android.content.Context;

import com.jqhee.latte.core.net.callback.IError;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.net.callback.RestRequestCallbacks;
import com.jqhee.latte.core.net.download.DownloadHandler;
import com.jqhee.latte.core.ui.loader.LatteLoader;
import com.jqhee.latte.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    /// 文件下载
    // 文件目录
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      String download_dir,
                      String extension,
                      String name,
                      IRequest irequest,
                      ISuccess isuccess,
                      IFailure ifailure,
                      IError ierror,
                      RequestBody body,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context) {
        URL = url;
        PARAMS.putAll(params);
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
        LOADER_STYLE = loaderStyle;
        CONTEXT = context;
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        BODY = body;
        FILE = file;
    }

    // 发起网络请求参数配置
    public static RestClientBuilder builder() {
        return  new RestClientBuilder();
    }

    // 具体的网络请求
    private  void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        // 防止报空指针错误
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }

        // 显示加载指示器
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                // 表单的方式提交
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;

            case UPLOAD_FILES:
                // 待测试
                Map<String, MultipartBody.Part> paramsMap = new HashMap<>();
                for (Map.Entry<String, Object> entry: PARAMS.entrySet()
                     ) {
                    if (entry.getValue() instanceof File) {
                        final RequestBody trequestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), ((File)entry.getValue()));
                        // 表单的方式提交
                        final MultipartBody.Part tbody = MultipartBody.Part.createFormData(entry.getKey(), ((File)entry.getValue()).getName(), trequestBody);
                        paramsMap.put(entry.getKey(), tbody);
                    } else {
                        final MultipartBody.Part tbody = MultipartBody.Part.createFormData(entry.getKey(), entry.getKey());
                        paramsMap.put(entry.getKey(), tbody);
                    }
                }
                call = service.upLoadFiles(URL, paramsMap);
                break;
            default:
                break;
        }

        if (call != null) {
            // 在主线程执行，不推荐使用
            // call.execute();
            // 另起一条线程执行
            call.enqueue(getRequestCallback());
        }

    }

    // 设置网络请求回调接口
    private Callback<String> getRequestCallback() {
        return  new RestRequestCallbacks(
                IREQUEST,
                ISUCCESS,
                IFAILURE,
                IERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY != null) {
            request(HttpMethod.POST_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw  new RuntimeException("params is not be null");
            }
            request(HttpMethod.POST);
        }

    }

    public final void put() {

        if (BODY != null) {
            request(HttpMethod.PUT_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw  new RuntimeException("params is not be null");
            }
            request(HttpMethod.PUT);
        }

    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void uploadFiles() {
        request(HttpMethod.UPLOAD_FILES);
    }

    public final void download() {
        new DownloadHandler(URL, IREQUEST,
                DOWNLOAD_DIR, EXTENSION, NAME,
                ISUCCESS, IFAILURE, IERROR).handleDownload();
    }
}
