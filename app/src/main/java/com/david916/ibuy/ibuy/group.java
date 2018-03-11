package com.david916.ibuy.ibuy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.ViewGroup.LayoutParams;

public class group extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent msg = getIntent();
        String email = msg.getStringExtra("email").toString();
        String name = msg.getStringExtra("name").toString();
        String group = msg.getStringExtra("group").toString();

        if (group.isEmpty()) {
            TableRow.LayoutParams param = new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f);

            TableLayout tableLayout = (TableLayout) findViewById(R.id.upload_tablelayout);
            TableRow head_row = new TableRow(this);
            TextView head = new TextView(this);
            head.setText("Join a group!");
            head.setPadding(20, 20, 20, 20);
            head.setTextColor(Color.WHITE);
            head.setTextSize(40);
            head.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            head.setLayoutParams(param);
            head_row.setLayoutParams(param);
            head_row.addView(head);

            TableRow no_group_msg_row = new TableRow(this);

            TextView no_group_msg = new TextView(this);
            no_group_msg.setText("You haven't joined any group yet, why not try to join a group or start a new group?\n\n - To join a group, just enter one of the group member's email address. \n\n - To start a new group, just enter a member's email address who isn't in any group.");
            no_group_msg.setPadding(10, 10, 10, 10);
            no_group_msg.setTextColor(Color.WHITE);
            no_group_msg.setTextSize(20);
            //no_group_msg.setWidth(400);
            no_group_msg.setLayoutParams(param);

            no_group_msg_row.addView(no_group_msg);
            //no_group_msg_row.setLayoutParams(param);

            tableLayout.addView(head_row);
            tableLayout.addView(no_group_msg_row);

            TableRow input_email_row = new TableRow(this);
            input_email_row.setLayoutParams(param);

            EditText input_email = new EditText(this);
            input_email.setTextColor(Color.WHITE);
            input_email.setTextSize(30);
            input_email.setLayoutParams(param);

            input_email_row.addView(input_email);
            tableLayout.addView(input_email_row);

            TableRow btn_row = new TableRow(this);
            btn_row.setLayoutParams(param);
            Button btn = new Button(this);
            btn.setPadding(20, 20, 20, 20);
            btn.setTextSize(30);
            btn.setText("Next");
            btn.setBackgroundColor(Color.CYAN);
            btn.setLayoutParams(param);
            btn.setAllCaps(false);
            btn.setId(R.id.btn_group_next);
            btn_row.addView(btn);
            tableLayout.addView(btn_row);

        }
    }
}
