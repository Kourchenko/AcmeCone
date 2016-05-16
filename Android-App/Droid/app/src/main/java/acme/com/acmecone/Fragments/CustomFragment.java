package acme.com.acmecone.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.Items.Cone;
import acme.com.acmecone.Items.Corner;
import acme.com.acmecone.Items.Pipe;
import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;

public class CustomFragment extends Fragment {


    private String[] rowData = {
            "Cone",
            "Corners",
            "Pipe Wraps",
            "Drop Scuppers",
            "Scuppers",
            "Pitch Pans",
            "Tube Wraps",
            "Curb Wraps",
            "Sleeper Boxes"
    };

    private Integer[] imgData = {
            R.mipmap.default_cones,
            R.mipmap.default_corners,
            R.mipmap.default_pipewraps,
            R.mipmap.default_drains,
            R.mipmap.default_scuppers,
            R.mipmap.default_pitchpans,
            R.mipmap.default_tubewraps,
            R.mipmap.default_boxes,
            R.mipmap.default_boxes
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
                        final View layout_cone = View.inflate(v.getContext(), R.layout.dialog_cone_custom, null);
                        builder_cone.setView(layout_cone);

                        final RadioGroup btn_coneRadioGroup = (RadioGroup) layout_cone.findViewById(R.id.cone_btn_radio_group);
                        final RadioButton btn_coneSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_split);
                        final RadioButton btn_coneNonSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_nonsplit);
                        final EditText cone_textQuantity = (EditText) layout_cone.findViewById(R.id.cone_text_quantity);
                        final EditText cone_textTop = (EditText) layout_cone.findViewById(R.id.cone_text_top);
                        final Spinner cone_menuTop = (Spinner) layout_cone.findViewById(R.id.menu_cone_top_spinner);
                        final EditText cone_textBtm = (EditText) layout_cone.findViewById(R.id.cone_text_bottom);
                        final Spinner cone_menuBtm = (Spinner) layout_cone.findViewById(R.id.menu_cone_bottom_spinner);
                        final EditText cone_textFlg = (EditText) layout_cone.findViewById(R.id.cone_text_flange);
                        final Spinner cone_menuFlg = (Spinner) layout_cone.findViewById(R.id.menu_cone_flange_spinner);
                        final EditText cone_textHth = (EditText) layout_cone.findViewById(R.id.cone_text_height);
                        final Spinner cone_menuHth = (Spinner) layout_cone.findViewById(R.id.menu_cone_height_spinner);
                        final EditText cone_textColor = (EditText) layout_cone.findViewById(R.id.cone_text_color);
                        final EditText cone_textMaterial = (EditText) layout_cone.findViewById(R.id.cone_text_material);


                        ArrayAdapter<CharSequence> cone_topAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        cone_menuTop.setAdapter(cone_topAdp);

                        ArrayAdapter<CharSequence> cone_botAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        cone_menuBtm.setAdapter(cone_botAdp);

                        ArrayAdapter<CharSequence> cone_hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        cone_menuHth.setAdapter(cone_hthAdp);

                        ArrayAdapter<CharSequence> cone_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        cone_menuFlg.setAdapter(cone_flgAdp);


                        builder_cone.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (btn_coneRadioGroup.getCheckedRadioButtonId() == btn_coneSplit.getId()) {

                                    try {
                                        final String type = "Split";
                                        final int quantity = Integer.parseInt(cone_textQuantity.getText().toString());
                                        final int height = Integer.parseInt(cone_textHth.getText().toString());
                                        final String heightFrac = cone_menuHth.getSelectedItem().toString();
                                        final int top = Integer.parseInt(cone_textTop.getText().toString());
                                        final String topFrac = cone_menuTop.getSelectedItem().toString();
                                        final int bot = Integer.parseInt(cone_textBtm.getText().toString());
                                        final String botFrac = cone_menuBtm.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(cone_textFlg.getText().toString());
                                        final String flangeFrac = cone_menuFlg.getSelectedItem().toString();
                                        final String color = cone_textColor.getText().toString();
                                        final String material = cone_textMaterial.getText().toString();

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

                                } else if (btn_coneRadioGroup.getCheckedRadioButtonId() == btn_coneNonSplit.getId()) {
                                    try {
                                        final int quantity = Integer.parseInt(cone_textQuantity.getText().toString());
                                        final String type = "Non-Split";
                                        final int height = Integer.parseInt(cone_textHth.getText().toString());
                                        final String heightFrac = cone_menuHth.getSelectedItem().toString();
                                        final int top = Integer.parseInt(cone_textTop.getText().toString());
                                        final String topFrac = cone_menuTop.getSelectedItem().toString();
                                        final int bot = Integer.parseInt(cone_textBtm.getText().toString());
                                        final String botFrac = cone_menuBtm.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(cone_textFlg.getText().toString());
                                        final String flangeFrac = cone_menuFlg.getSelectedItem().toString();
                                        final String color = cone_textColor.getText().toString();
                                        final String material = cone_textMaterial.getText().toString();

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
                                        Toast.makeText(layout_cone.getContext(), "ADDED Cone!", Toast.LENGTH_SHORT).show();

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
                        final View layout_corner = View.inflate(v.getContext(), R.layout.dialog_corner_custom, null);
                        builder_corner.setView(layout_corner);

                        final RadioGroup btn_cornerRadioGroup = (RadioGroup) layout_corner.findViewById(R.id.corner_btn_radio_group);
                        final RadioButton btn_cornerRadioInside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_inside);
                        final RadioButton btn_cornerRadioOutside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_outside);
                        final EditText corner_textQuantity = (EditText) layout_corner.findViewById(R.id.text_corner_quantity);
                        final EditText corner_textHeight = (EditText) layout_corner.findViewById(R.id.corner_text_height);
                        final Spinner corner_menuHeight = (Spinner) layout_corner.findViewById(R.id.corner_menu_height_spinner);
                        final EditText corner_textDepth = (EditText) layout_corner.findViewById(R.id.corner_text_depth);
                        final Spinner corner_menuDepth = (Spinner) layout_corner.findViewById(R.id.menu_corner_depth_spinner);
                        final EditText corner_textFlange = (EditText) layout_corner.findViewById(R.id.corner_text_flange);
                        final Spinner corner_menuFlange = (Spinner) layout_corner.findViewById(R.id.menu_corner_flange_spinner);
                        final EditText corner_color = (EditText) layout_corner.findViewById(R.id.corner_text_color);
                        final EditText corner_material = (EditText) layout_corner.findViewById(R.id.corner_text_material);


                        ArrayAdapter<CharSequence> hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        corner_menuHeight.setAdapter(hthAdp);

                        ArrayAdapter<CharSequence> dptAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        corner_menuDepth.setAdapter(dptAdp);

                        ArrayAdapter<CharSequence> flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        corner_menuFlange.setAdapter(flgAdp);

                        builder_corner.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (btn_cornerRadioGroup.getCheckedRadioButtonId() == btn_cornerRadioInside.getId()) {
                                    try {
                                        final String type = "Inside";
                                        final int quantity = Integer.parseInt(corner_textQuantity.getText().toString());
                                        final int height = Integer.parseInt(corner_textHeight.getText().toString());
                                        final String heightFrac = corner_menuHeight.getSelectedItem().toString();
                                        final int depth = Integer.parseInt(corner_textDepth.getText().toString());
                                        final String depthFrac = corner_menuDepth.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(corner_textFlange.getText().toString());
                                        final String flangeFrac = corner_menuFlange.getSelectedItem().toString();
                                        final String color = corner_color.getText().toString();
                                        final String material = corner_material.getText().toString();

                                        final Corner corner = new Corner();
                                        corner.type = type;
                                        corner.quantity = quantity;
                                        corner.height = height;
                                        corner.heightFrac = heightFrac;
                                        corner.depth = depth;
                                        corner.depthFrac = depthFrac;
                                        corner.flange = flange;
                                        corner.flangeFrac = flangeFrac;
                                        corner.color = color;
                                        corner.material = material;

                                        ConstantVar.CORNERS.add(corner);
                                        Toast.makeText(layout_corner.getContext(), "ADDED Corner!", Toast.LENGTH_SHORT).show();


                                    } catch(NumberFormatException e) {
                                        Toast.makeText(layout_corner.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                    }
                                } else if (btn_cornerRadioGroup.getCheckedRadioButtonId() == btn_cornerRadioOutside.getId()) {

                                    try {
                                        final String type = "Outside";
                                        final int quantity = Integer.parseInt(corner_textQuantity.getText().toString());
                                        final int height = Integer.parseInt(corner_textHeight.getText().toString());
                                        final String heightFrac = corner_menuHeight.getSelectedItem().toString();
                                        final int depth = Integer.parseInt(corner_textDepth.getText().toString());
                                        final String depthFrac = corner_menuDepth.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(corner_textFlange.getText().toString());
                                        final String flangeFrac = corner_menuFlange.getSelectedItem().toString();
                                        final String color = corner_color.getText().toString();
                                        final String material = corner_material.getText().toString();

                                        final Corner corner = new Corner();
                                        corner.type = type;
                                        corner.quantity = quantity;
                                        corner.height = height;
                                        corner.heightFrac = heightFrac;
                                        corner.depth = depth;
                                        corner.depthFrac = depthFrac;
                                        corner.flange = flange;
                                        corner.flangeFrac = flangeFrac;
                                        corner.color = color;
                                        corner.material = material;

                                        ConstantVar.CORNERS.add(corner);
                                        Toast.makeText(layout_corner.getContext(), "ADDED Corner!", Toast.LENGTH_SHORT).show();


                                    } catch(NumberFormatException e) {
                                        Toast.makeText(layout_corner.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        });

                        builder_corner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_corner.show();
                        break;

                    case 2:
                        AlertDialog.Builder builder_pipe = new AlertDialog.Builder(v.getContext());

                        builder_pipe.setTitle("Custom Pipe Wraps");
                        final View layout_pipe = View.inflate(v.getContext(), R.layout.dialog_corner_custom, null);
                        builder_pipe.setView(layout_pipe);

                        final RadioGroup btn_pipeRadioGroup = (RadioGroup) layout_pipe.findViewById(R.id.pipe_btn_radio_group);
                        final RadioButton btn_pipeRadioSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_split);
                        final RadioButton btn_pipeRadioNonSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_nonsplit);
                        final EditText pipe_textQuantity = (EditText) layout_pipe.findViewById(R.id.pipe_text_quantity);
                        final EditText pipe_textHeight = (EditText) layout_pipe.findViewById(R.id.pipe_text_height);
                        final Spinner pipe_menuHeight = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_height_spinner);
                        final EditText pipe_textDiameter = (EditText) layout_pipe.findViewById(R.id.pipe_text_diameter);
                        final Spinner pipe_menuDiameter = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_diameter_spinner);
                        final EditText pipe_textFlange = (EditText) layout_pipe.findViewById(R.id.pipe_text_flange);
                        final Spinner pipe_menuFlange = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_flange_spinner);
                        final EditText pipe_color = (EditText) layout_pipe.findViewById(R.id.pipe_text_color);
                        final EditText pipe_material = (EditText) layout_pipe.findViewById(R.id.pipe_text_material);

                        ArrayAdapter<CharSequence> pipe_hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pipe_menuHeight.setAdapter(pipe_hthAdp);

                        ArrayAdapter<CharSequence> pipe_diaAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pipe_menuDiameter.setAdapter(pipe_diaAdp);

                        ArrayAdapter pipe_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pipe_menuFlange.setAdapter(pipe_flgAdp);

                        builder_pipe.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (btn_pipeRadioGroup.getCheckedRadioButtonId() == btn_pipeRadioSplit.getId()) {
                                    try {

                                        final String type = "Split";
                                        final int quantity = Integer.parseInt(pipe_textQuantity.getText().toString());
                                        final int height = Integer.parseInt(pipe_textHeight.getText().toString());
                                        final String heightFrac = pipe_menuHeight.getSelectedItem().toString();
                                        final int diameter = Integer.parseInt(pipe_textDiameter.getText().toString());
                                        final String diameterFrac = pipe_menuDiameter.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(pipe_textFlange.getText().toString());
                                        final String flangeFrac = pipe_menuFlange.getSelectedItem().toString();
                                        final String color = pipe_color.getText().toString();
                                        final String material = pipe_material.getText().toString();

                                        Pipe pipe = new Pipe();
                                        pipe.type = type;
                                        pipe.quantity = quantity;
                                        pipe.height = height;
                                        pipe.heightFrac = heightFrac;
                                        pipe.diameter = diameter;
                                        pipe.diameterFrac = diameterFrac;
                                        pipe.flange = flange;
                                        pipe.flangeFrac = flangeFrac;
                                        pipe.color = color;
                                        pipe.material = material;

                                        ConstantVar.PIPES.add(pipe);

                                        Toast.makeText(layout_pipe.getContext(), "ADDED Pipe!", Toast.LENGTH_SHORT).show();

                                    } catch (NumberFormatException e) {
                                        Toast.makeText(layout_pipe.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                    }

                                } else if (btn_pipeRadioGroup.getCheckedRadioButtonId() == btn_pipeRadioNonSplit.getId()) {
                                    try {

                                        final String type = "Non-Split";
                                        final int quantity = Integer.parseInt(pipe_textQuantity.getText().toString());
                                        final int height = Integer.parseInt(pipe_textHeight.getText().toString());
                                        final String heightFrac = pipe_menuHeight.getSelectedItem().toString();
                                        final int diameter = Integer.parseInt(pipe_textDiameter.getText().toString());
                                        final String diameterFrac = pipe_menuDiameter.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(pipe_textFlange.getText().toString());
                                        final String flangeFrac = pipe_menuFlange.getSelectedItem().toString();
                                        final String color = pipe_color.getText().toString();
                                        final String material = pipe_material.getText().toString();

                                        Pipe pipe = new Pipe();
                                        pipe.type = type;
                                        pipe.quantity = quantity;
                                        pipe.height = height;
                                        pipe.heightFrac = heightFrac;
                                        pipe.diameter = diameter;
                                        pipe.diameterFrac = diameterFrac;
                                        pipe.flange = flange;
                                        pipe.flangeFrac = flangeFrac;
                                        pipe.color = color;
                                        pipe.material = material;

                                        ConstantVar.PIPES.add(pipe);

                                        Toast.makeText(layout_pipe.getContext(), "ADDED Pipe!", Toast.LENGTH_SHORT).show();

                                    } catch (NumberFormatException e) {
                                        Toast.makeText(layout_pipe.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }
                        });

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
