package com.example.tabbedactivities.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabbedactivities.PopUpClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tabbedactivities.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private Context mContext;
    private Button view_Course;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        view_Course = root.findViewById(R.id.view_course_current_status);
        final TextView textView = root.findViewById(R.id.text_home);
        view_Course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "View Courses Now", Toast.LENGTH_SHORT).show();
                PopUpClass p = new PopUpClass();
                p.showPopupWindow(v);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.view_course_current_status:
//                Toast.makeText(getContext(), "View Courses Now", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    public void onButtonShowPopupWindowClick(View view) {
//
//        // inflate the layout of the popup window
//        View popupView = inflater.inflate(R.layout.popup_courses, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }
}
