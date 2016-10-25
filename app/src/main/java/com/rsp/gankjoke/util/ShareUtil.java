package com.rsp.gankjoke.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by codeest on 2016/8/22.
 */
public class ShareUtil {


    public static void shareText(Context context,String text,String title){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        context.startActivity(Intent.createChooser(intent,title));
    }

    public static void shareImage(Context context, Uri uri, String title){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent,title));
    }


}
