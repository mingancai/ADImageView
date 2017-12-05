package cmj.adimageview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet


/**
 * @author:  mingancai
 * @Time: 2017/12/5 0005.
 */

class ADImageView : AppCompatImageView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var mDx: Int = 0
    var mMinDx: Int = 0

    fun setDx(dx: Int) {
        if (drawable == null)
            return
        mDx = dx - mMinDx
        if (mDx <= 0)
            mDx = 0
        if (mDx > drawable.bounds.height() - mMinDx)
            mDx = drawable.bounds.height() - mMinDx

        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mMinDx = h
    }

    fun getDx(): Int {
        return mDx
    }

    override fun onDraw(canvas: Canvas?) {
        val w: Int = width
        val h: Int = width.times(drawable.intrinsicHeight).div(drawable.intrinsicWidth)
        drawable.bounds = Rect(0, 0, w, h)
        canvas?.save()
        canvas?.translate(0F, -getDx().toFloat())
        super.onDraw(canvas)
        canvas?.restore()
    }
}
