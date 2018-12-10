package com.eihror.amazingprogress

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

class AmazingProgress(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IAmazingProgress {

    private lateinit var innerBlock: LinearLayout
    private lateinit var txtTitle: TextView
    private lateinit var txtSubTitle: TextView
    private lateinit var progressBar: ProgressBar

    companion object {
        var mInnerBlock: Int = -1

        var mShowTitle: Boolean = false
        var mTitleText: String = "Title"
        var mTitleSize: Float = 24F

        var mShowSubTitle: Boolean = false
        var mSubTitleText: String = "Sub Title..."
        var mSubTitleSize: Float = 18F

    }

    private var lp: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
    )

    init {

        var ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.AmazingProgress)

        mInnerBlock = ta.getColor(R.styleable.AmazingProgress_innerBlockColor, -1)
        mShowTitle = ta.getBoolean(R.styleable.AmazingProgress_showTitle, false)
        mTitleText = if (ta.getString(R.styleable.AmazingProgress_titleText).isNullOrBlank() ||
                ta.getString(R.styleable.AmazingProgress_titleText).isNullOrEmpty()) {
            "Title"
        } else {
            ta.getString(R.styleable.AmazingProgress_titleText)
        }

        mTitleSize = ta.getFloat(R.styleable.AmazingProgress_titleSize, 24F)
        mShowSubTitle = ta.getBoolean(R.styleable.AmazingProgress_showSubTitle, false)
        mSubTitleText = if (ta.getString(R.styleable.AmazingProgress_subTitleText).isNullOrEmpty() ||
                ta.getString(R.styleable.AmazingProgress_subTitleText).isNullOrBlank()) {
            "Sub Title..."
        } else {
            ta.getString(R.styleable.AmazingProgress_subTitleText)
        }
        mSubTitleSize = ta.getFloat(R.styleable.AmazingProgress_subTitleSize, 18F)

        ta.recycle()

        initMaster()
        initInnerBlock()
        initProgress()
        initTitle()
        initSubTitle()

        innerBlock.addView(progressBar)
        innerBlock.addView(txtTitle)
        innerBlock.addView(txtSubTitle)
        this@AmazingProgress.addView(innerBlock)

        setInnerBlockColor(mInnerBlock)
        setShowTitle(mShowTitle)
        setTitleText(mTitleText)
        setTitleSize(mTitleSize)
        setShowSubTitle(mShowSubTitle)
        setSubTitleText(mSubTitleText)
        setSubTitleSize(mSubTitleSize)


    }

    private fun initMaster() {
        //Set Layout background color
        setBackgroundColor(ContextCompat.getColor(context, R.color.baseProgress))
        //Set gravity
        gravity = Gravity.CENTER
        //Set Orientation
        orientation = LinearLayout.VERTICAL
        visibility = View.GONE
    }

    private fun initInnerBlock() {
        innerBlock = LinearLayout(context)

        innerBlock.layoutParams = lp
        innerBlock.setPadding(50, 50, 50, 50)
        innerBlock.gravity = Gravity.CENTER
        innerBlock.orientation = LinearLayout.VERTICAL
    }

    private fun initProgress() {
        progressBar = ProgressBar(context)
    }

    /**
     * Set Title on Progress Layout
     */
    private fun initTitle() {
        txtTitle = TextView(context)
        txtTitle.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        txtTitle.textSize = mTitleSize
        txtTitle.gravity = Gravity.CENTER
    }

    private fun initSubTitle() {
        txtSubTitle = TextView(context)
        txtSubTitle.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        txtSubTitle.textSize = mSubTitleSize
        txtSubTitle.gravity = Gravity.CENTER

    }

    fun show(show: Boolean) {
        this@AmazingProgress.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun reloadLayout() {
        invalidate()
        requestLayout()
    }

    override fun getInnerBlockColor(): Int {
        return mInnerBlock
    }

    override fun setInnerBlockColor(color: Int) {
        mInnerBlock = if (color != -1) color else android.R.color.transparent

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.cornerRadii = floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f)
            shape.setColor(mInnerBlock)
            innerBlock.background = shape
        } else {
            innerBlock.setBackgroundColor(mInnerBlock)
        }

        reloadLayout()

    }

    override fun isShowTitle(): Boolean {
        return mShowTitle
    }

    override fun setShowTitle(show: Boolean) {
        mShowTitle = show
        txtTitle.visibility = if (mShowTitle) View.VISIBLE else View.GONE

        reloadLayout()
    }

    override fun getTitleText(): String {
        return mTitleText
    }

    override fun setTitleText(title: String) {
        mTitleText = title
        txtTitle.text = mTitleText

        reloadLayout()
    }

    override fun getTitleSize(): Float {
        return mTitleSize
    }

    override fun setTitleSize(size: Float) {
        mTitleSize = size
        txtTitle.textSize = mTitleSize
        reloadLayout()
    }

    override fun isShowSubTitle(): Boolean {
        return mShowSubTitle
    }

    override fun setShowSubTitle(show: Boolean) {
        mShowSubTitle = show
        txtSubTitle.visibility = if (mShowSubTitle) View.VISIBLE else View.GONE

        reloadLayout()
    }

    override fun getSubTitleText(): String {
        return mSubTitleText
    }

    override fun setSubTitleText(subTitle: String) {
        mSubTitleText = subTitle
        txtSubTitle.text = mSubTitleText

        reloadLayout()
    }

    override fun getSubTitleSize(): Float {
        return mSubTitleSize
    }

    override fun setSubTitleSize(size: Float) {
        mSubTitleSize = size
        txtSubTitle.textSize = mSubTitleSize
        reloadLayout()
    }


}