package kr.co.company.pocketmoney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {
    ArrayList<MoneyItem> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.money_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoneyItem item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(MoneyItem item) {
        items.add(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView selected_day;
        TextView selected_io;
        ImageView selected_category;
        TextView selected_money;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selected_day = itemView.findViewById(R.id.selected_day);
            selected_io = itemView.findViewById(R.id.selected_io);
            selected_category = itemView.findViewById(R.id.selected_category);
            selected_money = itemView.findViewById(R.id.selected_money);
        }

        public void setItem(MoneyItem item) {
            selected_day.setText(item.getDate());
            selected_io.setText(item.getIO());
            selected_money.setText(item.getMoney());

            String category = item.getContent();
            if (category.equals("식비")) {
                selected_category.setImageResource(R.drawable.eat);
            } else if (category.equals("교통")) {
                selected_category.setImageResource(R.drawable.car);
            } else if (category.equals("준비물")) {
                selected_category.setImageResource(R.drawable.prepare);
            } else if (category.equals("문화")) {
                selected_category.setImageResource(R.drawable.hobby);
            } else if (category.equals("기타")) {
                selected_category.setImageResource(R.drawable.etc);
            }

        }
    }
}