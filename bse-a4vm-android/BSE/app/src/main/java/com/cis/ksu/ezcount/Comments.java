package com.cis.ksu.ezcount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.util.HashSet;
import java.util.Set;

public class Comments extends AppCompatActivity {

    EditText comments = null;
    Button save = null;
    String bullKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        comments = (EditText)findViewById(R.id.comments);
        save = (Button) findViewById(R.id.saveComments);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        bullKey = getIntent().getStringExtra("bullKey");
        Toast.makeText(getApplicationContext(),bullKey,Toast.LENGTH_SHORT).show();
        Set commentText = (HashSet)SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_COMMENTS_INFO, bullKey);
        if(commentText!=null && !commentText.isEmpty())
        {
            if(commentText.iterator().hasNext())
            {
                comments.setText(commentText.iterator().next().toString().trim());
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set data = new HashSet();
                if(comments.getText().toString().trim().length() > 0)
                {
                    data.add(comments.getText().toString().trim());
                    SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_COMMENTS_INFO, bullKey, data);
                    comments.setText(comments.getText().toString().trim());
                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"nothing to save",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
