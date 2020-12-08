package tourguide.tourguidedemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button

import tourguide.tourguide.Overlay
import tourguide.tourguide.Pointer
import tourguide.tourguide.ToolTip
import tourguide.tourguide.TourGuide


class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Get parameters from main activity */
        val colorDemo = intent.getBooleanExtra(COLOR_DEMO, false)
        val gravityDemo = intent.getBooleanExtra(GRAVITY_DEMO, false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        val toolTip = ToolTip().setTitle("Welcome!").setDescription("Click on Get Started to begin...")

        // Setup pointer for demo
        val pointer = Pointer()
        if (colorDemo) {
            pointer.setColor(Color.RED)
        }
        val button1 = findViewById(R.id.button1) as Button
        val button2 = findViewById(R.id.button2) as Button
        if (gravityDemo) {
            pointer.setGravity(Gravity.BOTTOM or Gravity.RIGHT)
            button1.text = "BUTTON\n THAT IS\n PRETTY BIG"
        }

        // the return handler is used to manipulate the cleanup of all the tutorial elements
        val mTourGuideHandler = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                .setToolTip(toolTip)
                .setOverlay(Overlay().setBackgroundColor(Color.parseColor("#66FF0000")))
                .playOn(button1)

        button1.setOnClickListener { mTourGuideHandler.cleanUp() }
        button2.setOnClickListener { mTourGuideHandler.playOn(button1) }
    }

    companion object {
        val COLOR_DEMO = "color_demo"
        val GRAVITY_DEMO = "gravity_demo"
    }
}
