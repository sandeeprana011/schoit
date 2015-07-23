package rana.schoit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class CardBackFragmentSearchTutor extends Fragment {
    public CardBackFragmentSearchTutor() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.search_tutor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ch_st_curloc);
        final Button button = (Button) view.findViewById(R.id.but_st_pickplace);

        // subject array spinner initialization
        Spinner spinner_subject = (Spinner) view.findViewById(R.id.spin_subject);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.array_subject, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_subject.setAdapter(arrayAdapter);

        // Class array spinner initialization
        Spinner spinner_class = (Spinner) view.findViewById(R.id.spin_class);
        ArrayAdapter<CharSequence> arrayAdapterClass = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.array_class, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(arrayAdapterClass);


        //switch b/w pick place and use my current location
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }
        });
    }


}