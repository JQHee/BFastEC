package com.jqhee.latte.core.net.body;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {

    private ResponseBody responseBody;

    private ProgressListener progressListener;

    private BufferedSource bufferedSource;


    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener){

        this.responseBody=responseBody;

        this.progressListener=progressListener;
    }


    @Override
    public MediaType contentType() {

        return responseBody.contentType();
    }

    @Override
    public long contentLength() {

        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {

        if(bufferedSource==null){

            bufferedSource= Okio.buffer(source(responseBody.source()));
        }

        return bufferedSource;
    }


    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                //当前读取字节数
                long bytesRead = super.read(sink, byteCount);
                //增加当前读取的字节数，如果读取完成了bytesRead会返回-1
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                //回调，如果contentLength()不知道长度，会返回-1

                progressListener.onProgress(totalBytesRead,responseBody.contentLength(),bytesRead,bytesRead==-1);
                return bytesRead;
            }
        };
    }
}
