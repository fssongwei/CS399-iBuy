package com.david916.ibuy.ibuy;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {

    public String name;
    public String email;
    public String group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btn_login = (Button) findViewById(R.id.login);
        final Button btn_register = (Button) findViewById(R.id.register);
        final EditText email = (EditText)   findViewById(R.id.email);
        final EditText password = (EditText)   findViewById(R.id.password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login.check_login().execute(email.getText().toString(),password.getText().toString());
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }

    class check_login extends AsyncTask<String, Integer, Boolean> {

        final ProgressBar bar1 = (ProgressBar) findViewById(R.id.bar1);


        public check_login() {

        }

        @Override
        protected void onPreExecute() {
            bar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            bar1.setProgress(progress);
        }

        @Override
        protected Boolean doInBackground(String... user_info) {

            String sql_email = user_info[0];
            String sql_password = user_info[1];

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
                String sql = "select * from login";
                //ResultSet类，用来存放获取的结果集！！
                ResultSet rs = statement.executeQuery(sql);

                String getusername = null;
                String getpassword = null;
                while(rs.next()){
                    getusername = rs.getString("email");
                    if(getusername.equals(sql_email))
                    {
                        getpassword = rs.getString("password");
                        if(getpassword.equals(sql_password)){
                            name = rs.getString("username");
                            email = sql_email;
                            group = rs.getString("group");
                            if(group == null){
                                group = "";
                            }
                            return true;
                        }
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
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            bar1.setVisibility(View.INVISIBLE);
            if(result) {
                System.out.println("login success");

                Intent intent=new Intent(Login.this,MainActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("name",name);
                intent.putExtra("group",group);
                startActivity(intent);

            }
            else System.out.println("login fail");
        }


    }
}
