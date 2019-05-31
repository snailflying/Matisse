package com.theone.matisse.compress;

import android.app.Activity;
import android.content.Intent;

import com.theone.matisse.internal.entity.ResultMedia;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.theone.matisse.ui.MatisseActivity.EXTRA_RESULT_MEDIA;

/**
 * @Author zhiqiang
 * @Date 2019-05-31
 * @Email liuzhiqiang@moretickets.com
 * @Description
 */
public class SimpleCompressedListener implements OnCompressedListener {
    private final WeakReference<Activity> mActivity;

    public SimpleCompressedListener(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(ArrayList<ResultMedia> results) {
        if (mActivity.get() == null) {
            return;
        }
        Intent result = new Intent();
        result.putParcelableArrayListExtra(EXTRA_RESULT_MEDIA, results);

        mActivity.get().setResult(RESULT_OK, result);
        mActivity.get().finish();
    }

    @Override
    public void onError(Throwable e) {

    }
}
