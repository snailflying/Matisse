package com.theone.matisse.compress;

import com.theone.matisse.internal.entity.ResultMedia;

import java.util.List;

/**
 * @Author zhiqiang
 * @Date 2019-05-31
 * @Email liuzhiqiang@moretickets.com
 * @Description
 */
public interface CompressStrategy {
    /**
     * 压缩
     *
     * @param mediaList        原始数据
     * @param compressListener 压缩成功的回调
     */
    void compress(List<ResultMedia> mediaList, OnCompressedListener compressListener);

}
