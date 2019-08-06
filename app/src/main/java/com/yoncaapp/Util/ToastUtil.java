package com.yoncaapp.Util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yoncaapp.R;


public class ToastUtil {
    public static String TOAST_WARNING = "toast_warning";
    public static String TOAST_ERROR = "toast_error";
    public static String TOAST_INFO = "toast_info";
    public static String TOAST_SUCCESS = "toast_success";


    public static void show(Activity activity, int message, String type){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message, (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        ImageView image = layout.findViewById(R.id.image);
        if(type.equals(TOAST_ERROR)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.errorColor));
        }else if(type.equals(TOAST_WARNING)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.warningColor));
        }else if(type.equals(TOAST_SUCCESS)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.successColor));
        }else{
            layout.setBackgroundColor(activity.getResources().getColor(R.color.infoColor));
        }

        TextView text = layout.findViewById(R.id.text);
        text.setText(activity.getResources().getString(message));

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void show(Activity activity, String message, String type){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message, (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        ImageView image = layout.findViewById(R.id.image);
        if(type.equals(TOAST_ERROR)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.errorColor));
        }else if(type.equals(TOAST_WARNING)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.warningColor));
        }else if(type.equals(TOAST_SUCCESS)){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.successColor));
        }else{
            layout.setBackgroundColor(activity.getResources().getColor(R.color.infoColor));
        }

        TextView text = layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
