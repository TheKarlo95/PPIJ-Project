package hr.lordsofsmell.parfume.feature.perfumelist.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import hr.lordsofsmell.parfume.R;

public class SearchDialog extends DialogFragment {

    private EditText etCompany;
    private EditText etModel;
    private EditText etYear;
    private CheckBox cbMale;
    private CheckBox cbFemale;
    private CheckBox cbUnisex;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_search, null);
        bind(view);

        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.search_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.search_dialog_positive_button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String company = getText(etCompany);
                                String model = getText(etModel);
                                String year = getText(etYear);
                                ArrayList<String> genders = getGenders();

                                PerfumeListActivity activity = (PerfumeListActivity) getActivity();
                                activity.search(company, model, year, genders);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(R.string.search_dialog_cancel_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .create();
    }

    private void bind(@NonNull View view) {
        etCompany = (EditText) view.findViewById(R.id.et_company);
        etModel = (EditText) view.findViewById(R.id.et_model);
        etYear = (EditText) view.findViewById(R.id.et_year);
        cbMale = (CheckBox) view.findViewById(R.id.cb_male);
        cbFemale = (CheckBox) view.findViewById(R.id.cb_female);
        cbUnisex = (CheckBox) view.findViewById(R.id.cb_unisex);
    }

    public ArrayList<String> getGenders() {
        ArrayList<String> genders = new ArrayList<>(3);
        if (cbMale.isChecked()) {
            genders.add(PerfumeListActivity.GENDER_MALE);
        }
        if (cbFemale.isChecked()) {
            genders.add(PerfumeListActivity.GENDER_FEMALE);
        }
        if (cbUnisex.isChecked()) {
            genders.add(PerfumeListActivity.GENDER_UNISEX);
        }
        return genders;
    }

    private static String getText(TextView textView) {
        String text = textView.getText().toString();

        return TextUtils.isEmpty(text) ? null : text;
    }
}
