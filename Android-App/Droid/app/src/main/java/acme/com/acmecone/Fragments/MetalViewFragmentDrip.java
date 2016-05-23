package acme.com.acmecone.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.acmecone.R;

public class MetalViewFragmentDrip extends Fragment {

    public TextView txt_phone_call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = inflater.inflate(R.layout.metal_layout_drip, container, false);

        txt_phone_call = (TextView) view.findViewById(R.id.metal_phone);
        txt_phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Acme Cone Co.", Toast.LENGTH_LONG).show();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: (866) 516-4079"));
                startActivity(callIntent);
            }
        });

        return view;
    }

}
