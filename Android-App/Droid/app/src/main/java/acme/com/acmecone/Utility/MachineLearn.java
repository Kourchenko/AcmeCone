package acme.com.acmecone.Utility;

/**
 * Created by Rob on 4/8/2016.
 */
public class MachineLearn {


    // Machine learning sample
    /*
    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.my_list_item, STOCK_ITEMS);
    final AutoCompleteTextView stockItem = (AutoCompleteTextView) view.findViewById(R.id.orderform_stock_item);
    stockItem.setAdapter(adapter);

    // Remember past inputs -- Machine Learning Test
    ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MACHINE_LEARNING_name);
    final AutoCompleteTextView name = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_name);
    name.setAdapter(nameAdapter);

    ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MACHINE_LEARNING_email);
    final AutoCompleteTextView email = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_email);
    email.setAdapter(emailAdapter);

    ArrayAdapter<String> companyAdapter = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MACHINE_LEARNING_company);
    final AutoCompleteTextView company = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_company);
    company.setAdapter(companyAdapter);

    ArrayAdapter<String> manfAdapter = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MACHINE_LEARNING_manf);
    final AutoCompleteTextView manf = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_manufacturer);
    manf.setAdapter(manfAdapter);


    getOrderFormName = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_name);
    getOrderFormEmail = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_email);
    getOrderFormCompany = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_company);
    getOrderFormMF = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_manufacturer);

    stockQ = (EditText) view.findViewById(R.id.orderform_stock_num);

    stockB = (Button) view.findViewById(R.id.orderform_stock_submitB);
    stockB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String orderName = getOrderFormName.getText().toString().trim();
            final String orderEmail = getOrderFormEmail.getText().toString().trim();
            final String orderCompany = getOrderFormCompany.getText().toString().trim();
            final String orderMF = getOrderFormMF.getText().toString().trim();

            final String stockN = stockQ.getText().toString();
            final String stockI = stockItem.getText().toString().trim();

            if (!stockN.isEmpty() && !stockI.isEmpty()) {

                if (ConstantVar.NAME.isEmpty() && ConstantVar.EMAIL.isEmpty()
                        && ConstantVar.COMPANY.isEmpty() && ConstantVar.MANUFACTURER.isEmpty()) {
                    ConstantVar.NAME += orderName;
                    ConstantVar.EMAIL += orderEmail;
                    ConstantVar.COMPANY += orderCompany;
                    ConstantVar.MANUFACTURER += orderMF;

                    mMessage += stockN + ": " + stockI + "\n";

                    ConstantVar.ORDER_MESSAGE += orderName + "\n"
                            + orderEmail + "\n"
                            + orderCompany + "\n"
                            + "Manufacturer: " + orderMF;

                    ConstantVar.ORDER_SEND_DATABASE.put(mMessage, mMessage);
                    ConstantVar.ORDER_REVIEW_DATABASE.put(mMessage, mMessage);
                    stockQ.setText("");
                    stockItem.setText("");

                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();
                } else {
                    ConstantVar.ORDER_SEND_DATABASE.put(mMessage, mMessage);
                    ConstantVar.ORDER_REVIEW_DATABASE.put(mMessage, mMessage);
                    stockQ.setText("");
                    stockItem.setText("");

                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
            }
        }
    });

    orderPlaceB = (Button) view.findViewById(R.id.orderform_place_order);
    orderPlaceB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String machineLearningName = getOrderFormName.getText().toString();
            final String machineLearningEmail = getOrderFormEmail.getText().toString();
            final String machineLearningCompany = getOrderFormCompany.getText().toString();
            final String machineLearningManf = getOrderFormMF.getText().toString();
            final String orderQ = stockQ.getText().toString();
            final String orderI = stockItem.getText().toString();

            if (!ConstantVar.NAME.isEmpty() && !ConstantVar.EMAIL.isEmpty()) {


                try {
                    if (!ConstantVar.MACHINE_LEARNING_name.contains(machineLearningName)
                            || !ConstantVar.MACHINE_LEARNING_email.contains(machineLearningEmail)
                            || !ConstantVar.MACHINE_LEARNING_company.contains(machineLearningCompany)
                            || !ConstantVar.MACHINE_LEARNING_manf.contains(machineLearningManf)) {

                        ConstantVar.MACHINE_LEARNING_name.add(machineLearningName);
                        ConstantVar.MACHINE_LEARNING_email.add(machineLearningEmail);
                        ConstantVar.MACHINE_LEARNING_company.add(machineLearningCompany);
                        ConstantVar.MACHINE_LEARNING_manf.add(machineLearningManf);
                    }
                }
                finally {

                    if (!orderQ.isEmpty() && !orderI.isEmpty()) {

                        mMessage += orderQ + ": " + orderI + "\n\n";

                        ConstantVar.ORDER_REVIEW_DATABASE.put(mMessage, mMessage);
                        ConstantVar.ORDER_SEND_DATABASE.put(mMessage, mMessage);

                        Intent reviewIntent = new Intent(context, OrderReviewFragment.class);
                        startActivity(reviewIntent);

                        stockQ.setText("");
                        stockItem.setText("");
                        mMessage = "";
                        getOrderFormName.setText("");
                        getOrderFormEmail.setText("");
                        getOrderFormCompany.setText("");
                        getOrderFormMF.setText("");
                    } else {

                        Intent reviewIntent = new Intent(context, OrderReviewFragment.class);
                        startActivity(reviewIntent);

                        stockQ.setText("");
                        stockItem.setText("");
                        mMessage = "";
                        getOrderFormName.setText("");
                        getOrderFormEmail.setText("");
                        getOrderFormCompany.setText("");
                        getOrderFormMF.setText("");

                    }
                }

            } else if (!getOrderFormName.getText().toString().isEmpty()
                    || !getOrderFormEmail.getText().toString().isEmpty()
                    && !ConstantVar.ORDER_SEND_DATABASE.isEmpty()) {

                ConstantVar.NAME += getOrderFormName;
                ConstantVar.EMAIL += getOrderFormEmail;
                ConstantVar.COMPANY += getOrderFormCompany;
                ConstantVar.MANUFACTURER += getOrderFormMF;

                Intent reviewIntent = new Intent(context, OrderReviewFragment.class);
                startActivity(reviewIntent);

                getOrderFormName.setText("");
                getOrderFormEmail.setText("");
                getOrderFormCompany.setText("");
                getOrderFormMF.setText("");


            } else {
                Toast.makeText(context, "Fill Out Name & Email...", Toast.LENGTH_SHORT).show();
            }
        }
    });
    */
}
