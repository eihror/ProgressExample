package com.eihror.amazingprogress

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.support.v4.content.res.ResourcesCompat
import android.util.TypedValue


class AmazingProgress(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), IAmazingProgress {

    private lateinit var innerBlock: LinearLayout
    private lateinit var txtTitle: TextView
    private lateinit var txtSubTitle: TextView
    private lateinit var progressBar: ProgressBar

    companion object {
        var mBlock: Int = R.color.baseProgress
        var mInnerBlock: Int = android.R.color.transparent

        var mShowTitle: Boolean = false
        var mTitleText: String = ""
        var mTitleSize: Float = 24F
        var mTitleFont: Int = -1
        var mTitleColor: Int = android.R.color.black

        var mShowSubTitle: Boolean = false
        var mSubTitleText: String = ""
        var mSubTitleSize: Float = 18F
        var mSubTitleFont: Int = -1
        var mSubTitleColor: Int = android.R.color.black

        var mProgressBarColor: Int = android.R.color.holo_green_light
        var mProgressBarSize: Int = 100

        var txtp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    }

    init {

        var ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.AmazingProgress)

        mBlock = ta.getColor(R.styleable.AmazingProgress_blockColor, ContextCompat.getColor(context, R.color.baseProgress))
        mInnerBlock = ta.getColor(R.styleable.AmazingProgress_innerBlockColor, ContextCompat.getColor(context, android.R.color.transparent))

        mShowTitle = ta.getBoolean(R.styleable.AmazingProgress_showTitle, false)
        mTitleText = if (ta.getString(R.styleable.AmazingProgress_titleText).isNullOrBlank() ||
                ta.getString(R.styleable.AmazingProgress_titleText).isNullOrEmpty()) {
            context.getString(R.string.default_title)
        } else {
            ta.getString(R.styleable.AmazingProgress_titleText)!!
        }
        mTitleSize = ta.getFloat(R.styleable.AmazingProgress_titleSize, 24F)
        mTitleFont = ta.getResourceId(R.styleable.AmazingProgress_titleFont, -1)
        mTitleColor = ta.getColor(R.styleable.AmazingProgress_titleColor, ContextCompat.getColor(context, android.R.color.black))

        mShowSubTitle = ta.getBoolean(R.styleable.AmazingProgress_showSubTitle, false)
        mSubTitleText = if (ta.getString(R.styleable.AmazingProgress_subTitleText).isNullOrEmpty() ||
                ta.getString(R.styleable.AmazingProgress_subTitleText).isNullOrBlank()) {
            context.getString(R.string.default_sub_title)
        } else {
            ta.getString(R.styleable.AmazingProgress_subTitleText)!!
        }
        mSubTitleSize = ta.getFloat(R.styleable.AmazingProgress_subTitleSize, 18F)
        mSubTitleFont = ta.getResourceId(R.styleable.AmazingProgress_subTitleFont, -1)
        mSubTitleColor = ta.getColor(R.styleable.AmazingProgress_subTitleColor, ContextCompat.getColor(context, android.R.color.black))

        mProgressBarColor = ta.getColor(R.styleable.AmazingProgress_progressColor, ContextCompat.getColor(context, android.R.color.holo_green_light))
        mProgressBarSize = ta.getDimensionPixelSize(R.styleable.AmazingProgress_progressSize, 100)

        ta.recycle()

        setViews()

    }

    private fun initMaster() {
        //Set Layout background color
        setBackgroundColor(ContextCompat.getColor(context, R.color.baseProgress))
        //Set gravity
        gravity = Gravity.CENTER
        //Set Orientation
        orientation = LinearLayout.VERTICAL
    }

    /**
     * Instanciate Inner Block on Progress Layout
     */
    private fun initInnerBlock() {
        innerBlock = LinearLayout(context)

        innerBlock.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        innerBlock.setPadding(50, 50, 50, 50)
        innerBlock.gravity = Gravity.CENTER
        innerBlock.orientation = LinearLayout.VERTICAL
    }

    /**
     * Instanciate Progress on Progress Layout
     */
    private fun initProgress() {
        progressBar = ProgressBar(context)
        val params = LinearLayout.LayoutParams(mProgressBarSize, mProgressBarSize)
        progressBar.layoutParams = params

    }

    /**
     * Instanciate Title on Progress Layout
     */
    private fun initTitle() {
        txtTitle = TextView(context)
        txtp.setMargins(10, 10, 10, 0)
        txtTitle.layoutParams = txtp
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleSize)
        txtTitle.setTextColor(mTitleColor)
        txtTitle.gravity = Gravity.CENTER

    }

    /**
     * Instanciate Sub Title on Progress Layout
     */
    private fun initSubTitle() {
        txtSubTitle = TextView(context)
        txtp.setMargins(10, 8, 10, 0)
        txtSubTitle.layoutParams = txtp
        txtSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mSubTitleSize)
        txtSubTitle.setTextColor(mSubTitleColor)
        txtSubTitle.gravity = Gravity.CENTER

    }

    fun show(show: Boolean) {
        this@AmazingProgress.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun setViews() {
        initMaster()
        initInnerBlock()
        initProgress()
        initTitle()
        initSubTitle()

        innerBlock.addView(progressBar)
        innerBlock.addView(txtTitle)
        innerBlock.addView(txtSubTitle)
        this@AmazingProgress.addView(innerBlock)

        setParams()
    }

    private fun setParams() {
        setBlockColor(mBlock)
        setInnerBlockColor(mInnerBlock)
        setShowTitle(mShowTitle)
        setTitleText(mTitleText)
        setTitleSize(mTitleSize)
        setTitleFont(mTitleFont)
        setTitleColor(mTitleColor)
        setShowSubTitle(mShowSubTitle)
        setSubTitleText(mSubTitleText)
        setSubTitleSize(mSubTitleSize)
        setSubTitleFont(mSubTitleFont)
        setSubTitleColor(mSubTitleColor)
        setProgressBarColor(mProgressBarColor)
        setProgressBarSize(mProgressBarSize)
    }

    private fun reloadLayout() {
        invalidate()
        requestLayout()
    }

    /**
     * Block Functions
     */

    override fun getBlockColor(): Int {
        return mBlock
    }

    override fun setBlockColor(color: Int) {
        mBlock = if (color != -1) color else R.color.baseProgress
        this@AmazingProgress.setBackgroundColor(mBlock)

        reloadLayout()
    }

    /**
     * Inner Block Functions
     */

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

    /**
     * Title Functions
     */

    override fun getShowTitle(): Boolean {
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
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleSize)
        reloadLayout()
    }

    override fun getTitleFont(): Int {
        return mTitleFont
    }

    override fun setTitleFont(font: Int) {
        if (font != -1 && mShowTitle) {
            mTitleFont = font
            val typeface = ResourcesCompat.getFont(context, mTitleFont)
            txtTitle.typeface = typeface

            reloadLayout()
        }
    }

    override fun getTitleColor(): Int {
        return mTitleColor
    }

    override fun setTitleColor(color: Int) {
        mTitleColor = if (color != -1) color else android.R.color.black
        txtTitle.setTextColor(mTitleColor)
    }

    /**
     * SubTitle Functions
     */

    override fun getShowSubTitle(): Boolean {
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
        txtSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mSubTitleSize)
        reloadLayout()
    }

    override fun getSubTitleFont(): Int {
        return mSubTitleFont
    }

    override fun setSubTitleFont(font: Int) {
        if (font != -1 && mShowSubTitle) {
            mSubTitleFont = font
            val typeface = ResourcesCompat.getFont(context, mSubTitleFont)
            txtSubTitle.typeface = typeface

            reloadLayout()
        }
    }

    override fun getSubTitleColor(): Int {
        return mSubTitleColor
    }

    override fun setSubTitleColor(color: Int) {
        mSubTitleColor = if (color != -1) color else android.R.color.black
        txtSubTitle.setTextColor(mSubTitleColor)
    }

    /**
     * Progressbar Functions
     */

    override fun getProgressBarColor(): Int {
        return mProgressBarColor
    }

    override fun setProgressBarColor(color: Int) {
        mProgressBarColor = if (color != -1) color else android.R.color.holo_green_light
        progressBar.indeterminateTintList = ColorStateList.valueOf(mProgressBarColor)

        reloadLayout()
    }

    override fun getProgressBarSize(): Int {
        return mProgressBarSize
    }

    override fun setProgressBarSize(size: Int) {
        mProgressBarSize = size
        val pxs = (mProgressBarSize * resources.displayMetrics.density).toInt()
        progressBar.minimumHeight = pxs
        progressBar.minimumWidth = pxs

        reloadLayout()
    }


}