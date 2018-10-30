package lib.rocigno.usefulthingslib.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Funções destinadas ao teclado
 */
public class KeyboardUtils {

    public static void hideKeyboard(View view, Context ctx){
        InputMethodManager ipm = (InputMethodManager) ctx.getSystemService(Activity.INPUT_METHOD_SERVICE);
        ipm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
