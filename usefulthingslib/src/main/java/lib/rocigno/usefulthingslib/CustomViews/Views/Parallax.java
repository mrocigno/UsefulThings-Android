/*
 * Copyright (c) 2018. Created by mrocigno
 *                                     _
 *                                    /_\
 *                       _            )_(            _
 *                       |`-.___,.-~'`|=|`'~-.,___,-'|
 *                       |  __________|=|__________  |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |        ,-|_|-.        | |
 *                       | |      ,' _____ ',      | |
 *                       | |     / ,'| A |'. \     | |
 *                       | |    /_// |/V\| \\_\    | |
 *            ___________|_|_________|___|_________|_|__________
 *           |             ____            _                    |
 *           |   _ __ ___ |  _ \ ___   ___(_) __ _ _ __   ___   |
 *           |  | '_ ` _ \| |_) / _ \ / __| |/ _` | '_ \ / _ \  |
 *           |  | | | | | |  _ < (_) | (__| | (_| | | | | (_) | |
 *           |  |_| |_| |_|_| \_\___/ \___|_|\__, |_| |_|\___/  |
 *           |                                |___/             |
 *           '--------------------------------------------------'
 *                       \ \         | | |         / /
 *                        \ \        | | |        / /
 *                         \ \       | | |       / /
 *                          `.`.     | | |     ,','
 *                            `.`.   | | |   ,','
 *                              `.`-.| | |,-','
 *                                `-.| | |,-'
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                    \|/
 *                                     V
 */

package lib.rocigno.usefulthingslib.CustomViews.Views;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import lib.rocigno.usefulthingslib.R;
import lib.rocigno.usefulthingslib.Utils.GlideUtil;

public class Parallax extends AppCompatActivity {

    FrameLayout frmContainer_parallax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frmContainer_parallax = findViewById(R.id.frmContainer_parallax);

        final ImageView img0 = findViewById(R.id.img0);
        final ImageView img1 = findViewById(R.id.img1);
        final ImageView img2 = findViewById(R.id.img2);
        final ImageView img3 = findViewById(R.id.img3);
        final ImageView img4 = findViewById(R.id.img4);
        final ImageView img5 = findViewById(R.id.img5);
        final ImageView img6 = findViewById(R.id.img6);
        final ImageView img7 = findViewById(R.id.img7);
        final ImageView img8 = findViewById(R.id.img8);

        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax0.png", img0);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax1.png", img1);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax2.png", img2);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax3.png", img3);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax4.png", img4);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax5.png", img5);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax6.png", img6);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax7.png", img7);
        GlideUtil.initGlide(Parallax.this, "http://www.firewatchgame.com/images/parallax/parallax8.png", img8);

        NestedScrollView nested = findViewById(R.id.nested);
        nested.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {

                img0.setTop(i1);
                img1.setTop((int) (i1 * 0.9));
                img2.setTop((int) (i1 * 0.8));
                img3.setTop((int) (i1 * 0.7));
                img4.setTop((int) (i1 * 0.6));
                img5.setTop((int) (i1 * 0.5));
                img6.setTop((int) (i1 * 0.4));
                img7.setTop((int) (i1 * 0.3));

            }
        });
    }

    public void setContainerView(int layout){
        View view = getLayoutInflater().inflate(layout, null);
        frmContainer_parallax.addView(view);
    }

}
