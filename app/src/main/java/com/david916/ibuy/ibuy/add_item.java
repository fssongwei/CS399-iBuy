package com.david916.ibuy.ibuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;



public class add_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent msg = getIntent();
        String email = msg.getStringExtra("email").toString();
        String name = msg.getStringExtra("name").toString();
        String group = msg.getStringExtra("group").toString();


        final Button btn_next = (Button) findViewById(R.id.next);
        final EditText item = (EditText) findViewById(R.id.item);

        //Database database = new Database(this);

        final Button btn_1 = (Button) findViewById(R.id.c1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_2 = (Button) findViewById(R.id.c2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_3 = (Button) findViewById(R.id.c3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_4 = (Button) findViewById(R.id.c4);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_5 = (Button) findViewById(R.id.c5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_6 = (Button) findViewById(R.id.c6);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_7 = (Button) findViewById(R.id.c7);
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });


        final Button btn_8 = (Button) findViewById(R.id.c8);
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_9 = (Button) findViewById(R.id.c9);
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_10 = (Button) findViewById(R.id.c10);
        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_11 = (Button) findViewById(R.id.c11);
        btn_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        final Button btn_12 = (Button) findViewById(R.id.c12);
        btn_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", btn_1.getText().toString());
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(add_item.this, upload.class);
                intent.putExtra("itemname", item.getText().toString());
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("group", group);
                intent.putExtra("category", "");

                //intent.putExtra("",item.getText().toString());

                startActivity(intent);
            }
        });

        //new add_item.Category_generator().execute();
        final Button add_category = (Button) findViewById(R.id.add_category);

        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_item.this, add_category.class);
                startActivity(intent);
            }
        });
    }



    class Category_generator extends AsyncTask <String, Integer, ArrayList> {

        final Spinner category_bar = (Spinner) findViewById(R.id.category);

        public Category_generator() {

        }

        /*
        @Override
        protected void onPreExecute() {
            bar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            bar1.setProgress(progress);
        }
        */

        @Override
        protected ArrayList<String> doInBackground(String... user_info) {

            ArrayList<String> category_array = new ArrayList<String>();

            Connection con;
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://119.29.164.98:3306/ibuy";
            String user = "root";
            String pw = "root";
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,pw);
                if(!con.isClosed())
                    System.out.println("Succeeded connecting to the Database!");
                Statement statement = con.createStatement();

                //要执行的SQL语句
                String sql = "select * from category";
                //ResultSet类，用来存放获取的结果集！！
                ResultSet rs = statement.executeQuery(sql);

                while(rs.next()){

                    //category_array.add(rs.getString("category"));
                    //category_array.add(rs.getString("category"));
                   // category_array.add(rs.getString("category"));

                    for(int i = 0 ; i < 10; i ++){
                        System.out.println(rs.getString("category"));
                    }

                }
                rs.close();
                con.close();

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
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList result) {

        }


    }


}
