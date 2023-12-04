package com.myapps.getcall_smsinfoanynumberthree.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import com.myapps.getcall_smsinfoanynumberthree.R;

public class CustomLoadingDialog {
    private Context context;
    private Dialog dialog;

    public CustomLoadingDialog(Context context) {
        this.context = context;
        this.dialog = null;
    }

    public void showLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        android.view.View view = inflater.inflate(R.layout.layout_loading_dialog, null);
        builder.setView(view);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Customize the loading dialog layout here if needed

        dialog.show();
    }

    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
