package acme.com.acmecone.Fragments;

import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class CornersFragment extends Fragment {

    private Button cornersB;
    private EditText cornersQ, cornersL, cornersW, cornersH;
    private Spinner cornersLF, cornersWF, cornersHF;
    private EditText cornersNotes;
    private RadioGroup cornersRadio;
    private RadioButton cornersInside, cornersOutside;
    private String cornersRadioChoice;
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";


    public CornersFragment() {
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

        final View view = inflater.inflate(R.layout.corners_fragment, container, false);

        final Context context = getActivity().getApplicationContext();

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        cornersRadio = (RadioGroup) view.findViewById(R.id.corners_choice);
        cornersInside = (RadioButton) view.findViewById(R.id.corners_inside);
        cornersInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Inside Corners", Toast.LENGTH_SHORT).show();
                cornersRadioChoice = "Inside";
            }
        });
        cornersOutside = (RadioButton) view.findViewById(R.id.corners_outside);
        cornersOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Outside", Toast.LENGTH_SHORT).show();
                cornersRadioChoice = "Outside";
            }
        });

        cornersQ = (EditText) view.findViewById(R.id.corners_quantity);
        cornersL = (EditText) view.findViewById(R.id.corners_length);
        cornersW = (EditText) view.findViewById(R.id.corners_width);
        cornersH = (EditText) view.findViewById(R.id.corners_height);

        cornersNotes = (EditText) view.findViewById(R.id.corners_notes);

        cornersLF = (Spinner) view.findViewById(R.id.corners_length_frac);
        ArrayAdapter<CharSequence> spinnerCornersLF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        cornersLF.setAdapter(spinnerCornersLF);

        cornersWF = (Spinner) view.findViewById(R.id.corners_width_frac);
        ArrayAdapter<CharSequence> spinnerCornersWF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        cornersWF.setAdapter(spinnerCornersWF);

        cornersHF = (Spinner) view.findViewById(R.id.corners_height_frac);
        ArrayAdapter<CharSequence> spinnerCornersHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        cornersHF.setAdapter(spinnerCornersHF);

        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView color = (AutoCompleteTextView) view.findViewById(R.id.corners_color);
        color.setAdapter(adapterColor);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView material = (AutoCompleteTextView) view.findViewById(R.id.corners_material);
        material.setAdapter(adapterMaterial);

        cornersB = (Button) view.findViewById(R.id.corners_submitB);
        cornersB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderCornersQ = cornersQ.getText().toString();
                final String orderCornersL = cornersL.getText().toString();
                final String orderCornersLF = cornersLF.getSelectedItem().toString();
                final String orderCornersW = cornersW.getText().toString();
                final String orderCornersWF = cornersWF.getSelectedItem().toString();
                final String orderCornersH = cornersH.getText().toString();
                final String orderCornersHF = cornersHF.getSelectedItem().toString();
                final String orderColors = color.getText().toString().trim();
                final String orderMaterials = material.getText().toString().trim();
                final String orderNotes = cornersNotes.getText().toString();
                final String orderChoice = cornersRadioChoice;

                if (!orderCornersQ.isEmpty()
                        && !orderCornersL.isEmpty()
                        && !orderCornersW.isEmpty()
                        && !orderCornersH.isEmpty()
                        && !orderMaterials.isEmpty()
                        && !orderMaterials.isEmpty()) {

                    mCustomReviewMessage += orderCornersQ + ": " + orderColors + " " + orderMaterials
                            + "\n" + orderChoice + " Custom Corners"
                            + "\nLength: "  + orderCornersL + " " + orderCornersLF + "\""
                            + "\nWidth: "   + orderCornersW + " " + orderCornersWF + "\""
                            + "\nFlange: "  + orderCornersH + " " + orderCornersHF + "\""
                            + "\n"
                            + "Custom Corners Notes: " + orderNotes
                            + "\n\n\n";


                    mCustomSendMessage += orderColors + " " + orderMaterials
                            + "\n"
                            + orderCornersQ + ") "
                            + orderChoice + " Corners "
                            + orderCornersL + " " + orderCornersLF + "\" L "
                            + orderCornersW + " " + orderCornersHF + "\" H "
                            + orderCornersH + " " + orderCornersHF + "\" F "
                            + "\n"
                            + "Corners Notes: " + orderNotes
                            + "\n\n";


                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);
                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();


                    cornersRadio.clearCheck();
                    cornersQ.setText("");
                    cornersL.setText("");
                    cornersLF.setSelection(0);
                    cornersW.setText("");
                    cornersWF.setSelection(0);
                    cornersH.setText("");
                    cornersHF.setSelection(0);
                    color.setText("");
                    material.setText("");
                    cornersNotes.setText("");
                    mCustomReviewMessage = "";
                    mCustomSendMessage = "";
                    cornersRadioChoice = "";

                } else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}









