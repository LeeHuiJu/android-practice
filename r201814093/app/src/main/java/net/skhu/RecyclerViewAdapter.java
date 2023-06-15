package net.skhu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1, textView2;
        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
        }

        public void setData(int index) {
            Student student = arrayList.get(index);
            textView1.setText(student.getStudentId());
            textView2.setText(student.getStudentName());
        }

        @Override
        public void onClick(View view) {
            int index = super.getAdapterPosition();
            MainActivity activity = (MainActivity) textView1.getContext();
            activity.onMemoClicked(index);
        }
    }

    LayoutInflater layoutInflater;
    ArrayList<Student> arrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Student> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.memo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData(index);
    }
}