package com.zx.cnn;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

public class Alg
{
    public native boolean Init(AssetManager manager);

    public native float[] Run(Bitmap bitmap);

    public native void Fini();

    static {
        System.loadLibrary("alg");
    }
}