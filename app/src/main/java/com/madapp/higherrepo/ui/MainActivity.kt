package com.madapp.higherrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.madapp.easydi.lib.inject
import com.madapp.higherrepo.R
import com.madapp.higherrepo.data.model.Product
import com.madapp.higherrepo.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel = inject()
    private lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        observeData()
    }

    private fun bindViews() {
        list = findViewById(R.id.list)
    }

    private fun observeData() {
        viewModel.products.observe(this, ::renderProducts)
    }

    private fun renderProducts(products: List<Product>) {
        list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products.map { it.name })
    }
}