package com.grappim.bankpick.view.ui.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.grappim.bankpick.app.onBoarding.OnBoardingPages
import com.grappim.bankpick.view.databinding.LayoutItemOnBoardingBinding
import com.grappim.uikit.drawable

class OnBoardingAdapter(
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val items = listOf(
        OnBoardingPages.First,
        OnBoardingPages.Second,
        OnBoardingPages.Third
    )

    inner class OnBoardingViewHolder(
        private val binding: LayoutItemOnBoardingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.dotsIndicator.attachTo(viewPager2)
        }

        fun bind(page: OnBoardingPages) {
            with(binding) {
                ivMain.setImageDrawable(root.context.drawable(page.image))
                tvTitle.text = page.title
                tvDescription.text = page.description

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = LayoutItemOnBoardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}