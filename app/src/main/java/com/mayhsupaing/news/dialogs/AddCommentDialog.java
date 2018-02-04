package com.mayhsupaing.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mayhsupaing.news.R;

/**
 * Created by Lenovo on 2/4/2018.
 */

public class AddCommentDialog extends Dialog {
    public AddCommentDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_comments);
        setCancelable(false);
    }
}
