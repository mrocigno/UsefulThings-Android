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


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import lib.rocigno.usefulthingslib.R;

@SuppressLint("AppCompatCustomView")
public class AutoValidateEditText extends EditText {


    public AutoValidateEditText(@NonNull Context context) {
        super(context);
        initAttrs(null);
    }

    public AutoValidateEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public AutoValidateEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    String mask = null;
    boolean cpf = false;
    int compare_with = 0;
    String error_msg = null;
    boolean obrigatory = false;
    int min_length = -1;

    public void initAttrs(AttributeSet attrs){

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AutoValidateEditText);
        mask = ta.getString(R.styleable.AutoValidateEditText_mask);
        error_msg = ta.getString(R.styleable.AutoValidateEditText_error_msg);
        compare_with = ta.getResourceId(R.styleable.AutoValidateEditText_compare_with, 0);
        obrigatory = ta.getBoolean(R.styleable.AutoValidateEditText_obrigatory_field, false);

        if (ta.getBoolean(R.styleable.AutoValidateEditText_validate_cpf, false)){
            mask = "###.###.###-##";
            cpf = true;
        }

        min_length = (mask == null? ta.getInt(R.styleable.AutoValidateEditText_min_length, -1) : mask.length());

        this.addTextChangedListener(new TextWatcher() {
            int oldL = 0; boolean anti_repeat = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence se, int i0, int i1, int i2) {
                if(mask != null){
                    String str = unmask();
                    if(anti_repeat){
                        anti_repeat = false;
                        return;
                    }
                    int count = str.length();

                    char[] chars = mask.toCharArray();
                    String result = "";
                    int dif = 0;
                    for (int i = 0; i < count; i++) {
                        try {
                            if(chars[i + dif] == '#'){
                                result += str.charAt(i);
                            }else{
                                if (count > oldL){
                                    while (chars[i + dif] != '#'){
                                        result += String.valueOf(chars[i + dif]);
                                        dif++;
                                    }
                                    result += str.charAt(i);
                                }else{
                                    if(i == (count - 1) && chars[i + dif + 1] != '#'){
                                        result += str.charAt(i);
                                    }else{
                                        while (chars[i + dif] != '#'){
                                            result += String.valueOf(chars[i + dif]);
                                            dif++;
                                        }
                                        result += str.charAt(i);
                                    }
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            break;
                        }
                    }
//                masked_txtText.setText(result);
                    anti_repeat = true;
                    oldL = unmask().length();
                    AutoValidateEditText.this.setText(result);
                    AutoValidateEditText.this.setSelection(result.length());
                    if(cpf && result.length() == 14){
                        VerificarCPF(result);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String text = AutoValidateEditText.this.getText().toString().trim();
                if(!hasFocus){
                    if(min_length != -1){
                        if(!valideMinLength()){
                            return;
                        }
                    }

                    if(cpf){
                        if(!VerificarCPF(text)){
                            return;
                        }
                    }

                    if(compare_with != 0){
                        if(!compare(text)){
                            return;
                        }
                    }
                }
            }
        });
    }

    public boolean valideMinLength(){
        String text = AutoValidateEditText.this.getText().toString().trim();
        if(min_length > text.length() && !text.equals("")){
            this.setError("Mínimo de " + min_length + " caracteres");
            return false;
        }
        return true;
    }

    public String unmask(){
        return this.getText().toString().trim()
                    .replaceAll("[.]", "")
                    .replaceAll("[-]", "")
                    .replaceAll("[/]", "")
                    .replaceAll("[(]", "")
                    .replaceAll("[ ]", "")
                    .replaceAll("[:]", "")
                    .replaceAll("[)]", "");
    }

    public boolean VerificarCPF(String CPF){
        if(CPF.equals("")){return true;}
        try{
            String get = CPF.replace(".", "");
            String digito = get.split("-")[1];
            get = get.split("-")[0];

            int valuedgt1 = 0; int multiplier = 10;
            for(int x = 0; x < get.length(); x++){
                int result = Integer.parseInt(get.substring(x, x+1));
                valuedgt1 += result*multiplier;
                multiplier--;
            }
            valuedgt1 = ((11 - (valuedgt1%11)) > 9? 0: (11 - (valuedgt1%11)));

            get += valuedgt1;

            int valuedgt2 = 0; multiplier = 11;
            for(int x = 0; x < get.length(); x++){
                int result = Integer.parseInt(get.substring(x, x+1));
                valuedgt2 += result*multiplier;
                multiplier--;
            }
            valuedgt2 = ((11 - (valuedgt2%11)) > 9? 0: (11 - (valuedgt2%11)));

            String valueFinal = (String.valueOf(valuedgt1) + String.valueOf(valuedgt2));
            if(valueFinal.equals(digito)){
                return true;
            }else{
                AutoValidateEditText.this.setError(error_msg == null? "CPF Invalido":error_msg);
                return false;
            }
        }catch(Exception e){
            AutoValidateEditText.this.setError(error_msg == null? "CPF Invalido":error_msg);
            return false;
        }
    }

    private boolean compare(String mtext){
        EditText edt = ((Activity) getContext()).findViewById(compare_with);
        String ctext = edt.getText().toString().trim();

        if(!ctext.equals(mtext) && !ctext.equals("")){
            String cname = (String) (edt.getHint() == null? "null":edt.getHint());
            String mname = (String) (getHint() == null? "null":getHint());

            edt.setError(error_msg == null? "Deve ser igual ao campo " + mname:error_msg);
            AutoValidateEditText.this.setError(error_msg == null? "Deve ser igual ao campo " + cname:error_msg);
            return false;
        }else{
            AutoValidateEditText.this.setError(null);
            edt.setError(null);
        }
        return true;
    }

    public boolean isValidated(){
        if(obrigatory){
            if(this.getText().toString().trim().equals("")){
                AutoValidateEditText.this.setError(error_msg == null? "Este campo é obrigatório":error_msg);
                return false;
            }
        }
        if(min_length != -1){
            if(!valideMinLength()){
                return false;
            }
        }
        if(cpf){
            if(!VerificarCPF(AutoValidateEditText.this.getText().toString().trim())){
                return false;
            }

        }
        if(compare_with != 0){
            if(!compare(AutoValidateEditText.this.getText().toString().trim())){
                return false;
            }
        }

        return true;
    }

}
