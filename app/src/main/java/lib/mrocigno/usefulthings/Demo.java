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

package lib.mrocigno.usefulthings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lib.rocigno.usefulthingslib.CustomViews.Views.AutoValidateLinearLayout;
import lib.rocigno.usefulthingslib.CustomViews.Views.Parallax;

public class Demo extends Parallax {

    AutoValidateLinearLayout avllMain_demo;
    Button btnValidar_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_demo);

        initVars();
        initActions();
    }

    private void initVars() {
        avllMain_demo = findViewById(R.id.avllMain_demo);
        btnValidar_demo = findViewById(R.id.btnValidar_demo);
    }

    private void initActions() {
        btnValidar_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avllMain_demo.isValidate();
            }
        });
    }
}




















