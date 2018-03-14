package brandonjf.com.searchsy.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import brandonjf.com.searchsy.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_base)
    }

    fun addFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            this.add(R.id.cl_fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
