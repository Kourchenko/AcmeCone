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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

import acme.com.acmecone.Items.Cone;
import acme.com.acmecone.Items.Corner;
import acme.com.acmecone.Items.Curb;
import acme.com.acmecone.Items.Drop;
import acme.com.acmecone.Items.Pan;
import acme.com.acmecone.Items.Pipe;
import acme.com.acmecone.Items.Scupper;
import acme.com.acmecone.Items.Tube;
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

    private RecyclerView mRecycleView;
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


                                    } catch (NumberFormatException e) {
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


                                    } catch (NumberFormatException e) {
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
                        final View layout_pipe = View.inflate(v.getContext(), R.layout.dialog_pipe_custom, null);
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

                        builder_pipe.show();
                        break;

                    case 3:
                        AlertDialog.Builder builder_drop = new AlertDialog.Builder(v.getContext());

                        builder_drop.setTitle("Custom Drop Scuppers");
                        final View layout_drop = View.inflate(v.getContext(), R.layout.dialog_drop_custom, null);
                        builder_drop.setView(layout_drop);

                        final EditText drop_quantity = (EditText) layout_drop.findViewById(R.id.drop_text_quantity);
                        final EditText drop_diameter = (EditText) layout_drop.findViewById(R.id.drop_text_diameter);
                        final Spinner drop_diameterFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_diameter_spinner);
                        final EditText drop_depth = (EditText) layout_drop.findViewById(R.id.drop_text_depth);
                        final Spinner drop_depthFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_depth_spinner);
                        final EditText drop_flange = (EditText) layout_drop.findViewById(R.id.drop_text_flange);
                        final Spinner drop_flangeFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_flange_spinner);
                        final EditText drop_color = (EditText) layout_drop.findViewById(R.id.drop_text_color);
                        final EditText drop_material = (EditText) layout_drop.findViewById(R.id.drop_text_material);

                        ArrayAdapter<CharSequence> drop_diaAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        drop_diameterFrac.setAdapter(drop_diaAdp);

                        final ArrayAdapter<CharSequence> drop_depAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        drop_depthFrac.setAdapter(drop_depAdp);

                        ArrayAdapter<CharSequence> drop_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        drop_flangeFrac.setAdapter(drop_flgAdp);

                        builder_drop.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    final int quantity = Integer.parseInt(drop_quantity.getText().toString());
                                    final int diameter = Integer.parseInt(drop_diameter.getText().toString());
                                    final String diameterFrac = drop_diameterFrac.getSelectedItem().toString();
                                    final int depth = Integer.parseInt(drop_depth.getText().toString());
                                    final String depthFrac = drop_depthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(drop_flange.getText().toString());
                                    final String flangeFrac = drop_flangeFrac.getSelectedItem().toString();
                                    final String color = drop_color.getText().toString();
                                    final String material = drop_material.getText().toString();

                                    Drop drop = new Drop();
                                    drop.quantity = quantity;
                                    drop.diameter = diameter;
                                    drop.diameterFrac = diameterFrac;
                                    drop.depth = depth;
                                    drop.depthFrac = depthFrac;
                                    drop.flange = flange;
                                    drop.flangeFrac = flangeFrac;
                                    drop.color = color;
                                    drop.material = material;

                                    ConstantVar.DROPS.add(drop);
                                    Toast.makeText(layout_drop.getContext(), "ADDED Drop!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_drop.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_drop.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_drop.show();

                        break;

                    case 4:
                        AlertDialog.Builder builder_scupper = new AlertDialog.Builder(v.getContext());

                        builder_scupper.setTitle("Custom Scuppers");
                        final View layout_scupper = View.inflate(v.getContext(), R.layout.dialog_scupper_custom, null);
                        builder_scupper.setView(layout_scupper);

                        final RadioGroup scupper_radioGroup = (RadioGroup) layout_scupper.findViewById(R.id.scupper_btn_radio_group);
                        final RadioButton scupper_radio_tw = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_tw);
                        final RadioButton scupper_radio_ovrf = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_ovrf);
                        final EditText scupper_quantity = (EditText) layout_scupper.findViewById(R.id.scupper_text_quantity);
                        final EditText scupper_depth = (EditText) layout_scupper.findViewById(R.id.scupper_text_depth);
                        final Spinner scupper_depthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_depth_spinner);
                        final EditText scupper_width = (EditText) layout_scupper.findViewById(R.id.scupper_text_width);
                        final Spinner scupper_widthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_width_spinner);
                        final EditText scupper_length = (EditText) layout_scupper.findViewById(R.id.scupper_text_length);
                        final Spinner scupper_lengthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_length_spinner);
                        final EditText scupper_flange = (EditText) layout_scupper.findViewById(R.id.scupper_text_flange);
                        final Spinner scupper_flangeFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_flange_spinner);
                        final EditText scupper_color = (EditText) layout_scupper.findViewById(R.id.scupper_text_color);
                        final EditText scupper_material = (EditText) layout_scupper.findViewById(R.id.scupper_text_material);

                        ArrayAdapter<CharSequence> scupper_depAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        scupper_depthFrac.setAdapter(scupper_depAdp);

                        ArrayAdapter<CharSequence> scupper_wdtAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        scupper_widthFrac.setAdapter(scupper_wdtAdp);

                        ArrayAdapter<CharSequence> scupper_lngAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        scupper_lengthFrac.setAdapter(scupper_lngAdp);

                        ArrayAdapter<CharSequence> scupper_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        scupper_flangeFrac.setAdapter(scupper_flgAdp);

                        builder_scupper.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radio_tw.getId()) {
                                        final String type = "Thru-Wall";
                                        final int quantity = Integer.parseInt(scupper_quantity.getText().toString());
                                        final int depth = Integer.parseInt(scupper_depth.getText().toString());
                                        final String depthFrac = scupper_depthFrac.getSelectedItem().toString();
                                        final int width = Integer.parseInt(scupper_width.getText().toString());
                                        final String widthFrac = scupper_widthFrac.getSelectedItem().toString();
                                        final int length = Integer.parseInt(scupper_length.getText().toString());
                                        final String lengthFrac = scupper_lengthFrac.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(scupper_flange.getText().toString());
                                        final String flangeFrac = scupper_flangeFrac.getSelectedItem().toString();
                                        final String color = scupper_color.getText().toString();
                                        final String material = scupper_material.getText().toString();

                                        Scupper scupper = new Scupper();
                                        scupper.type = type;
                                        scupper.quantity = quantity;
                                        scupper.depth = depth;
                                        scupper.depthFrac = depthFrac;
                                        scupper.width = width;
                                        scupper.widthFrac = widthFrac;
                                        scupper.length = length;
                                        scupper.lengthFrac = lengthFrac;
                                        scupper.flange = flange;
                                        scupper.flangeFrac = flangeFrac;
                                        scupper.color = color;
                                        scupper.material = material;

                                        ConstantVar.SCUPPERS.add(scupper);
                                        Toast.makeText(layout_scupper.getContext(), "ADDED Scupper!", Toast.LENGTH_SHORT).show();


                                    } else if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radio_ovrf.getId()) {
                                        final String type = "Overflow";
                                        final int quantity = Integer.parseInt(scupper_quantity.getText().toString());
                                        final int depth = Integer.parseInt(scupper_depth.getText().toString());
                                        final String depthFrac = scupper_depthFrac.getSelectedItem().toString();
                                        final int width = Integer.parseInt(scupper_width.getText().toString());
                                        final String widthFrac = scupper_widthFrac.getSelectedItem().toString();
                                        final int length = Integer.parseInt(scupper_length.getText().toString());
                                        final String lengthFrac = scupper_lengthFrac.getSelectedItem().toString();
                                        final int flange = Integer.parseInt(scupper_flange.getText().toString());
                                        final String flangeFrac = scupper_flangeFrac.getSelectedItem().toString();
                                        final String color = scupper_color.getText().toString();
                                        final String material = scupper_material.getText().toString();

                                        Scupper scupper = new Scupper();
                                        scupper.type = type;
                                        scupper.quantity = quantity;
                                        scupper.depth = depth;
                                        scupper.depthFrac = depthFrac;
                                        scupper.width = width;
                                        scupper.widthFrac = widthFrac;
                                        scupper.length = length;
                                        scupper.lengthFrac = lengthFrac;
                                        scupper.flange = flange;
                                        scupper.flangeFrac = flangeFrac;
                                        scupper.color = color;
                                        scupper.material = material;

                                        ConstantVar.SCUPPERS.add(scupper);
                                        Toast.makeText(layout_scupper.getContext(), "ADDED Scupper!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_scupper.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_scupper.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_scupper.show();
                        break;

                    case 5:
                        AlertDialog.Builder builder_pan = new AlertDialog.Builder(v.getContext());
                        builder_pan.setTitle("Custom Pitch Pans");
                        final View layout_pan = View.inflate(v.getContext(), R.layout.dialog_pitch_pans_custom, null);
                        builder_pan.setView(layout_pan);

                        final String[] pan_array_dimension = {"Round", "Square"};

                        final RelativeLayout pan_dimension_width = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_width_hide);
                        final RelativeLayout pan_dimension_length = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_length_hide);
                        final RelativeLayout pan_dimension_diameter = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_diameter_hide);
                        final RadioGroup pan_radioGroupType = (RadioGroup) layout_pan.findViewById(R.id.pitchpans_btn_radio_group_type);
                        final RadioButton pan_radioGroupSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_split);
                        final RadioButton pan_radioGroupNonSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_nonsplit);
                        final EditText pan_quantity = (EditText) layout_pan.findViewById(R.id.pitchpans_text_quantity);
                        final EditText pan_height = (EditText) layout_pan.findViewById(R.id.pitchpans_text_height);
                        final Spinner pan_heightFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_height_spinner);
                        final EditText pan_diameter = (EditText) layout_pan.findViewById(R.id.pitchpans_text_diameter);
                        final Spinner pan_diameterFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_diameter_spinner);
                        final EditText pan_length = (EditText) layout_pan.findViewById(R.id.pitchpans_text_length);
                        final Spinner pan_lengthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_length_spinner);
                        final EditText pan_width = (EditText) layout_pan.findViewById(R.id.pitchpans_text_width);
                        final Spinner pan_widthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_width_spinner);
                        final EditText pan_flange = (EditText) layout_pan.findViewById(R.id.pitchpans_text_flange);
                        final Spinner pan_flangeFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_flange_spinner);
                        final EditText pan_color = (EditText) layout_pan.findViewById(R.id.pitchpans_text_color);
                        final EditText pan_material = (EditText) layout_pan.findViewById(R.id.pitchpans_text_material);


                        ArrayAdapter<CharSequence> pan_hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_heightFrac.setAdapter(pan_hthAdp);

                        ArrayAdapter<CharSequence> pan_diaAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_diameterFrac.setAdapter(pan_diaAdp);

                        ArrayAdapter<CharSequence> pan_lngAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_lengthFrac.setAdapter(pan_lngAdp);

                        ArrayAdapter<CharSequence> pan_wdtAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_widthFrac.setAdapter(pan_wdtAdp);

                        ArrayAdapter<CharSequence> pan_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_flangeFrac.setAdapter(pan_flgAdp);


                        builder_pan.setSingleChoiceItems(pan_array_dimension, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    pan_dimension_length.setVisibility(View.GONE);
                                    pan_dimension_width.setVisibility(View.GONE);
                                    pan_dimension_diameter.setVisibility(View.VISIBLE);
                                } else if (i == 1) {
                                    pan_dimension_length.setVisibility(View.VISIBLE);
                                    pan_dimension_width.setVisibility(View.VISIBLE);
                                    pan_dimension_diameter.setVisibility(View.GONE);
                                }
                            }
                        });

                        builder_pan.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupSplit.getId()) {

                                        if (pan_dimension_diameter.getVisibility() == View.VISIBLE) {
                                            final String dimension = "Round";
                                            final String type = "Split";
                                            final int quantity = Integer.parseInt(pan_quantity.getText().toString());
                                            final int height = Integer.parseInt(pan_height.getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int diameter = Integer.parseInt(pan_diameter.getText().toString());
                                            final String diameterFrac = pan_diameterFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(pan_flange.getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = pan_color.getText().toString();
                                            final String material = pan_material.getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.diameter = diameter;
                                            pan.diameterFrac = diameterFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();


                                        } else if (pan_dimension_diameter.getVisibility() == View.GONE) {
                                            final String dimension = "Square";
                                            final String type = "Split";
                                            final int quantity = Integer.parseInt(pan_quantity.getText().toString());
                                            final int height = Integer.parseInt(pan_height.getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int length = Integer.parseInt(pan_length.getText().toString());
                                            final String lengthFrac = pan_lengthFrac.getSelectedItem().toString();
                                            final int width = Integer.parseInt(pan_width.getText().toString());
                                            final String widthFrac = pan_widthFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(pan_flange.getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = pan_color.getText().toString();
                                            final String material = pan_material.getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.length = length;
                                            pan.lengthFrac = lengthFrac;
                                            pan.width = width;
                                            pan.widthFrac = widthFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupNonSplit.getId()) {
                                        if (pan_dimension_diameter.getVisibility() == View.VISIBLE) {
                                            final String dimension = "Round";
                                            final String type = "Non-Split";
                                            final int quantity = Integer.parseInt(pan_quantity.getText().toString());
                                            final int height = Integer.parseInt(pan_height.getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int diameter = Integer.parseInt(pan_diameter.getText().toString());
                                            final String diameterFrac = pan_diameterFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(pan_flange.getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = pan_color.getText().toString();
                                            final String material = pan_material.getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.diameter = diameter;
                                            pan.diameterFrac = diameterFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();


                                        } else if (pan_dimension_diameter.getVisibility() == View.GONE) {
                                            final String dimension = "Square";
                                            final String type = "Non-Split";
                                            final int quantity = Integer.parseInt(pan_quantity.getText().toString());
                                            final int height = Integer.parseInt(pan_height.getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int length = Integer.parseInt(pan_length.getText().toString());
                                            final String lengthFrac = pan_lengthFrac.getSelectedItem().toString();
                                            final int width = Integer.parseInt(pan_width.getText().toString());
                                            final String widthFrac = pan_widthFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(pan_flange.getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = pan_color.getText().toString();
                                            final String material = pan_material.getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.length = length;
                                            pan.lengthFrac = lengthFrac;
                                            pan.width = width;
                                            pan.widthFrac = widthFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_pan.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_pan.show();
                        break;

                    case 6:
                        AlertDialog.Builder builder_tube = new AlertDialog.Builder(v.getContext());

                        builder_tube.setTitle("Custom Tube Wraps");
                        final View layout_tube = View.inflate(v.getContext(), R.layout.dialog_tube_custom, null);
                        builder_tube.setView(layout_tube);

                        final RadioGroup tube_radioGroup = (RadioGroup) layout_tube.findViewById(R.id.tube_btn_radio_group);
                        final RadioButton tube_radioSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_split);
                        final RadioButton tube_radioNonSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_nonsplit);
                        final EditText tube_quantity = (EditText) layout_tube.findViewById(R.id.tube_text_quantity);
                        final EditText tube_height = (EditText) layout_tube.findViewById(R.id.tube_text_height);
                        final Spinner tube_heightFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_height_spinner);
                        final EditText tube_length = (EditText) layout_tube.findViewById(R.id.tube_text_length);
                        final Spinner tube_lengthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_length_spinner);
                        final EditText tube_width = (EditText) layout_tube.findViewById(R.id.tube_text_width);
                        final Spinner tube_widthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_width_spinner);
                        final EditText tube_flange = (EditText) layout_tube.findViewById(R.id.tube_text_flange);
                        final Spinner tube_flangeFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_flange_spinner);
                        final EditText tube_color = (EditText) layout_tube.findViewById(R.id.tube_text_color);
                        final EditText tube_material = (EditText) layout_tube.findViewById(R.id.tube_text_material);

                        ArrayAdapter<CharSequence> tube_hthAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        tube_heightFrac.setAdapter(tube_hthAdp);

                        ArrayAdapter<CharSequence> tube_lngAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        tube_lengthFrac.setAdapter(tube_lngAdp);

                        ArrayAdapter<CharSequence> tube_wdtAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        tube_widthFrac.setAdapter(tube_wdtAdp);

                        ArrayAdapter<CharSequence> tube_flgAdp = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        tube_flangeFrac.setAdapter(tube_flgAdp);

                        builder_tube.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (tube_radioGroup.getCheckedRadioButtonId() == tube_radioSplit.getId()) {
                                    final String type = "Split";
                                    final int quantity = Integer.parseInt(tube_quantity.getText().toString());
                                    final int height = Integer.parseInt(tube_height.getText().toString());
                                    final String heightFrac = tube_heightFrac.getSelectedItem().toString();
                                    final int length = Integer.parseInt(tube_length.getText().toString());
                                    final String lengthFrac = tube_lengthFrac.getSelectedItem().toString();
                                    final int width = Integer.parseInt(tube_width.getText().toString());
                                    final String widthFrac = tube_widthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(tube_flange.getText().toString());
                                    final String flangeFrac = tube_flangeFrac.getSelectedItem().toString();
                                    final String color = tube_color.getText().toString();
                                    final String material = tube_material.getText().toString();


                                    Tube tube = new Tube();
                                    tube.type = type;
                                    tube.quanity = quantity;
                                    tube.height = height;
                                    tube.heightFrac = heightFrac;
                                    tube.length = length;
                                    tube.lengthFrac = lengthFrac;
                                    tube.width = width;
                                    tube.widthFrac = widthFrac;
                                    tube.flange = flange;
                                    tube.flangeFrac = flangeFrac;
                                    tube.color = color;
                                    tube.material = material;

                                    ConstantVar.TUBES.add(tube);
                                    Toast.makeText(layout_tube.getContext(), "ADDED Tube Wraps!", Toast.LENGTH_SHORT).show();
                                } else if (tube_radioGroup.getCheckedRadioButtonId() == tube_radioNonSplit.getId()) {
                                    final String type = "Non-Split";
                                    final int quantity = Integer.parseInt(tube_quantity.getText().toString());
                                    final int height = Integer.parseInt(tube_height.getText().toString());
                                    final String heightFrac = tube_heightFrac.getSelectedItem().toString();
                                    final int length = Integer.parseInt(tube_length.getText().toString());
                                    final String lengthFrac = tube_lengthFrac.getSelectedItem().toString();
                                    final int width = Integer.parseInt(tube_width.getText().toString());
                                    final String widthFrac = tube_widthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(tube_flange.getText().toString());
                                    final String flangeFrac = tube_flangeFrac.getSelectedItem().toString();
                                    final String color = tube_color.getText().toString();
                                    final String material = tube_material.getText().toString();


                                    Tube tube = new Tube();
                                    tube.type = type;
                                    tube.quanity = quantity;
                                    tube.height = height;
                                    tube.heightFrac = heightFrac;
                                    tube.length = length;
                                    tube.lengthFrac = lengthFrac;
                                    tube.width = width;
                                    tube.widthFrac = widthFrac;
                                    tube.flange = flange;
                                    tube.flangeFrac = flangeFrac;
                                    tube.color = color;
                                    tube.material = material;

                                    ConstantVar.TUBES.add(tube);
                                    Toast.makeText(layout_tube.getContext(), "ADDED Tube Wraps!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder_tube.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_tube.show();

                        break;

                    case 7:
                        AlertDialog.Builder builder_curb = new AlertDialog.Builder(v.getContext());
                        builder_curb.setTitle("Custom Curb Wraps");
                        final View layout_curb = View.inflate(v.getContext(), R.layout.dialog_pitch_pans_custom, null);
                        builder_curb.setView(layout_curb);

                        final RadioGroup curb_radioGroup = (RadioGroup) layout_curb.findViewById(R.id.tube_btn_radio_group);
                        final RadioButton curb_radioSplit = (RadioButton) layout_curb.findViewById(R.id.tube_btn_radio_split);
                        final RadioButton curb_radioNonSplit = (RadioButton) layout_curb.findViewById(R.id.tube_btn_radio_nonsplit);

                        builder_curb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioSplit.getId()) {

                                    final String type = "Split";
                                    final int quantity = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_height)).getText().toString());
                                    final String heightFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_height_spinner)).getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_length)).getText().toString());
                                    final String lengthFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_length_spinner)).getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_width)).getText().toString());
                                    final String widthFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_width_spinner)).getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_flange)).getText().toString());
                                    final String flangeFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_flange_spinner)).getSelectedItem().toString();
                                    final String color = ((EditText) layout_curb.findViewById(R.id.curb_text_color)).getText().toString();
                                    final String material = ((EditText) layout_curb.findViewById(R.id.curb_text_material)).getText().toString();

                                    Curb curb = new Curb();
                                    curb.type = type;
                                    curb.quantity = quantity;
                                    curb.height = height;
                                    curb.heightFrac = heightFrac;
                                    curb.length = length;
                                    curb.lengthFrac = lengthFrac;
                                    curb.width = width;
                                    curb.widthFrac = widthFrac;
                                    curb.flange = flange;
                                    curb.flangeFrac = flangeFrac;
                                    curb.color = color;
                                    curb.material = material;

                                    ConstantVar.CURBS.add(curb);
                                    Toast.makeText(layout_curb.getContext(), "ADDED Curb Wrap!", Toast.LENGTH_SHORT).show();

                                } else if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioNonSplit.getId()) {
                                    final String type = "Non-Split";
                                    final int quantity = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_height)).getText().toString());
                                    final String heightFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_height_spinner)).getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_length)).getText().toString());
                                    final String lengthFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_length_spinner)).getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_width)).getText().toString());
                                    final String widthFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_width_spinner)).getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.tube_text_flange)).getText().toString());
                                    final String flangeFrac = ((Spinner) layout_curb.findViewById(R.id.curb_menu_flange_spinner)).getSelectedItem().toString();
                                    final String color = ((EditText) layout_curb.findViewById(R.id.curb_text_color)).getText().toString();
                                    final String material = ((EditText) layout_curb.findViewById(R.id.curb_text_material)).getText().toString();

                                    Curb curb = new Curb();
                                    curb.type = type;
                                    curb.quantity = quantity;
                                    curb.height = height;
                                    curb.heightFrac = heightFrac;
                                    curb.length = length;
                                    curb.lengthFrac = lengthFrac;
                                    curb.width = width;
                                    curb.widthFrac = widthFrac;
                                    curb.flange = flange;
                                    curb.flangeFrac = flangeFrac;
                                    curb.color = color;
                                    curb.material = material;

                                    ConstantVar.CURBS.add(curb);
                                    Toast.makeText(layout_curb.getContext(), "ADDED Curb Wrap!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder_curb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_curb.show();
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
