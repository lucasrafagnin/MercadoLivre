package br.com.mercadolivre.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.mercadolivre.R
import br.com.mercadolivre.ui.activity.HomeActivity
import br.com.mercadolivre.ui.activity.PaymentActivity
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun end() {
        Intents.release()
    }

    @Test
    fun testMainButton() {
        rule.launchActivity(Intent())

        onView(ViewMatchers.withId(R.id.action)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())))
        onView(ViewMatchers.withId(R.id.price)).perform(ViewActions.typeText("100"))
        onView(ViewMatchers.withId(R.id.action)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun testOpenPaymentActivity() {
        rule.launchActivity(Intent())

        onView(ViewMatchers.withId(R.id.price)).perform(ViewActions.typeText("100"))
        onView(ViewMatchers.withId(R.id.action)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(PaymentActivity::class.java.name))
    }

}
