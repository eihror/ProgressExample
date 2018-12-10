package com.eihror.amazingprogress

interface IAmazingProgress {
    fun getInnerBlockColor() : Int
    fun setInnerBlockColor(color: Int)

    fun isShowTitle(): Boolean
    fun setShowTitle(show: Boolean)
    fun getTitleText(): String
    fun getTitleSize(): Float
    fun setTitleText(title: String)
    fun setTitleSize(size: Float)

    fun isShowSubTitle(): Boolean
    fun setShowSubTitle(show: Boolean)
    fun getSubTitleText(): String
    fun getSubTitleSize(): Float
    fun setSubTitleText(subTitle: String)
    fun setSubTitleSize(size: Float)
}