package com.example.tabbedactivities.ui.logout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tabbedactivities.R;
import com.example.tabbedactivities.LoggedIn;
import com.example.tabbedactivities.ui.home.HomeFragment;

public class Logout extends Fragment {

    public interface finishActivity{
        void destroyActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        Button yes = (Button) root.findViewById(R.id.yes_logout);
        yes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout Yes Pressed", Toast.LENGTH_SHORT).show();
            }
        });
        Button no = (Button) root.findViewById(R.id.no_logout);
        no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout No Pressed", Toast.LENGTH_SHORT).show();
                Fragment frag = new HomeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(context, "Logout Fragment Attached", Toast.LENGTH_SHORT).show();
    }
}
