package acme.com.acmecone.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

import acme.com.acmecone.Cone;
import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;

public class CustomFragment extends Fragment {


    private String[] rowData = {
            "Cone",
            "Boxes",
            "Corners",
            "Pipe Wraps",
            "Drains",
            "Scuppers",
            "Pitch Pans",
            "Tube Wraps"
    };

    private Integer[] imgData = {
            R.mipmap.default_cones,
            R.mipmap.default_boxes,
            R.mipmap.default_corners,
            R.mipmap.default_pipewraps,
            R.mipmap.default_drains,
            R.mipmap.default_scuppers,
            R.mipmap.default_pitchpans,
            R.mipmap.default_tubewraps
    };

    private  RecyclerView mRecycleView;
    private RecyclerView.Adapter mCustomAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.custom_fragment, container, false);
        final Context context = getActivity().getApplicationContext();

        mRecycleView = (RecyclerView) view.findViewById(R.id.custom_recycle_view);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecycleView.setLayoutManager(mLayoutManager);
        mCustomAdapter = new CustomAdapter(rowData, imgData);
        mRecycleView.setAdapter(mCustomAdapter);

        return view;
    }


    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        String[] textData;
        Integer[] imgData;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mText;
            public ImageView mImage;

            public ViewHolder(View v) {
                super(v);
                mText = (TextView) v.findViewById(R.id.custom_row_txt);
                mImage = (ImageView) v.findViewById(R.id.custom_row_img);

                v.setClickable(true);
                v.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                switch (position) {
                    case 0:

                        AlertDialog.Builder builder_cone = new AlertDialog.Builder(v.getContext());

                        builder_cone.setTitle("Custom Cone");
                        final View layout_cone = View.inflate(v.getContext(), R.layout.cone_custom_dialog, null);
                        builder_cone.setView(layout_cone);

                        final RadioGroup coneRadioGroup = (RadioGroup) layout_cone.findViewById(R.id.button_group_cone);
                        final RadioButton coneSplit = (RadioButton) layout_cone.findViewById(R.id.button_cone_split);
                        final RadioButton coneNonSplit = (RadioButton) layout_cone.findViewById(R.id.button_cone_nonsplit);
                        final EditText textQuantity = (EditText) layout_cone.findViewById(R.id.text_cone_quantity);
                        final EditText textTop = (EditText) layout_cone.findViewById(R.id.text_cone_top);
                        final Spinner menuTop = (Spinner) layout_cone.findViewById(R.id.menu_cone_top_spinner);
                        final EditText textBtm = (EditText) layout_cone.findViewById(R.id.text_cone_bottom);
                        final Spinner menuBtm = (Spinner) layout_cone.findViewById(R.id.menu_cone_bottom_spinner);
                        final EditText textFlg = (EditText) layout_cone.findViewById(R.id.text_cone_flange);
                        final Spinner menuFlg = (Spinner) layout_cone.findViewById(R.id.menu_cone_flange_spinner);
                        final EditText textHth = (EditText) layout_cone.findViewById(R.id.text_cone_height);
                        final Spinner menuHth = (Spinner) layout_cone.findViewById(R.id.menu_cone_height_spinner);
                        final EditText textColor = (EditText) layout_cone.findViewById(R.id.text_cone_color);
                        final EditText textMaterial = (EditText) layout_cone.findViewById(R.id.text_cone_material);



                        ArrayAdapter<CharSequence> topAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        menuTop.setAdapter(topAdp);

                        ArrayAdapter<CharSequence> botAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        menuBtm.setAdapter(botAdp);

                        ArrayAdapter<CharSequence> hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        menuHth.setAdapter(hthAdp);

                        ArrayAdapter<CharSequence> flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        menuFlg.setAdapter(flgAdp);


                        builder_cone.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (coneRadioGroup.getCheckedRadioButtonId() == coneSplit.getId()) {

                                    try {
                                        final String type = "Split";
                                        final int quantity = Integer.parseInt(textQuantity.getText().toString());
                                        final int height = Integer.parseInt(textHth.getText().toString());
                                        final String heightFrac = menuHth.getSelectedItem().toString();
                                        final int top = Integer.parseInt(textTop.getText().toString());
                                        final String topFrac = menuTop.getSelectedItem().toString();
                                        final int bot = Integer.parseInt(textBtm.getText().toString());
                                        final String botFrac = menuBtm.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(textFlg.getText().toString());
                                        final String flangeFrac = menuFlg.getSelectedItem().toString();
                                        final String color = textColor.getText().toString();
                                        final String material = textMaterial.getText().toString();

                                        final Cone cone = new Cone();
                                        cone.quantity = quantity;
                                        cone.type = type;
                                        cone.height = height;
                                        cone.heightFrac = heightFrac;
                                        cone.top = top;
                                        cone.topFrac = topFrac;
                                        cone.bot = bot;
                                        cone.botFrac = botFrac;
                                        cone.flange = flange;
                                        cone.flangeFrac = flangeFrac;
                                        cone.color = color;
                                        cone.material = material;

                                        ConstantVar.CONES.add(cone);
                                    } catch (NumberFormatException e) {
                                        Toast.makeText(layout_cone.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();
                                    }

                                } else if (coneRadioGroup.getCheckedRadioButtonId() == coneNonSplit.getId()) {
                                    try {
                                        final int quantity = Integer.parseInt(textQuantity.getText().toString());
                                        final String type = "Non-Split";
                                        final int height = Integer.parseInt(textHth.getText().toString());
                                        final String heightFrac = menuHth.getSelectedItem().toString();
                                        final int top = Integer.parseInt(textTop.getText().toString());
                                        final String topFrac = menuTop.getSelectedItem().toString();
                                        final int bot = Integer.parseInt(textBtm.getText().toString());
                                        final String botFrac = menuBtm.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(textFlg.getText().toString());
                                        final String flangeFrac = menuFlg.getSelectedItem().toString();
                                        final String color = textColor.getText().toString();
                                        final String material = textMaterial.getText().toString();

                                        final Cone cone = new Cone();
                                        cone.quantity = quantity;
                                        cone.type = type;
                                        cone.height = height;
                                        cone.heightFrac = heightFrac;
                                        cone.top = top;
                                        cone.topFrac = topFrac;
                                        cone.bot = bot;
                                        cone.botFrac = botFrac;
                                        cone.flange = flange;
                                        cone.flangeFrac = flangeFrac;
                                        cone.color = color;
                                        cone.material = material;

                                        ConstantVar.CONES.add(cone);
                                    } catch (NumberFormatException e) {
                                        Toast.makeText(layout_cone.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                        builder_cone.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder_cone.show();

                        break;

                    case 1:
                        AlertDialog.Builder builder_corner = new AlertDialog.Builder(v.getContext());

                        builder_corner.setTitle("Custom Corner");
                        final View layout_corner = View.inflate(v.getContext(), R.layout.corner_custom_dialog, null);
                        builder_corner.setView(layout_corner);






                    case 2:
                        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
                        //
                        break;

                    case 6:
                        //
                        break;

                    case 7:
                        //
                        break;
                }
            }
        }

        public CustomAdapter(String[] textData, Integer[] imgData) {
            // TODO Auto-generated constructor stub
            this.textData = textData;
            this.imgData = imgData;
        }



        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);

            TextView text = (TextView) parent.findViewById(R.id.custom_row_txt);
            ImageView img = (ImageView) parent.findViewById(R.id.custom_row_img);

            ViewHolder vh = new ViewHolder(vi);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mText.setText(textData[position]);
            holder.mImage.setImageResource(imgData[position]);
        }


        @Override
        public int getItemCount() {
            // TODO Auto-generated method stub
            return textData.length;
        }


        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

    }
}
