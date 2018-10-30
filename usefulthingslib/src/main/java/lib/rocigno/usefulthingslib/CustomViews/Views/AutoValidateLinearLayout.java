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

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AutoValidateLinearLayout extends LinearLayout {
    public AutoValidateLinearLayout(Context context) {
        super(context);
    }

    public AutoValidateLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoValidateLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isValidate(){
        int count = getChildCount();
        ArrayList<Boolean> booleans = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);

            if(view instanceof AutoValidateEditText){
                AutoValidateEditText medt = (AutoValidateEditText) view;
                booleans.add(medt.isValidated());
            }else

            if(view instanceof AutoValidateLinearLayout){
                AutoValidateLinearLayout avll = (AutoValidateLinearLayout) view;
                booleans.add(avll.isValidate());
            }else

            if(view instanceof TextInputLayout){
                TextInputLayout til = (TextInputLayout) view;
                FrameLayout frm = (FrameLayout) til.getChildAt(0);
                try{
                    AutoValidateEditText avet = (AutoValidateEditText) frm.getChildAt(0);
                    booleans.add(avet.isValidated());
                }catch (Exception ignore){}
            }
        }

        for (int i = 0; i < booleans.size(); i++) {
            if(!booleans.get(i)){
                return false;
            }
        }
        return true;
    }
}
