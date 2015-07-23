package rana.schoit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by sirfira on 11/7/15.
 */
public class CardBackFragmentSearchSchool extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.search_school, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ch_ss);
        final Button button = (Button) view.findViewById(R.id.choose_place);

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
