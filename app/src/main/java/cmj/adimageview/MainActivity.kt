package cmj.adimageview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var linearlayoutManager: LinearLayoutManager? = null
    var adapter: ListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        linearlayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView?.layoutManager = linearlayoutManager

        recyclerView?.adapter = adapter

        val data: ArrayList<String> = ArrayList()
        for (i in 0 until 100) {
            data.add((i + 1).toString() + "大吉大利晚上吃鸡")
        }

        adapter.notifyData(data)


        recyclerView?.addOnScrollListener(OnScrollListener())
    }

    inner class OnScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val fPos: Int? = linearlayoutManager?.findFirstVisibleItemPosition()
            val lPos: Int? = linearlayoutManager?.findLastCompletelyVisibleItemPosition()
            if (fPos != null && lPos != null) {
                for (i in fPos..lPos) {
                    val view: View? = linearlayoutManager?.findViewByPosition(i)
                    val adImageView: ADImageView? = view?.findViewById(R.id.id_iv_ad)
                    if (adImageView?.visibility == View.VISIBLE) {
                        adImageView.setDx(linearlayoutManager?.height?.minus(view.top)!!)
                    }
                }
            }
        }
    }
}
