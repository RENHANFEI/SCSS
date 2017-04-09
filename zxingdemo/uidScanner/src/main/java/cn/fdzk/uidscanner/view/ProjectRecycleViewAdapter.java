//package cn.fdzk.uidscanner.view;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
//import cn.fdzk.uidscanner.R;
//import cn.fdzk.uidscanner.model.ProjectBean;
//
///**
// * Created by konie on 2017/4/8.
// */
//
//public class ProjectRecycleViewAdapter extends RecyclerView.Adapter<ProjectRecycleViewAdapter.ProjectRecycleViewHolder> {
//
//    private List<ProjectBean> data;
//
//    private OnItemClickListener onItemClickListener;
//
//    public ProjectRecycleViewAdapter(List<ProjectBean> data) {
//        this.data = data;
//    }
//
//    public void setData(List<ProjectBean> data) {
//        this.data = data;
//        notifyDataSetChanged();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    @Override
//    public ProjectRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
//
//        ProjectRecycleViewHolder holder = new ProjectRecycleViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(final ProjectRecycleViewHolder holder, final int position) {
//        holder.setContent(data.get(position));
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(holder.itemView, holder, position);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if (data == null) {
//            return 0;
//        }
//
//        return data.size();
//    }
//
//    public class ProjectRecycleViewHolder extends RecyclerView.ViewHolder {
//
//        public ProjectRecycleViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        public void setContent(ProjectBean itemData) {
//            itemView.setTag(itemData);
//            TextView textView = (TextView) itemView.findViewById(R.id.text);
//            textView.setText(itemData.getName());
//        }
//    }
//
//}
