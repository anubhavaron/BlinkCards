package com.addy1397.blinkcards;


import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    final String[] subect={"MATHEMATICS","CHEMISTRY","PHYSICS","COMPUTER SCIENCE"};
    boolean[] checkedItems;
    ArrayList<Integer> itemsSelecected = new ArrayList<>();

    private ImageButton imageButton_chooseSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageButton_chooseSubject = (ImageButton) view.findViewById(R.id.imageButton_chooseSubject);
        checkedItems = new boolean[subect.length];

        imageButton_chooseSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("CHOOSE SUBJECTS");
                mBuilder.setMultiChoiceItems(subect, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked) {
                            if(!itemsSelecected.contains(position)){
                                itemsSelecected.add(position);
                            }
                        }
                        else{
                            itemsSelecected.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         String S="";
                         for(int i=0;i<itemsSelecected.size();i++){
                             S+=subect[itemsSelecected.get(i)];
                         }
                        Toast.makeText(getActivity(), S, Toast.LENGTH_SHORT).show();
                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String S="";
                        for(int i=0;i<itemsSelecected.size();i++){
                            S+=subect[itemsSelecected.get(i)];
                        }
                        Toast.makeText(getActivity(), S, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        return view;
    }
}
