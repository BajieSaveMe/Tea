package com.fude.david.tea.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.fude.david.base.ext.dip2px

import com.fude.david.tea.R

/**
 * zzy
 */

class TagBottomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var bottom_icon: Int = 0
    private var bottom_text: String? = null

    lateinit var mRelativeLayout: RelativeLayout
    lateinit var mTextView: TextView
    lateinit var mImageView: ImageView
    private var isStartAnimation = false
    private var isEndAnimation = false
    lateinit var endAnimSet: AnimatorSet
    lateinit var startAnimSet: AnimatorSet

    init {
        initView(context, attrs, defStyleAttr)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        clipToPadding = false
        clipChildren = false

        startAnimSet = AnimatorSet()
        endAnimSet = AnimatorSet()

        val a = context.obtainStyledAttributes(attrs, R.styleable.TagBottomView, defStyleAttr, 0)
        bottom_icon = a.getResourceId(R.styleable.TagBottomView_bottom_icon, -1)
        bottom_text = a.getString(R.styleable.TagBottomView_bottom_text)
        a.recycle()

        mRelativeLayout = RelativeLayout(context)
        mRelativeLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        mImageView = ImageView(context)
        mImageView.setImageResource(bottom_icon)
        val lp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(22.dip2px(context), 22.dip2px(context))
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL)
        mImageView.layoutParams = lp

        mTextView = TextView(context)
        mTextView.textSize = 10f
        mTextView.text = bottom_text
        mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))

        val lp1 = RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL)
        lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        mTextView.layoutParams = lp1

        mRelativeLayout.addView(mImageView)
        mRelativeLayout.addView(mTextView)
        addView(mRelativeLayout)
    }

    fun setImageResource(id: Int) {
        bottom_icon = id
        mImageView.setImageResource(bottom_icon)
    }

    /**
     * 不动画
     *
     * @param first
     */
    fun setCancelAnimation(first: Boolean) {
        isStartAnimation = first
        isEndAnimation = first
    }

    fun setMySelected(selected: Boolean) {
        if (selected) {
            mTextView.setTextColor(resources.getColor(R.color.color_3998E9))
            mImageView.isSelected = true
            if (isStartAnimation) {
                isStartAnimation = false
            } else {
                startAnimator()
            }
        } else {
            endAnimator()
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startAnimator() {

        if (!startAnimSet.isRunning) {
            if (!endAnimSet.isRunning) {
                //图片动画
                val animation1 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 1.2f)
                animation1.duration = 200
                val animation2 = ObjectAnimator.ofFloat(mImageView, "scaleX", 1f, 1.2f)
                animation2.duration = 200
                val animation3 = ObjectAnimator.ofFloat(mImageView, "alpha", 0.5f, 0.8f)
                animation3.duration = 200

                var animationColor: ObjectAnimator? = null
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    animationColor = ObjectAnimator.ofArgb(mTextView, "textColor", -0x413e3a, -0xc66717).setDuration(200)
                } else {
                    mTextView!!.setTextColor(resources.getColor(R.color.color_3998E9))
                }


                val animation4 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.2f, 1f)
                animation4.duration = 80
                val animation5 = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.2f, 1f)
                animation5.duration = 80
                val animation6 = ObjectAnimator.ofFloat(mImageView, "alpha", 0.8f, 1f)
                animation6.duration = 80

                val animSet1 = AnimatorSet()
                animSet1.playTogether(animation4, animation5, animation6)
                animSet1.startDelay = 200

                if (null != animationColor)
                    startAnimSet.playTogether(animation1, animation2, animation3, animationColor, animSet1)
                else
                    startAnimSet.playTogether(animation1, animation2, animation3, animSet1)

                startAnimSet.start()
            } else {
                endAnimSet.removeAllListeners()
                endAnimSet.cancel()
                mImageView.isSelected = true
                mTextView.setTextColor(resources.getColor(R.color.color_3998E9))
            }
        } else {
            startAnimSet.removeAllListeners()
            startAnimSet.cancel()
            mImageView.isSelected = true
            mTextView.setTextColor(resources.getColor(R.color.color_3998E9))
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun endAnimator() {
        if (mImageView.isSelected) {

            if (isEndAnimation) {
                mImageView.isSelected = false
                mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))
                isEndAnimation = false
            } else {
                if (!endAnimSet.isRunning) {
                    if (!startAnimSet.isRunning) {
                        val animator = ValueAnimator.ofFloat(1f, 0.6f).setDuration(120)
                        animator.addUpdateListener { animation ->
                            val value = animation.animatedValue as Float
                            mImageView.alpha = value
                            if (value == 0.6f) {
                                mImageView.isSelected = false
                                mImageView.alpha = 1f
                            }
                        }

                        var animationColor: ObjectAnimator? = null
                        if (android.os.Build.VERSION.SDK_INT >= 21) {
                            animationColor = ObjectAnimator.ofArgb(mTextView, "textColor", -0xc66717, -0x413e3a).setDuration(200)
                        } else {
                            mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))
                        }

                        if (null != animationColor)
                            endAnimSet.playTogether(animator, animationColor)
                        else
                            endAnimSet.playTogether(animator)

                        endAnimSet.start()
                    } else {
                        startAnimSet.removeAllListeners()
                        startAnimSet.cancel()
                        mImageView.isSelected = false
                        mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))
                    }

                } else {
                    endAnimSet.removeAllListeners()
                    endAnimSet.cancel()
                    mImageView.isSelected = false
                    mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))
                }
            }
        } else {
            mImageView.isSelected = false
            mTextView.setTextColor(resources.getColor(R.color.color_BEC1C6))
        }
    }
}
