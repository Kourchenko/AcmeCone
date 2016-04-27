package acme.com.acmecone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.concurrent.Callable;


import acme.com.acmecone.Adapters.ViewAdapter;
import acme.com.acmecone.R;
import acme.com.acmecone.Adapters.ListViewAdapter;
import acme.com.acmecone.Adapters.SwipeToDismissTouchListener;
import acme.com.acmecone.Utility.ConstantVar;


public class ReviewFragment extends Fragment {

    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 3000;
    public ListView listView;
    private TextView listEmpty;
    private Vibrator vib;


    private static final String[] STOCK_ITEMS = new String[]{
            // RVOs
            "RVO White TPO", "RVO Grey TPO", "RVO White PVC", "RVO Grey PVC",


            // Breather Vents
            "2-Way BV White TPO", "2-Way BV Grey TPO", "2-Way White PVC", "2-Way Grey PVC",
            "Breather Vent White TPO (2-Way)", "Breather Vent Grey TPO (2-Way)",
            "Breather Vent White PVC (2-Way)", "Breather Vent Grey PVC (2-Way)",


            // Cones -- Split
            "A Cone [Split] White TPO", "A Cone [Split] Grey TPO", "A Cone [Split] White PVC", "A Cone [Split] Grey PVC",
            "B Cone [Split] White TPO", "B Cone [Split] Grey TPO", "B Cone [Split] White PVC", "B Cone [Split] Grey PVC",
            "C Cone [Split] White TPO", "C Cone [Split] Grey TPO", "C Cone [Split] White PVC", "C Cone [Split] Grey PVC",


            // Cones -- Non-Split
            "A Cone [Non-Split] White TPO", "A Cone [Non-Split] Grey TPO", "A Cone [Non-Split] White PVC", "A Cone [Non-Split] Grey PVC",
            "B Cone [Non-Split] White TPO", "B Cone [Non-Split] Grey TPO", "B Cone [Non-Split] White PVC", "B Cone [Non-Split] Grey PVC",
            "C Cone [Non-Split] White TPO", "C Cone [Non-Split] Grey TPO", "C Cone [Non-Split] White PVC", "C Cone [Non-Split] Grey PVC",


            // Corners
            "Inside Corner White TPO", "Inside Corner Grey TPO", "Inside Corner White PVC", "Inside Corner Grey PVC",
            "Outside Corner White TPO", "Outside Corner Grey TPO", "Outside Corner White PVC", "Outside Corner Grey PVC",


            // Drains
            "Drop Scupper - 2\" White TPO", "Drop Scupper - 2\" Grey TPO",
            "Drop Scupper - 2\" White PVC", "Drop Scupper - 2\" Grey PVC",

            "Drop Scupper - 3\" White TPO", "Drop Scupper - 3\" Grey TPO",
            "Drop Scupper - 3\" White PVC", "Drop Scupper - 3\" Grey PVC",

            "Metal Drop - 2\" White TPO", "Metal Drop - 2\" Grey TPO",
            "Metal Drop - 2\" White PVC", "Metal Drop - 2\" Grey PVC",

            "Metal Drop - 3\" White TPO", "Metal Drop - 3\" Grey TPO",
            "Metal Drop - 3\" White PVC", "Metal Drop - 3\" Grey PVC",

            "3\" Commercial Drain White TPO", "3\" Commercial Drain White PVC",
            "3\" Commercial Drain Grey TPO", "3\" Commercial Drain Grey PVC",

            "4\"X4\" Standard ThruWall Scupper White TPO", "4\"X4\" Standard ThruWall Scupper Grey TPO",
            "4\"X4\" Standard ThruWall Scupper White PVC", "4\"X4\" Standard ThruWall Scupper Grey PVC",

            "6\"X6\" Standard ThruWall Scupper White TPO", "6\"X6\" Standard ThruWall Scupper Grey TPO",
            "6\"X6\" Standard ThruWall Scupper White PVC", "6\"X6\" Standard ThruWall Scupper Grey PVC",

            "Pitch Pan Standard White TPO", "Pitch Pan Standard Grey TPO",
            "Pitch Pan Standard White PVC", "Pitch Pan Standard Grey PVC"
    };

    public ReviewFragment() {
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
        View view = inflater.inflate(R.layout.review_fragment, container, false);
        final Context context = getActivity().getApplicationContext();
        final MyBaseAdapter adapter = new MyBaseAdapter();
        vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        listView = (ListView) view.findViewById(R.id.review_list_view);
        listEmpty = (TextView) view.findViewById(R.id.review_empty_cart);
        listView.setAdapter(adapter);


        final SwipeToDismissTouchListener touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                adapter.remove(position);
                            }
                        });

        touchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        listView.setOnTouchListener(touchListener);

        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(context, "IndexOf: " + position, Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

                return true;
            }
        });

        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);

        if (getView() != null) {
            if (visible) {
                Snackbar.make(getView(), "REVIEW: Swipe Left or Right to Dismiss", Snackbar.LENGTH_SHORT).show();
            }
        }
    }


    static class MyBaseAdapter extends BaseAdapter {
        MyBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.DATASET.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.DATASET.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                for (Map.Entry<Integer, String> entry: ConstantVar.REVIEW_DATABASE.entrySet()) {
                    String mDSET = entry.getKey() + ": " + entry.getValue();
                    if (ConstantVar.DATASET.get(position).equals(mDSET)) {
                        ConstantVar.DATASET.remove(ConstantVar.DATASET.indexOf(ConstantVar.DATASET.get(position)));
                        ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(entry.getValue()));

                    } else {
                        System.out.println("Error Found While removing: " + mDSET);
                    }
                }

                if (ConstantVar.DATASET.size() == 1 && ConstantVar.REVIEW_DATABASE.size() != ConstantVar.DATASET.size()) {
                    ConstantVar.DATASET.remove(0);
                }


            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.DATASET.get(position));
            return convertView;
        }
    }

}













