package windy.viewbinding;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import windy.viewbinding.annotation.BindResource;
import windy.viewbinding.annotation.BindView;
import windy.viewbinding.annotation.OnClick;
import windy.viewbinding.binder.ViewBinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    public TextView tvHello;
    @BindView(R.id.imv)
    public ImageView imv;
    @BindResource(resourceId = R.string.app_name)
    public String name;
    @OnClick(R.id.btn_show_toast)
    public void showToast(){
        Toast.makeText(this, "Hello Windy.F",Toast.LENGTH_SHORT).show();
        imv.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewBinder.getBinder().bindView(this);
        tvHello.setText("Windy.F");
    }

}
