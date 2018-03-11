package com.david916.ibuy.ibuy;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.view.ViewGroup;
import android.widget.*;
import android.os.AsyncTask;
import java.util.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String name;
    public String email;
    public String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent msg = getIntent();
        name = msg.getStringExtra("name").toString();
        email = msg.getStringExtra("email").toString();
        group = msg.getStringExtra("group").toString();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        new MainActivity.get_item_list().execute(email);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        final TextView username_bar = (TextView) findViewById(R.id.user_name);
        final TextView email_bar = (TextView) findViewById(R.id.user_email);
        email_bar.setText(email);
        username_bar.setText(name);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_page) {
            Intent intent=new Intent(MainActivity.this,add_item.class);
            intent.putExtra("email",email);
            intent.putExtra("name",name);
            intent.putExtra("group",group);
            //intent.putExtra("",item.getText().toString());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.group) {

            Intent intent=new Intent(MainActivity.this,group.class);
            intent.putExtra("email",email);
            intent.putExtra("name",name);
            intent.putExtra("group",group);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.about) {

            Intent intent=new Intent(MainActivity.this,about.class);
            startActivity(intent);

        } else if (id == R.id.sync) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setCancelable(true);
            dialog.setMessage("Sync Success!");
            dialog.show();

        } else if (id == R.id.setting) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void add_item(String item, String urgency, int itemid) {
        CardView.LayoutParams cardparam = new CardView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        cardparam.setMargins(10,10,10,10);
        CardView card = new CardView(this);
        card.setCardBackgroundColor(Color.TRANSPARENT);
        card.setRadius(5);
        card.setLayoutParams(cardparam);
        card.setPadding(10,10,10,10);

        LinearLayout.LayoutParams imgparam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        imgparam.setMargins(10,10,10,10);
        imgparam.gravity = Gravity.RIGHT;
        ImageView img = new ImageView(this);
        img.setPadding(20,20,20,20);
        img.setLayoutParams(imgparam);
        img.getLayoutParams().height = 150;
        img.getLayoutParams().width = 150;
        if(urgency.equals("Urgent")) {
            img.setImageResource(R.drawable.urgent);
        } else if(urgency.equals("Medium")) {
            img.setImageResource(R.drawable.medium);
        } else if(urgency.equals("Low")) {
            img.setImageResource(R.drawable.low);
        }
        card.addView(img);


        TextView item_info = new TextView(this);
        item_info.setHeight(150);
        item_info.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        item_info.setGravity(Gravity.CENTER);
        item_info.setText(item);
        item_info.setTextColor(Color.WHITE);
        item_info.setTextSize(20);
        card.addView(item_info);

        CardView.LayoutParams checkparam = new CardView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        checkparam.setMargins(10,10,10,10);
        checkparam.gravity = Gravity.RIGHT;
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(checkparam);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "" + itemid;
                new MainActivity.remove_item().execute(email,id);
                card.setVisibility(View.GONE);
            }
        });

        card.addView(checkBox);


        LinearLayout list_panel = (LinearLayout) findViewById(R.id.list_panel);
        list_panel.addView(card);
    }

    class get_item_list extends AsyncTask<String, Integer, ArrayList> {

        final ProgressBar bar3 = (ProgressBar) findViewById(R.id.bar3);


        public get_item_list() {

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
        protected ArrayList doInBackground(String... user_info) {

            String email = user_info[0];

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
                String sql = "select * from itemlist where user = '" + email + "'";
                //ResultSet类，用来存放获取的结果集！！
                ResultSet rs = statement.executeQuery(sql);

                ArrayList<ArrayList<String>> result_list = new ArrayList<>();

                while(rs.next()){
                    String level = rs.getString("level");
                    String itemid = rs.getString("itemid");
                    String name = rs.getString("name");
                    String quantity = rs.getString("quantity");
                    //add_item(name,level,itemid);
                    ArrayList<String> temparray = new ArrayList<>();
                    temparray.add(level);
                    temparray.add(itemid);
                    temparray.add(name);
                    temparray.add(quantity);
                    result_list.add(temparray);
                }
                rs.close();
                con.close();
                return result_list;
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
            bar3.setVisibility(View.INVISIBLE);
            if(result != null) {
                for(int i = 0 ; i < result.size(); i ++){
                    ArrayList<String> temp = (ArrayList<String>)result.get(i);
                    String level = temp.get(0);
                    int itemid = Integer.parseInt(temp.get(1));
                    String name = temp.get(2) + " : "  + temp.get(3);
                    add_item(name,level,itemid);
                }

            }
            else System.out.println("login fail");
        }


    }


    class remove_item extends AsyncTask<String, Integer, Boolean> {



        public remove_item() {

        }

        /*
        @Override
        protected void onPreExecute() {
            bar3.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            bar3.setProgress(progress);
        }
        */


        @Override
        protected Boolean doInBackground(String... user_info) {

            String email = user_info[0];
            String item_id = user_info[1];

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


                //要执行的SQL语句
                String sql = "select * from itemlist where user = '" + email + "' and itemid = " +
                        "'" + item_id + "'";
                //ResultSet类，用来存放获取的结果集！！

                Statement stmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    rs.deleteRow();
                }
                rs.close();
                con.close();
                return true;
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
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //bar1.setVisibility(View.INVISIBLE);
            if(result) {
            }
            else System.out.println("login fail");
        }


    }

}


