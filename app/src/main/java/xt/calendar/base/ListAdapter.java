package xt.calendar.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * @描述: listView对应的adapter基类
 * @更新描述:
 */
public abstract class ListAdapter<T> extends BaseAdapter {
	protected List<T>	mDatas;

	public List<T> getDatas() {
		if(null == mDatas){
			return new ArrayList<>();
		}
		return mDatas;
	}
	public ArrayList<String> idList = new ArrayList<>();//被选中的id集合
	public ListAdapter(List<T> datas) {
		this.mDatas = datas;
	}

	@Override
	public int getCount()
	{
		if (mDatas != null) { return mDatas.size(); }
		return 0;
	}

	public void upDateList( List<T> list,boolean isRefresh){
		if(list == null){
			return;
		}
		if(mDatas == null){
			mDatas = new ArrayList<T>();
		}

		if(isRefresh){
			mDatas.clear();
			mDatas.addAll(list);
		}else {
			mDatas.addAll(list);
		}
		notifyDataSetChanged();

	}
	@Override
	public Object getItem(int position)
	{
		if (mDatas != null) { return mDatas.get(position); }
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ListHolder holder = null;
		if (convertView == null)
		{
			// 没有复用

			// 1. holder初始化
			holder = getHolder(parent);

			// 2. 加载View
			convertView = holder.getRootView();
		}
		else
		{
			// 有复用
			holder = (ListHolder) convertView.getTag();
		}

		// 设置数据,给View铺数据
		holder.setData(mDatas.get(position),position);

		return convertView;
	}


	protected abstract ListHolder<T> getHolder( ViewGroup parent);

}