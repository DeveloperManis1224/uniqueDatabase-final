package com.unique.app.adssan;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;

import com.unique.app.adssan.R;

public class ActivitySignUp2 extends AppCompatActivity {

    TextView btn_getMail;
    EditText studentName;
    private LinearLayout formLyt,btnLyt;
    private Spinner collegeSpinner;
    private String[] collegeList = new String[]{"Select College","ACS Medical College and Hospital, Chennai",
            "Annaii Medical College and Hospital, Pennalur, Kanchipuram",
            "Annapoorna Medical College & Hospital, Salem",
            "Chengalpattu Medical College, Chengalpattu",
            "Chettinad Hospital & Research Institute, Kanchipuram",
            "Christian Medical College, Vellore",
            "Coimbatore Medical College, Coimbatore",
            "Dhanalakshmi Srinivasan Medical College and Hospital,Perambalur",
            "ESI-PGIMSR,ESI Hospital,K.K Nagar,Chennai",
            "Government Dharmapuri Medical College, Dharmapuri",
            "Government Medical College & ESIC Hospital, Coimbatore, TamilNadu",
            "Government Medical College, Omandurar",
            "Government Medical College, Pudukottai, Tamil Nadu",
            "Government Sivagangai Medical College, Sivaganga",
            "Government Thiruvannamalai Medical College, Thiruvannamalai",
            "Government Vellore Medical College, Vellore",
            "Government Villupuram Medical College, Villupuram",
            "IRT Perundurai Medical College, Perundurai",
            "KanyaKumari Government Medical College, Asaripallam",
            "K A P Viswanathan Government Medical College, Trichy",
            "Karpagam Faculty of Medical Sciences & Research, Coimbatore",
            "Karpaga Vinayaga Institute of Medical Sciences,Maduranthagam",
            "Kilpauk Medical College, Chennai",
            "Madha Medical College and Hospital, Thandalam, Chennai",
            "Madras Medical College, Chennai",
            "Madurai Medical College, Madurai",
            "Meenakshi Medical College and Research Institute, Enathur",
            "Melmaruvathur Adiparasakthi Instt. Medical Sciences and Research",
            "Mohan Kumaramangalam Medical College, Salem",
            "Ponnaiyah Ramajayam Institute of Medical Sciences, Manamai-Nellur",
            "PSG Institute of Medical Sciences, Coimbatore",
            "Rajah Muthiah Medical College, Annamalainagar",
            "Saveetha Medical College and Hospital, Kanchipuram",
            "Shri Satya Sai Medical College and Research Institute, Kancheepuram",
            "Sree Balaji Medical College and Hospital, Chennai",
            "Sree Mookambika Institute of Medical Sciences, Kanyakumari",
            "Sri Muthukumaran Medical College,Chennai",
            "Sri Ramachandra Medical College & Research Institute, Chennai",
            "SRM Medical College Hospital & Research Centre, Kancheepuram",
            "Stanley Medical College, Chennai",
            "Tagore Medical College and Hospital, Chennai",
            "Thanjavur Medical College,Thanjavur",
            "Theni Government Medical College,Theni",
            "Thiruvarur Govt. Medical College, Thiruvarur",
            "Thoothukudi Medical College, Thoothukudi",
            "Tirunelveli Medical College,Tirunelveli",
            "Trichy SRM Medical College Hospital & Research Centre, Trichy",
            "Velammal Medical College Hospital and Research Institute, Madurai",
            "Vinayaka Missions Kirupananda Variyar Medical College, Salem","Others"};

    public void onClickGoogle(View v)
    {
        Intent googlePicker = AccountPicker
                .newChooseAccountIntent(null, null, new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE},
                        true, null, null, null, null);
        startActivityForResult(googlePicker, 123);
    }

    public void onClickSubmit(View v)
    {
        if(!studentName.getText().toString().isEmpty() &&
                !collegeSpinner.getSelectedItem().toString().equalsIgnoreCase("Select College") &&
                !btn_getMail.getText().toString().isEmpty()) {
            Intent in = new Intent(ActivitySignUp2.this, ActivitySignUp.class);
            in.putExtra("name", "" + studentName.getText().toString().trim());
            in.putExtra("college", "" + collegeSpinner.getSelectedItem().toString());
            in.putExtra("email", "" + btn_getMail.getText().toString().trim());
            startActivity(in);
        }
        else
        {
            Toast.makeText(this, "Invalid details...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        btn_getMail = findViewById(R.id.edt_accounts);
        studentName = findViewById(R.id.edt_name);
        //collegeName = findViewById(R.id.edt_college);
        formLyt = findViewById(R.id.frm_lyt);
        collegeSpinner = findViewById(R.id.edt_college);

        btnLyt = findViewById(R.id.btn_lyt);

//        btnLyt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                formLyt.setVisibility(View.VISIBLE);
//                btnLyt.setVisibility(View.GONE);
//            }
//        });


        final ArrayAdapter<String> studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, collegeList);

        collegeSpinner.setAdapter(studentAdapter);

    }
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            formLyt.setVisibility(View.VISIBLE);
            btnLyt.setVisibility(View.GONE);
            btn_getMail.setText(""+accountName);
            Log.d("ggggggggggggg", accountName);
        }
    }


}
