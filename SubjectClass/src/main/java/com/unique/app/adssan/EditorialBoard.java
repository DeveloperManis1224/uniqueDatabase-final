package com.unique.app.adssan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.unique.app.adssan.Adapter.EditAuthorAdapter;

import java.util.ArrayList;

public class EditorialBoard extends AppCompatActivity {

    ArrayList<DataEditorial> name=new ArrayList<>();
    private RecyclerView listView;
    private RecyclerView.LayoutManager mLayoutManager;

    private Integer imgList[] = { R.drawable.dr1,
            R.drawable.dr2, R.drawable.dr3,
            R.drawable.dr4, R.drawable.dr5,
            R.drawable.dr6, R.drawable.dr7,
            R.drawable.dr8, R.drawable.dr9,
            R.drawable.dr10, R.drawable.dr11,
            R.drawable.dr12
            };
    private String drNameList[] = new String[]{"Dr. P.Meganathan., MBBS., MD (Paed). Editor in Chief",
    "Dr. L.Ramya Meganathan MBBS. Co-Editor Subjects Contributors",
    "Dr. S.Anton Remith Riche MBBS., DCH., DNB.,",
    "Dr. S.Aravind MBBS., MD (GM)",
    "Dr. P.T.Lakshmipriya, MBBS.,",
    "Dr. S.Meenoo MBBS., MS(OG)., DNB (OG).,",
    "Dr. M.H.Mohamed Muzzammil., MBBS., MD (Paed)",
    "Dr. J.Princy Felicia., MBBS., MD (SPM)",
    "Dr. A.Ramanathan.,MBBS.,",
    "Dr. P.Sivakaran,MBBS.,MS(ORTHO)",
    "Dr. J.Vasanth Kumar., MBBS.,",
    "Dr. S.P.Vinoth Kumar., MBBS., MD (Paed)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial_board);

        listView = (RecyclerView) findViewById(R.id.dr_list);
        listView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);

        for(int i = 0;i<12;i++)
        {
            name.add(new DataEditorial(drNameList[i],imgList[i]));
        }
        EditAuthorAdapter dataAdapter = new EditAuthorAdapter(name);
        listView.setAdapter(dataAdapter);


    }
}
