package com.theone.matisse.compress;

import com.theone.matisse.internal.entity.ResultMedia;

import java.util.ArrayList;

public interface OnCompressedListener {

    /**
     * Fired when the compression is started, override to handle in your own code
     */
    void onStart();

    /**
     * Fired when a compression returns successfully, override to handle in your own code
     */
    void onSuccess(ArrayList<ResultMedia> results);

    /**
     * Fired when a compression fails to complete, override to handle in your own code
     */
    void onError(Throwable e);
}