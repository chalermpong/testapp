package com.chalermpong.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val listView = view.findViewById<RecyclerView>(R.id.listView)
        listView.adapter = ListAdapter()
        listView.layoutManager = LinearLayoutManager(this.context)
    }

    class VH(view: View): RecyclerView.ViewHolder(view) {

    }

    class ListAdapter: RecyclerView.Adapter<VH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            if (viewType == 0) {
                return VH(MainActivity.MyTextView(parent.context).apply { this.text = "${viewType} -> ${this.hashCode()} "})
            } else {
                return VH(TextView(parent.context).apply { this.text = viewType.toString() })
            }
        }

        override fun onBindViewHolder(holder: VH, position: Int) {

        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun getItemViewType(position: Int): Int {
            return position % 10
        }

    }
}