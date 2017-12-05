package cmj.adimageview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * @author:  mingancai
 * @Time: 2017/12/5 0005.
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var data: ArrayList<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (data == null)
            return 0
        else
            return data?.size as Int
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (position > 0 && position % 6 == 0) {
            holder?.setVisible(holder.id_iv_ad, true)
            holder?.setVisible(holder.id_tv_title, false)
            holder?.setVisible(holder.id_tv_desc, false)
        } else {
            holder?.setVisible(holder.id_iv_ad, false)
            holder?.setVisible(holder.id_tv_title, true)
            holder?.setVisible(holder.id_tv_desc, true)
            holder?.id_tv_desc?.setText(data?.get(position))
        }
    }

    fun notifyData(data: ArrayList<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var id_iv_ad: ADImageView? = null
        var id_tv_title: TextView? = null
        var id_tv_desc: TextView? = null

        init {
            id_iv_ad = itemView?.findViewById(R.id.id_iv_ad)
            id_tv_title = itemView?.findViewById(R.id.id_tv_title)
            id_tv_desc = itemView?.findViewById(R.id.id_tv_desc)
        }

        fun setVisible(view: View?, isVisible: Boolean) {
            if (isVisible)
                view?.visibility = View.VISIBLE
            else
                view?.visibility = View.GONE
        }
    }
}