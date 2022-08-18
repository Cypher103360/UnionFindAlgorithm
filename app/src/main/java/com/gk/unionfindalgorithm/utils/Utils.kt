package com.gk.unionfindalgorithm.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object Utils {
    @JvmStatic
    lateinit var parent: IntArray
    lateinit var rank: IntArray

    private var k: Int = 0
    var parentArraySize: Int = 0


    fun connected(p: Int, q: Int): Boolean {
        return root(p) == root(q)
    }

    private fun root(i: Int): Int {
        k = i
        if (parent[k] != k) {
            parent[k] = root(parent[k])
        }
        return parent[k]
    }

    fun QuickFindUnion(n: Int) {
        parent = IntArray(n)
        rank = IntArray(n)
        parentArraySize = n

        for (i in 0 until n) {
            parent[i] = i
        }
    }

    fun union(p: Int, q: Int) {
        val i: Int = root(p)
        val j: Int = root(q)

        if (i == j) {
            return
        }
        if (rank[i] < rank[j]) {
            parent[i] = j

        } else if (rank[i] > rank[j]) {
            parent[j] = i

        } else {
            parent[j] = i
            rank[i] = rank[i] + 1
        }

    }

    fun setText(text: String, showConnectionText: TextView) {
        showConnectionText.visibility = View.VISIBLE
        showConnectionText.text = text
    }

    val showToast = { view: View ->
        val snackBarView = Snackbar.make(view,
            "Please enter the nodes between 0 to ${parentArraySize - 1}",
            Snackbar.LENGTH_LONG)

        val view1 = snackBarView.view
        val params = view1.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view1.layoutParams = params
        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBarView.show()

    }
}