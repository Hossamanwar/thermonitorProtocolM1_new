package guc.thermo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] list) {
        super(context, R.layout.customlist, list);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View customView = LayoutInflater.from(getContext()).inflate(R.layout.customlist, viewGroup, false);
        String item = getItem(pos);
        TextView textView = customView.findViewById(R.id.listEt);
        ImageView imageView = customView.findViewById(R.id.listIv);

        textView.setText(item);

        if (item.equals("SQL")) {
            imageView.setImageResource(R.drawable.sql);
        } else if (item.equals("Java")) {
            imageView.setImageResource(R.drawable.java);
        } else if (item.equals("Java Script")) {
            imageView.setImageResource(R.drawable.java_script);
        } else if (item.equals("C#")) {
            imageView.setImageResource(R.drawable.c_sharp);
        } else if (item.equals("python")) {
            imageView.setImageResource(R.drawable.python);
        } else if (item.equals("C++")) {
            imageView.setImageResource(R.drawable.c_plus);
        }
        return customView;
    }
}
