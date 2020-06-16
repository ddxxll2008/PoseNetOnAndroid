package org.tensorflow.lite.examples.posenet

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView

class AnimationTestActivity : AppCompatActivity() {

    var leftBarricade: ImageView? = null
    var rightBarricade: ImageView? = null
    var leftRotateAnimSet: Animator? = null
    var rightRotateAnimSet: Animator? = null
    var leftTranslationYAnimSet: Animator? = null
    var rightTranslationYAnimSet: Animator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_animation)

        leftBarricade = findViewById(R.id.iv_left)
        rightBarricade = findViewById(R.id.iv_right)

        findViewById<Button>(R.id.btn_click).setOnClickListener {
            startLeftRotateAnim()
            startRightRotateAnim()
        }

        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            startLeftTransYAnim()
            startRightTransYAnim()
        }

        startLeftTransYAnim()
        startRightTransYAnim()
//        startLeftRotateAnim()
//        startRightRotateAnim()
    }

    private fun startLeftRotateAnim() {
        leftRotateAnimSet =
            AnimatorInflater.loadAnimator(this, R.animator.anim_left_rotate)
                .apply {
                    setTarget(leftBarricade)
                    start()
                }
    }

    private fun startRightRotateAnim() {
        rightRotateAnimSet =
            AnimatorInflater.loadAnimator(this, R.animator.anim_right_rotate)
                .apply {
                    setTarget(rightBarricade)
                    start()
                }
    }

    private fun startLeftTransYAnim() {
        leftTranslationYAnimSet =
            AnimatorInflater.loadAnimator(this, R.animator.anim_left_translation_y)
                .apply {
                    setTarget(leftBarricade)
                    start()
                }
    }

    private fun startRightTransYAnim() {
        rightTranslationYAnimSet =
            AnimatorInflater.loadAnimator(this, R.animator.anim_right_translation_y)
                .apply {
                    setTarget(rightBarricade)
                    start()
                }
    }
}