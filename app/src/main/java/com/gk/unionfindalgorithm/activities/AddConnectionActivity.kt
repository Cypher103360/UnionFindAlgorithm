package com.gk.unionfindalgorithm.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gk.unionfindalgorithm.R
import com.gk.unionfindalgorithm.utils.Utils.connected
import com.gk.unionfindalgorithm.utils.Utils.parentArraySize
import com.gk.unionfindalgorithm.utils.Utils.setText
import com.gk.unionfindalgorithm.utils.Utils.showToast
import com.gk.unionfindalgorithm.utils.Utils.union
import kotlinx.android.synthetic.main.activity_add_connection.*


class AddConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_connection)
        showConnectionText.visibility = View.GONE

        closeActivityBtn.setOnClickListener {
            onBackPressed()
        }
        add_more_btn.setOnClickListener {
            first_node.text.clear()
            second_node.text.clear()
            showConnectionText.visibility = View.GONE
            add_more_btn.visibility = View.GONE
        }
        add_connection_btn.setOnClickListener {
            val firstNode = first_node.text.toString().toInt()
            val secondNode = second_node.text.toString().toInt()

            if (TextUtils.isEmpty(firstNode.toString())) {
                first_node.error = "Please enter any value"
            } else if (TextUtils.isEmpty(secondNode.toString())) {
                second_node.error = "Please enter any value"
            } else {
                if (firstNode < parentArraySize && secondNode < parentArraySize) {
                    if (connected(firstNode, secondNode)) {
                        setText("(${firstNode}, ${secondNode}) are already connected!",
                            showConnectionText)
                    } else {
                        union(firstNode, secondNode)

                        setText("(${firstNode}, ${secondNode}) are now connected nodes",
                            showConnectionText)
                        add_more_btn.visibility = View.VISIBLE
                    }
                } else {
                    showToast(it)
                }
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}