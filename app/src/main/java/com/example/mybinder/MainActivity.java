package com.example.mybinder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText editText;
    private Button button;
    private TextView textView;
    private IPerson iPerson;
    private PersonConnection personConnection = new PersonConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        Intent service = new Intent("android.intent.action.AIDLService");
        service.setPackage("com.example.mybinder");
        bindService(service,personConnection,BIND_AUTO_CREATE);
        button.setOnClickListener(this);

    }

    private void bindViews() {

        editText = (EditText) findViewById(R.id.edit_Text);
        button = (Button) findViewById(R.id.button_query);
        textView = (TextView) findViewById(R.id.show_view);
    }

    @Override
    public void onClick(View view) {
        String number = editText.getText().toString();
        int num = Integer.valueOf(number);
        try {
            textView.setText(iPerson.queryPerson(num));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        editText.setText("");
    }


    private final class PersonConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iPerson = IPerson.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iPerson = null;
        }
    }
}
