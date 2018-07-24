package com.leoky.queueeu.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leoky.queueeu.Activity.ChangeEmail;
import com.leoky.queueeu.Activity.ChangePassword;
import com.leoky.queueeu.Activity.ChangePhone;
import com.leoky.queueeu.Activity.LoginActivity;
import com.leoky.queueeu.Activity.MainActivity;
import com.leoky.queueeu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment {

    private TextView tvName, tvEmail,tvDate,tvGender,tvCName,tvTime,tvPassword,tvPhone;
    private ImageView img;
    private Button btnLogout;

    public static SettingFrag newInstance() {
        // Required empty public constructor

        Bundle args = new Bundle();

        SettingFrag fragment = new SettingFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(R.string.settings);

        tvName =v.findViewById(R.id.tvName);
        tvEmail =v.findViewById(R.id.tvEmail);
        tvGender =v.findViewById(R.id.tvGender);
        tvPassword=v.findViewById(R.id.tvPassword);
        tvPhone =v.findViewById(R.id.tvPhone);
        img = v.findViewById(R.id.imgNow);
        btnLogout = v.findViewById(R.id.btn_logout);


        updateData();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Log Out")
                        .setMessage("Are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.sp.clearSp();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(),LoginActivity.class));

                                dialog.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }).show();

            }
        });

        v.findViewById(R.id.llEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), ChangeEmail.class));
            }
        });
        v.findViewById(R.id.llPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });
        v.findViewById(R.id.llPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePhone.class));
            }
        });

        return v;
    }

    private void updateData() {
        if (MainActivity.sp != null) {
            tvName.setText(MainActivity.sp.getSpName());
            tvEmail.setText(MainActivity.sp.getSpEmail());
            tvPhone.setText(MainActivity.sp.getSpPhone());
            tvGender.setText(MainActivity.sp.getSpGender());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }
}
