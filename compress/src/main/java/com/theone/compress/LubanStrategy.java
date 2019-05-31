package com.theone.compress;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.theone.matisse.compress.CompressStrategy;
import com.theone.matisse.compress.OnCompressedListener;
import com.theone.matisse.internal.entity.ResultMedia;
import com.theone.matisse.internal.utils.PathUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;

/**
 * @Author zhiqiang
 * @Date 2019-05-31
 * @Email liuzhiqiang@moretickets.com
 * @Description
 */
public class LubanStrategy implements CompressStrategy {

    private static final String TAG = "LubanStrategy";

    private Context context;

    public LubanStrategy(Context context) {
        this.context = context;
    }

    @Override
    public void compress(List<ResultMedia> mediaList, OnCompressedListener compressListener) {
        ArrayList<String> photos = new ArrayList<>();
        for (ResultMedia resultMedia : mediaList) {
            photos.add(resultMedia.getPath());
        }
        new SimpleAsyncTask(context, photos, compressListener).execute();
    }

    private static class SimpleAsyncTask extends AsyncTask<Void, Integer, List<File>> {

        WeakReference<Context> contextWeakReference;
        ArrayList<String> photos;
        OnCompressedListener compressListener;

        SimpleAsyncTask(Context context, ArrayList<String> items, OnCompressedListener compressListener) {
            photos = items;
            this.compressListener = compressListener;
            contextWeakReference = new WeakReference<>(context);

        }


        @Override
        protected List<File> doInBackground(Void... uris) {
            if (contextWeakReference == null) {
                return null;
            }
            try {
                Log.e(TAG, "aaron photos size:" + photos.size());
                return Luban.with(contextWeakReference.get()).load(photos).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<File> files) {
            if (contextWeakReference == null) {
                return;
            }
            ArrayList<ResultMedia> resultMedias = new ArrayList<>();
            ResultMedia media;
            for (File file : files) {
                media = new ResultMedia();
                media.setUri(PathUtils.getUriFromPath(file.getAbsolutePath()));
                media.setPath(file.getAbsolutePath());
                resultMedias.add(media);
                Log.e(TAG, "aaron file size:" + file.length()+ "file path:"+file.getAbsolutePath());
            }
            compressListener.onSuccess(resultMedias);
        }
    }

}

