package com.david916.ibuy.ibuy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Button add_item = (Button)  findViewById(R.id.btn_add_item);
        EditText item_input = (EditText)  findViewById(R.id.item);
        final EditText quantity = (EditText) findViewById(R.id.add_quantity);
        final EditText cost = (EditText) findViewById(R.id.add_cost);

        Intent msg = getIntent();
        String item_name = msg.getStringExtra("itemname").toString();
        String email = msg.getStringExtra("email").toString();
        String name = msg.getStringExtra("name").toString();
        String group = msg.getStringExtra("group").toString();
        String category = msg.getStringExtra("category").toString();


        if(item_name.length() == 0){
            item_input.setVisibility(View.VISIBLE);
        }

        if(category.length() != 0) {
            Spinner spinner = (Spinner) findViewById(R.id.category);
            SpinnerAdapter adapter= spinner.getAdapter(); //得到Spinner Adapter对象
            int count= adapter.getCount();
            for(int i=0;i<count;i++){
                if(category.equals(adapter.getItem(i).toString())){
                    spinner.setSelection(i,true);// 默认选中项
                    break;
                }
            }
        }

        TextView item = (TextView) findViewById(R.id.msg_item);


        item.setText("Item: "+ item_name);



        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantity.getText().toString().length() == 0 || cost.getText().toString().length() == 0) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(upload.this);
                    dialog.setCancelable(true);
                    dialog.setMessage("Missing something! Please input all the information.");
                    dialog.show();
                } else if(item_name.length() == 0) {
                    if(item_input.getText().toString().length() == 0) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(upload.this);
                        dialog.setCancelable(true);
                        dialog.setMessage("Missing item name!");
                        dialog.show();
                    } else {
                        new upload.Upload_item().execute(item_input.getText().toString(),email,name,group);
                    }
                } else {
                    new upload.Upload_item().execute(item_name,email,name,group);
                }
            }
        });

    }


    class Upload_item extends AsyncTask<String, Integer, Integer> {

        final EditText quantity = (EditText) findViewById(R.id.add_quantity);
        final EditText cost = (EditText) findViewById(R.id.add_cost);
        final Spinner level = (Spinner) findViewById(R.id.level);
        final Spinner category = (Spinner) findViewById(R.id.category);
        final ProgressBar bar3 = (ProgressBar) findViewById(R.id.bar3);

        String email;
        String name;
        String group;

        public Upload_item() {

        }

        @Override
        protected void onPreExecute() {
            bar3.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            bar3.setProgress(progress);
        }

        @Override
        protected Integer doInBackground(String... item) {

            String item_name = item[0];
            email = item[1];
            name = item[2];
            group = item[3];


            int num_quantity = Integer.parseInt(quantity.getText().toString());
            int num_cost = Integer.parseInt(cost.getText().toString());
            String str_level = level.getSelectedItem().toString();
            String str_category = category.getSelectedItem().toString();

            Connection con;
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://119.29.164.98:3306/ibuy";
            String user = "root";
            String pw = "root";

            //String mail = MainActivity.email;

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,pw);
                if(!con.isClosed())
                    System.out.println("Succeeded connecting to the Database!");
                Statement statement = con.createStatement();

                //System.out.println("name:"+item_name+",quantity:"+num_quantity+",cost:"+num_cost+",urgency:"+str_level+",category:"+str_category);
                String sql = "INSERT INTO `itemlist`(`name`, `quantity`, `cost`, `level`, `category`, `user`) VALUES ('"+item_name+"','"+num_quantity+"','"+num_cost+"','"+str_level+"','"+str_category+"','"+email+"')";

                System.out.println(sql);
                statement.execute(sql);
                con.close();
                return 0;
            } catch(ClassNotFoundException e) {
                //数据库驱动类异常处理
                System.out.println("Sorry,can`t find the Driver!");
                e.printStackTrace();
            } catch(SQLException e) {
                //数据库连接失败异常处理
                e.printStackTrace();
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return -1;

        }

        @Override
        protected void onPostExecute(Integer result) {
            bar3.setVisibility(View.INVISIBLE);
            if(result == 0) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(upload.this);
                dialog.setMessage("Add item success");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(upload.this,MainActivity.class);
                        intent.putExtra("email",email);
                        intent.putExtra("name",name);
                        intent.putExtra("group",group);
                        startActivity(intent);
                    }
                });

                dialog.show();

            }
            else if(result == -1) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(upload.this);
                dialog.setCancelable(true);
                dialog.setTitle("Network Error!");
                dialog.setMessage("oops! The network is not working, please try again later.");
                dialog.show();
            }
        }


    }




}
