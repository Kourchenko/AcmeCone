package acme.com.acmecone.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class ScuppersFragment extends Fragment {

    private EditText getScuppersQ, getScuppersW, getScuppersD, getScuppersH, getScuppersF;
    private Spinner getScuppersWF, getScuppersDF, getScuppersHF, getScuppersFF;
    private Button scuppersSubmit;
    private EditText scuppersNotes;
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";

    public ScuppersFragment() {
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

        final View view = inflater.inflate(R.layout.scuppers_fragment, container, false);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Context context = getActivity().getApplicationContext();

        getScuppersQ = (EditText) view.findViewById(R.id.scupper_quantity);
        getScuppersW = (EditText) view.findViewById(R.id.scuppers_width);
        getScuppersD = (EditText) view.findViewById(R.id.scuppers_depth);
        getScuppersH = (EditText) view.findViewById(R.id.scuppers_height);
        getScuppersF = (EditText) view.findViewById(R.id.scuppers_flange);
        scuppersNotes = (EditText) view.findViewById(R.id.scuppers_notes);

        getScuppersWF = (Spinner) view.findViewById(R.id.scuppers_width_frac);
        ArrayAdapter<CharSequence> spinnerScuppersWF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getScuppersWF.setAdapter(spinnerScuppersWF);

        getScuppersDF = (Spinner) view.findViewById(R.id.scuppers_depth_frac);
        ArrayAdapter<CharSequence> spinnerScuppersDF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getScuppersDF.setAdapter(spinnerScuppersDF);

        getScuppersHF = (Spinner) view.findViewById(R.id.scuppers_height_frac);
        ArrayAdapter<CharSequence> spinnerScuppersHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getScuppersHF.setAdapter(spinnerScuppersHF);

        getScuppersFF = (Spinner) view.findViewById(R.id.scuppers_flange_frac);
        ArrayAdapter<CharSequence> spinnerScuppersFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getScuppersFF.setAdapter(spinnerScuppersFF);


        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView colors = (AutoCompleteTextView) view.findViewById(R.id.scuppers_color);
        colors.setAdapter(adapterColors);

        final ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView materials = (AutoCompleteTextView) view.findViewById(R.id.scuppers_material);
        materials.setAdapter(adapterMaterial);

        scuppersSubmit = (Button) view.findViewById(R.id.scuppers_submitB);
        scuppersSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String orderScuppersQ = getScuppersQ.getText().toString();
                final String orderScuppersW = getScuppersW.getText().toString();
                final String orderScuppersWF = getScuppersWF.getSelectedItem().toString();
                final String orderScuppersD = getScuppersD.getText().toString();
                final String orderScuppersDF = getScuppersDF.getSelectedItem().toString();
                final String orderScuppersH = getScuppersH.getText().toString();
                final String orderScuppersHF = getScuppersHF.getSelectedItem().toString();
                final String orderScuppersF = getScuppersF.getText().toString();
                final String orderScuppersFF = getScuppersFF.getSelectedItem().toString();
                final String orderColors = colors.getText().toString();
                final String orderMaterials = materials.getText().toString();
                final String orderNotes = scuppersNotes.getText().toString();

                if (!orderScuppersQ.isEmpty()
                        && !orderScuppersW.isEmpty()
                        && !orderScuppersD.isEmpty()
                        && !orderScuppersH.isEmpty()
                        && !orderScuppersF.isEmpty()
                        && !orderColors.isEmpty()
                        && !orderMaterials.isEmpty()) {

                    mCustomReviewMessage += orderScuppersQ + ": " + orderColors + " " + orderMaterials
                            + "\n" + "Custom Scuppers"
                            + "\nWidth: " + orderScuppersW  + " " + orderScuppersWF + " "
                            + "\nDepth: " + orderScuppersD  + " " + orderScuppersDF + " "
                            + "\nHeight: " + orderScuppersH + " " + orderScuppersHF + " "
                            + "\nFlange: " + orderScuppersF + " " + orderScuppersFF + " "
                            + "\n"
                            + "Scuppers Notes: " + orderNotes
                            + "\n\n";

                    mCustomSendMessage += orderColors + " " + orderMaterials
                            + "\n"
                            + orderScuppersQ + ") " + "Scuppers "
                            + orderScuppersD + " " + orderScuppersDF + "\" Depth"
                            + orderScuppersH + " " + orderScuppersHF + "\" H "
                            + orderScuppersF + " " + orderScuppersFF + "\" F "
                            + "\n"
                            + "Scuppers Notes: " + orderNotes
                            + "\n\n";


                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);
                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();

                    getScuppersQ.setText("");
                    getScuppersW.setText("");
                    getScuppersD.setText("");
                    getScuppersH.setText("");
                    getScuppersF.setText("");
                    getScuppersWF.setSelection(0);
                    getScuppersDF.setSelection(0);
                    getScuppersHF.setSelection(0);
                    getScuppersFF.setSelection(0);
                    colors.setText("");
                    materials.setText("");
                    scuppersNotes.setText("");

                    mCustomSendMessage = "";
                    mCustomReviewMessage = "";
                } else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return view;
    }

}



















