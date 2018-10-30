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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import lib.rocigno.photopicker.PhotoPicker;
import lib.rocigno.usefulthingslib.CustomViews.CustomAlerts.AlertTop;
import lib.rocigno.usefulthingslib.CustomViews.Views.AutoValidateLinearLayout;
import lib.rocigno.usefulthingslib.CustomViews.Views.Parallax;

public class Demo extends Parallax {

    Activity activity;

    AutoValidateLinearLayout avllMain_demo;
    Button btnValidar_demo, btnShowSimpleAlertTop_demo, btnShowYesNoAlertTop_demo, btnShowInputAlertTop_demo;

    PhotoPicker pprFotos_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_demo);

        activity = Demo.this;

        initVars();
        initActions();
    }

    private void initVars() {
        avllMain_demo = findViewById(R.id.avllMain_demo);
        btnValidar_demo = findViewById(R.id.btnValidar_demo);
        btnShowSimpleAlertTop_demo = findViewById(R.id.btnShowSimpleAlertTop_demo);
        btnShowYesNoAlertTop_demo = findViewById(R.id.btnShowYesNoAlertTop_demo);
        btnShowInputAlertTop_demo = findViewById(R.id.btnShowInputAlertTop_demo);
        pprFotos_demo = findViewById(R.id.pprFotos_demo);
    }

    private void initActions() {
        btnValidar_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avllMain_demo.isValidate();
            }
        });
        btnShowSimpleAlertTop_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertTop.CustomTopSimpleAlert(activity, "Alert top", R.drawable.ic_warning, 4000);
            }
        });

        btnShowYesNoAlertTop_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertTop.CustomYesNoTopAlert(activity, "Titulo", "Mensagem", R.drawable.ic_warning, new AlertTop.YesNoCallBack() {
                    @Override
                    public void onClickYes() {
                        Toast.makeText(activity, "Clicou sim", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onClickNo() {
                        Toast.makeText(activity, "Clicou não", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnShowInputAlertTop_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertTop.CustomInputTopAlert(activity, "Titulo", R.drawable.ic_warning, new AlertTop.InputOk() {
                    @Override
                    public void onClickOk(String value) {
                        Toast.makeText(activity, "Digitou: " + value, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pprFotos_demo.result(requestCode,resultCode,data);
    }
}




















