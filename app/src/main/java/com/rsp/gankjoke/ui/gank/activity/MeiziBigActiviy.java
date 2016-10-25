package com.rsp.gankjoke.ui.gank.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.rsp.gankjoke.App;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.di.component.DaggerMainComponent;
import com.rsp.gankjoke.ui.base.BaseControlActivity;
import com.rsp.gankjoke.util.ACache;
import com.rsp.gankjoke.util.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MeiziBigActiviy extends BaseControlActivity {
    public static final String IMG_PATH = "img";

    @BindView(R.id.img)
    ImageView img;
    private String url;
    @Inject
    public ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        String isShow = aCache.getAsString(Constant.IS_SHOW_PROMPT);
        if (TextUtils.isEmpty(isShow)) {
            Snackbar.make(img,"长按图片可以保存到相册", Snackbar.LENGTH_LONG).setAction("不再提示", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aCache.put(Constant.IS_SHOW_PROMPT, "true");
                }
            }).show();
        }
        url = getIntent().getStringExtra(IMG_PATH);
        Glide.with(this).load(url).into(img);
        //ImageLoader.getInstance().displayImage(url,img);
    }

    @OnClick(R.id.img)
    public void onClick(View v) {
       /* Intent intent = new Intent(this, CoordinatorLayoutActivity.class);
        startActivity(intent);*/
        finishAfterTransition();
    }

    @OnLongClick(R.id.img)
    public boolean saveImg(View v) {
         File dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
     final File dcimDir = new File(dcim,"meizi");
        if (!dcimDir.exists()) {
            dcimDir.mkdirs();

        }
        Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                File meizi = new File(dcimDir, System.currentTimeMillis()+".jpg");
                try {
                    if (!meizi.exists()) {
                        meizi.createNewFile();
                    }
                    FileInputStream fis =
                            new FileInputStream(resource);
                    FileOutputStream fos = new FileOutputStream(meizi);
                    int len = 0;
                    byte[] buffer = new byte[1024 * 7];
                    while ((len = fis.read(buffer)) != -1) {
                        fos.write(buffer,0,len);
                    }
                    fis.close();
                    fos.close();
                    Toast.makeText(MeiziBigActiviy.this, "保存成功:" + meizi.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /* if (resource.renameTo(meizi)) {
                    Toast.makeText(MeiziBigActiviy.this, "保存成功:" + meizi.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MeiziBigActiviy.this, "保存失败", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
        return true;
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_meizi_big_activiy;
    }

}
