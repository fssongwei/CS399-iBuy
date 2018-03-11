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

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button btn_reg = (Button) findViewById(R.id.reg);
        final EditText reg_email = (EditText)   findViewById(R.id.reg_email);
        final EditText reg_password = (EditText)   findViewById(R.id.reg_password);
        final EditText reg_name = (EditText)   findViewById(R.id.reg_name);


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reg_name.getText().toString().length() == 0 || reg_email.getText().toString().length() == 0  || reg_password.getText().toString().length() == 0 ){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                    dialog.setCancelable(true);
                    dialog.setTitle("You missed something!");
                    dialog.setMessage("Please check again and make sure you enter everything!");
                    dialog.show();
                } else
                if(reg_password.getText().toString().length() < 6 ) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                    dialog.setCancelable(true);
                    dialog.setTitle("Easy Password!");
                    dialog.setMessage("Your password is too easy, you should use at least 6 digits password.");
                    dialog.show();
                } else {
                    new Register.Register_tool().execute(reg_name.getText().toString(), reg_email.getText().toString(), reg_password.getText().toString());
                }
            }
        });

    }


    class Register_tool extends AsyncTask<String, Integer, Integer> {

        final TableLayout login_panel = (TableLayout) findViewById(R.id.login_panel);
        final TextView username_bar = (TextView) findViewById(R.id.reg_name);
        final TextView email_bar = (TextView) findViewById(R.id.email);
        final ProgressBar bar2 = (ProgressBar) findViewById(R.id.bar2);


        public Register_tool() {

        }

        @Override
        protected void onPreExecute() {
            bar2.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            bar2.setProgress(progress);
        }

        @Override
        protected Integer doInBackground(String... user_info) {

            String sql_name = user_info[0];
            String sql_email = user_info[1];
            String sql_password = user_info[2];

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

                String checksql = "select * from login";
                ResultSet rs = statement.executeQuery(checksql);
                String getusername = null;
                String getemail = null;
                while(rs.next()){
                    getusername = rs.getString("username");
                    getemail = rs.getString("email");
                    if(getemail.equals(sql_email))
                    {
                        return 1;//email exist
                    }
                }
                rs.close();

                String sql = "INSERT INTO `login`(`username`, `email`, `password`) VALUES ('"+sql_name+"','"+sql_email+"','"+sql_password+"')";
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
            bar2.setVisibility(View.INVISIBLE);
            if(result == 0) {
                System.out.println("register success");

                AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                dialog.setTitle("Register success!");
                dialog.setMessage("Congratulation! Now you can enjoy iBuy.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Try it now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(Register.this,Login.class);
                        startActivity(intent);
                    }
                });

                dialog.show();

            }
            else if(result == -1) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                dialog.setCancelable(true);
                dialog.setTitle("Network Error!");
                dialog.setMessage("oops! The network is not working, please try again later.");
                dialog.show();
            }
            else if(result == 1) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                dialog.setCancelable(true);
                dialog.setTitle("Email Occupation!");
                dialog.setMessage("The Email you entered has been used. If it's yours, you can use it to login the iBuy.");
                dialog.show();
            }
        }


    }

}
