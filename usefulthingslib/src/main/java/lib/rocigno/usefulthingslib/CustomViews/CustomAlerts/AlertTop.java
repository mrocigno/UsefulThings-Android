package lib.rocigno.usefulthingslib.CustomViews.CustomAlerts;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.ViewHolder;

import lib.rocigno.usefulthingslib.R;
import lib.rocigno.usefulthingslib.Utils.KeyboardUtils;


public class AlertTop {
    public static void CustomTopSimpleAlert(@NonNull final Activity activity, @NonNull String msg, int img, @NonNull final int duration){

        ViewHolder holder = new ViewHolder(R.layout.alert_base);
        final DialogPlus alert = DialogPlus.newDialog(activity)
                .setContentHolder(holder)
                .setGravity(Gravity.TOP)
                .create();

        ((TextView)holder.getInflatedView().findViewById(R.id.alert_msg)).setText(msg);
        ((ImageView)holder.getInflatedView().findViewById(R.id.alert_img)).setImageDrawable(activity.getDrawable(img));

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(holder.getInflatedView().findViewById(R.id.alert_pbh), "progress", 100, 0);
        progressAnimator.setDuration(duration);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        alert.show();

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(duration);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            alert.dismiss();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public interface YesNoCallBack{
        void onClickYes();
        void onClickNo();
    }

    public static void CustomYesNoTopAlert(Activity activity, String title, String msg, int img, final YesNoCallBack yesNoCallBack){
        ViewHolder holder = new ViewHolder(R.layout.alert_yes_no_option);
        final DialogPlus alert = DialogPlus.newDialog(activity)
                .setContentHolder(holder)
                .setGravity(Gravity.TOP)
                .create();

        ((TextView) holder.getInflatedView().findViewById(R.id.alertYesNo_txtTle)).setText(title);
        ((TextView) holder.getInflatedView().findViewById(R.id.alertYesNo_txtCdo)).setText(msg);
        ((ImageView)holder.getInflatedView().findViewById(R.id.alertYesNo_imgIew)).setImageDrawable(activity.getDrawable(img));
        (holder.getInflatedView().findViewById(R.id.alertYesNo_btnYes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                yesNoCallBack.onClickYes();
            }
        });
        (holder.getInflatedView().findViewById(R.id.alertYesNo_btnNo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                yesNoCallBack.onClickNo();
            }
        });

        alert.show();
    }

    public interface InputOk{
        void onClickOk(String value);
    }

    public static void CustomInputTopAlert(final Activity activity, String title, int img, final InputOk inputOk){
        final ViewHolder holder = new ViewHolder(R.layout.alert_input);
        final EditText edtInput;
        final DialogPlus alert = DialogPlus.newDialog(activity)
                .setContentHolder(holder)
                .setGravity(Gravity.TOP)
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogPlus dialog) {
                        KeyboardUtils.hideKeyboard(new View(activity), activity);
                    }
                })
                .create();

        edtInput = holder.getInflatedView().findViewById(R.id.alertInput_edtInput);
        ((TextView) holder.getInflatedView().findViewById(R.id.alertInput_txtTle)).setText(title);
        ((ImageView)holder.getInflatedView().findViewById(R.id.alertInput_imgIew)).setImageDrawable(activity.getDrawable(img));
        (holder.getInflatedView().findViewById(R.id.alertInput_btnOk)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                KeyboardUtils.hideKeyboard(edtInput, activity);
                inputOk.onClickOk(edtInput.getText().toString().trim());
            }
        });
        edtInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                alert.dismiss();
                KeyboardUtils.hideKeyboard(edtInput, activity);
                inputOk.onClickOk(edtInput.getText().toString().trim());
                return false;
            }
        });
        (holder.getInflatedView().findViewById(R.id.alertInput_btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                KeyboardUtils.hideKeyboard(edtInput, activity);
            }
        });

        alert.show();
    }

}
