package acme.com.acmecone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class CurbsFragment extends Fragment {

    private RadioGroup curbsGroup;
    private RadioButton curbsS, curbsN;
    private EditText getCurbsQ, getCurbsL, getCurbsW, getCurbsH, getCurbsF;
    private Spinner curbsLF, curbsWF, curbsHF, curbsFF;
    private Button curbsB;
    private EditText curbsNotes;
    private String curbRadioChoice = "";
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";

    public CurbsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.curbs_fragment, container, false);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Context context = getActivity().getApplicationContext();

        curbsGroup = (RadioGroup) view.findViewById(R.id.curbs_radioGroup);
        curbsS = (RadioButton) view.findViewById(R.id.curbs_split);
        curbsS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Split", Toast.LENGTH_SHORT).show();
                curbRadioChoice = "Split \n";
            }
        });

        curbsN = (RadioButton) view.findViewById(R.id.curbs_nonsplit);
        curbsN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Non-Split", Toast.LENGTH_SHORT).show();
                curbRadioChoice = "Non-Split \n";
            }
        });

        curbsNotes = (EditText) view.findViewById(R.id.curbs_notes);

        getCurbsQ = (EditText) view.findViewById(R.id.curbs_quantity);
        getCurbsL = (EditText) view.findViewById(R.id.curbs_length);
        getCurbsW = (EditText) view.findViewById(R.id.curbs_width);
        getCurbsH = (EditText) view.findViewById(R.id.curbs_height);
        getCurbsF = (EditText) view.findViewById(R.id.curbs_flange);

        curbsLF = (Spinner) view.findViewById(R.id.curbs_length_frac);
        ArrayAdapter<CharSequence> spinnerCurbsLF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        curbsLF.setAdapter(spinnerCurbsLF);

        curbsWF = (Spinner) view.findViewById(R.id.curbs_width_frac);
        ArrayAdapter<CharSequence> spinnerCurbsWF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        curbsWF.setAdapter(spinnerCurbsWF);

        curbsHF = (Spinner) view.findViewById(R.id.curbs_height_frac);
        ArrayAdapter<CharSequence> spinnerCurbsHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        curbsHF.setAdapter(spinnerCurbsHF);

        curbsFF = (Spinner) view.findViewById(R.id.curbs_flange_frac);
        ArrayAdapter<CharSequence> spinnerCurbsFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        curbsFF.setAdapter(spinnerCurbsFF);

        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView colors = (AutoCompleteTextView) view.findViewById(R.id.curbs_color);
        colors.setAdapter(adapterColor);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView materials = (AutoCompleteTextView) view.findViewById(R.id.curbs_material);
        materials.setAdapter(adapterMaterial);

        curbsB = (Button) view.findViewById(R.id.curbs_submitB);
        curbsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderCurbsQ = getCurbsQ.getText().toString();
                final String orderCurbsL = getCurbsL.getText().toString();
                final String orderCurbsLF = curbsLF.getSelectedItem().toString();
                final String orderCurbsW = getCurbsW.getText().toString();
                final String orderCurbsWF = curbsWF.getSelectedItem().toString();
                final String orderCurbsH = getCurbsH.getText().toString();
                final String orderCurbsHF = curbsHF.getSelectedItem().toString();
                final String orderCurbsF = getCurbsF.getText().toString();
                final String orderCurbsFF = curbsFF.getSelectedItem().toString();
                final String orderColors = colors.getText().toString().trim();
                final String orderMaterials = materials.getText().toString().trim();
                final String orderNotes = curbsNotes.getText().toString();

                if (!orderCurbsQ.isEmpty()
                        && !orderCurbsL.isEmpty()
                        && !orderCurbsW.isEmpty()
                        && !orderCurbsH.isEmpty()
                        && !orderColors.isEmpty()
                        && !orderMaterials.isEmpty()) {

                    mCustomReviewMessage += orderCurbsQ + ": " + orderColors + " " + orderMaterials
                            + "\n" + curbRadioChoice + " Custom Curbs"
                            + "\nLength: "  + orderCurbsL + " " + orderCurbsLF + "\""
                            + "\nWidth: "   + orderCurbsW + " " + orderCurbsWF + "\""
                            + "\nHeight: "  + orderCurbsH + " " + orderCurbsHF + "\""
                            + "\nFlange: "  + orderCurbsF + " " + orderCurbsFF + "\""
                            + "\n"
                            + "Curbs Notes: " + orderNotes
                            + "\n\n";

                    mCustomSendMessage += orderColors + " " + orderMaterials
                            + "\n"
                            + orderCurbsQ + ") "
                            + curbRadioChoice + " Custom Curbs "
                            + orderCurbsL + " " + orderCurbsLF + "\" L "
                            + orderCurbsW + " " + orderCurbsW  + "\" W "
                            + orderCurbsH + " " + orderCurbsHF + "\" H "
                            + orderCurbsF + " " + orderCurbsFF + "\" F"
                            + "\n"
                            + "Curb Notes: " + orderNotes
                            + "\n\n";

                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                    curbsGroup.clearCheck();
                    getCurbsQ.setText("");
                    getCurbsL.setText("");
                    curbsLF.setSelection(0);
                    getCurbsW.setText("");
                    curbsWF.setSelection(0);
                    getCurbsH.setText("");
                    curbsHF.setSelection(0);
                    getCurbsF.setText("");
                    curbsFF.setSelection(0);
                    colors.setText("");
                    materials.setText("");
                    curbsNotes.setText("");

                    mCustomReviewMessage = "";
                    mCustomSendMessage = "";

                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

}