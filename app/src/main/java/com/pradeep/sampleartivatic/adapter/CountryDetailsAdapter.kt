package com.pradeep.sampleartivatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pradeep.sampleartivatic.R
import com.pradeep.sampleartivatic.databinding.ItemCountryDetailsLayoutBinding
import com.pradeep.sampleartivatic.models.Row

class CountryDetailsAdapter(
    private var countryDetailList: List<Row>,
    private var context: Context
) : RecyclerView.Adapter<CountryDetailsAdapter.CountryDetailsViewHolder>() {
    lateinit var binding: ItemCountryDetailsLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailsViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country_details_layout,
            parent,
            false
        )
        return CountryDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryDetailsViewHolder, position: Int) {
        val countryDetails: Row = countryDetailList[position]
        holder.bind(countryDetails, context)
    }

    override fun getItemCount(): Int {
        return countryDetailList.size
    }

    class CountryDetailsViewHolder(private var binding: ItemCountryDetailsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryDetails: Row, context: Context) {
            if (countryDetails.imageHref != null) {
                binding.image.visibility = View.VISIBLE
                Glide.with(context).load(countryDetails.imageHref)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.image)
            } else {
                binding.image.visibility = View.GONE
            }
            if (countryDetails.title != null) {
                binding.title.visibility = View.VISIBLE
                binding.title.text = countryDetails.title
            } else {
                binding.title.visibility = View.GONE
            }
            if (countryDetails.description != null) {
                binding.description.visibility = View.VISIBLE
                binding.description.text = countryDetails.description
            } else {
                binding.description.visibility = View.GONE
            }
        }
    }
}