package com.example.javacode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.javacode.databinding.LayoutJavacodeBinding;
import com.example.javacode.model.JavacodeModel;
import java.util.List;

public class JavaCodeAdepter extends RecyclerView.Adapter<JavaCodeAdepter.Holder> {

  private List<JavacodeModel> list;
  private Context context;
  private OnItemClickCallBack call;

  public JavaCodeAdepter(List<JavacodeModel> list, Context context, OnItemClickCallBack call) {
    this.list = list;
    this.context = context;
    this.call = call;
  }

  public interface OnItemClickCallBack {
    void click(JavacodeModel model, int pos);
  }

  static class Holder extends RecyclerView.ViewHolder {
    LayoutJavacodeBinding binding;

    public Holder(LayoutJavacodeBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutJavacodeBinding binding =
        LayoutJavacodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(Holder holder, int pos) {
    var get = list.get(pos);
    holder.binding.id.setText(get.getId());
    holder.binding.title.setText(get.getTitle());
    holder.itemView.setOnClickListener(
        i -> {
          call.click(get, pos);
        });
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
