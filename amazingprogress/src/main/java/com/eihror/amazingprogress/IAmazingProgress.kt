package com.eihror.amazingprogress

interface IAmazingProgress {

    fun getBlockColor(): Int
    fun setBlockColor(color: Int)
    fun getInnerBlockColor(): Int
    fun setInnerBlockColor(color: Int)

    fun getShowTitle(): Boolean
    fun setShowTitle(show: Boolean)
    fun getTitleText(): String
    fun getTitleSize(): Float
    fun setTitleText(title: String)
    fun setTitleSize(size: Float)
    fun getTitleFont(): Int
    fun setTitleFont(font: Int)
    fun getTitleColor(): Int
    fun setTitleColor(color: Int)

    fun getShowSubTitle(): Boolean
    fun setShowSubTitle(show: Boolean)
    fun getSubTitleText(): String
    fun getSubTitleSize(): Float
    fun setSubTitleText(subTitle: String)
    fun setSubTitleSize(size: Float)
    fun getSubTitleFont(): Int
    fun setSubTitleFont(font: Int)
    fun getSubTitleColor(): Int
    fun setSubTitleColor(color: Int)

    fun getProgressBarColor(): Int
    fun setProgressBarColor(color: Int)
    fun getProgressBarSize(): Int
    fun setProgressBarSize(size: Int)
}