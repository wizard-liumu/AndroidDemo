package cn.itcast.gwzx.example.administrator.corelmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.itcast.gwzx.R;
import cn.itcast.gwzx.corelmarket.bean.CartBean;
import cn.itcast.gwzx.corelmarket.database.SQLiteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteHelper sqLiteHelper;
    TextView content_name, content_price, content_introduction;
    Button btn;
    ImageView pic3;
    List<CartBean> list;
    ListView mListView;
    String[] titles = {
            "Xiaomi/小米 小米CC9", "Xiaomi/小米 Redmi Note 8", "Xiaomi/小米 Redmi 8A", "DOOV/朵唯 D11", "DOOV/朵唯 20190808", "Coolpad/酷派 1831-A0 酷玩7C 天猫", "DOOV/朵唯 X11 Pro", "21KE qin2 pro", "小辣椒 红辣椒 9E", "小辣椒 7P"};
    String[] introductions = {
            "Xiaomi/小米CC9智能4800万三摄3200万自拍照手机",
            "Xiaomi/小米 红米note8 4800万主摄拍照游戏手机",
            "大电量大屏幕智能游戏学生老年人手机",
            "DOOV/朵唯X11 Pro学生价游戏智能手机全面屏",
            "朵唯9X水滴屏八核128G智能手机全网通",
            "正品coolpad/酷派酷玩7c学生价智能手机全网通",
            "DOOV/朵唯X11 Pro学生价游戏智能手机全面屏",
            "小米有品多亲ai2pro手机qin2代小爱同学学生特小屏",
            "小辣椒7P智能手机",
            "正品小辣椒安卓智能机超薄学生价4g全网通"

    };
    String[] prices = {
            "￥1759起",
            "￥1199起",
            "￥699起",
            "￥688起",
            "￥489起",
            "￥479起",
            "￥689起",
            "￥599起",
            "￥449起",
            "￥449起"
    };
    int[] pics = {
            R.drawable.table,
            R.drawable.apple,
            R.drawable.cake,
            R.drawable.wireclothes,
            R.drawable.kiwifruit,
            R.drawable.scarf,
            R.drawable.timg,
            R.drawable.hefe,
            R.drawable.blick,
            R.drawable.mot
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        mListView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.content_btn);
        pic3 = (ImageView) findViewById(R.id.pic3);
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
        initData();
    }

    protected void initData() {
        sqLiteHelper = new SQLiteHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic3:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                vh = new ViewHolder();
                vh.title = (TextView) convertView.findViewById(R.id.content_name);
                vh.price = (TextView) convertView.findViewById(R.id.content_price);
                vh.introduction = (TextView) convertView.findViewById(R.id.content_introduction);
                vh.iv = (ImageView) convertView.findViewById(R.id.iv);
                vh.btn = (Button) convertView.findViewById(R.id.content_btn);
                convertView.setTag(vh);
                final ViewHolder finalVh = vh;
                vh.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String CartName = finalVh.title.getText().toString();
                        String CartPrice = finalVh.price.getText().toString();
                        String CartIntroduction = finalVh.introduction.getText().toString();
                        if (sqLiteHelper.insertData(CartName, CartPrice, CartIntroduction)) {
                            Toast.makeText(MainActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.title.setText(titles[position]);
            vh.price.setText(prices[position]);
            vh.introduction.setText(introductions[position]);
            vh.iv.setBackgroundResource(pics[position]);
            return convertView;
        }
    }

    class ViewHolder {
        TextView title, price, introduction;
        Button btn;
        ImageView iv;
    }

    private class ShopCartActivity {
    }
}
