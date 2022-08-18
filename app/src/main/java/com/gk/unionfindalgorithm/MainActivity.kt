package com.gk.unionfindalgorithm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gk.unionfindalgorithm.activities.AddConnectionActivity
import com.gk.unionfindalgorithm.utils.Utils.QuickFindUnion
import com.gk.unionfindalgorithm.utils.Utils.connected
import com.gk.unionfindalgorithm.utils.Utils.setText
import com.gk.unionfindalgorithm.utils.Utils.parentArraySize
import com.gk.unionfindalgorithm.utils.Utils.showToast
import kotlinx.android.synthetic.main.activity_add_connection.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.first_node
import kotlinx.android.synthetic.main.activity_main.second_node
import kotlinx.android.synthetic.main.activity_main.showConnectionText


class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_btn.setOnClickListener {
            startActivity(Intent(this, AddConnectionActivity::class.java))
        }

        QuickFindUnion(2001)

        checkConnectionBtn.setOnClickListener {
            val firstNode = first_node.text.toString()
            val secondNode = second_node.text.toString()

            if (TextUtils.isEmpty(firstNode)) {
                first_node.error = "Please enter any value"
                first_node.focusable
            } else if (TextUtils.isEmpty(secondNode)) {
                second_node.error = "Please enter any value"
                second_node.focusable
            } else {
                if (firstNode.toInt() < parentArraySize && secondNode.toInt() < parentArraySize) {
                    if (connected(firstNode.toInt(), secondNode.toInt())) {
                        setText("(${firstNode}, ${secondNode}) are connected nodes",
                            showConnectionText)
                    } else {
                        setText("(${firstNode}, ${secondNode}) are not connected nodes",
                            showConnectionText)
                    }
                } else {
                   showToast(it)
                }
            }

        }

    }

    override fun onStart() {
        super.onStart()
        first_node.text.clear()
        second_node.text.clear()
        showConnectionText.visibility = View.GONE
    }
}