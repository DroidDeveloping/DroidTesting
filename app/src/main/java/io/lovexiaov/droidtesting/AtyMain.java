package io.lovexiaov.droidtesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import java.io.IOException;
import java.io.InputStream;

import okio.BufferedSource;
import okio.Okio;

public class AtyMain extends AppCompatActivity {

    private String appId, appKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

        try {
            InputStream inputStream = getAssets().open("leancloud.properties");
            BufferedSource buffer = Okio.buffer(Okio.source(inputStream));
//            String s = buffer.readUtf8();
            while (!buffer.exhausted()) {
                String line = buffer.readUtf8Line();
                String[] kv = line.split("=");
                if (kv[0].equals("appId")) {
                    appId = kv[1];
                } else if (kv[0].equals("appKey")) {
                    appKey = kv[1];
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(AtyMain.this, "Cannot get AppId and AppKey.", Toast.LENGTH_SHORT).show();
        }
        AVOSCloud.initialize(this, appId, appKey);
//        AVAnalytics.trackAppOpened(getIntent());
        AVObject testObject = new AVObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
