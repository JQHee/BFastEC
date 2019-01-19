package com.jqhee.latte.core.net.body;

public interface ProgressListener {

    void onProgress(long progress, long total, long speed, boolean done);
}
