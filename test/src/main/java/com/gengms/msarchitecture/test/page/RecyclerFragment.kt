package com.gengms.msarchitecture.test.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gengms.architecture.navigation.nav
import com.gengms.msarchitecture.R
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnItemDecoration.setOnClickListener { nav().navigate(R.id.action_recyclerFragment_to_itemDecorationFragment) }
        btnRecyclerAdapter.setOnClickListener { nav().navigate(R.id.action_recyclerFragment_to_recyclerAdapterFragment) }
        btnSingleAdapter.setOnClickListener { nav().navigate(R.id.action_recyclerFragment_to_singleAdapterFragment) }
    }
}