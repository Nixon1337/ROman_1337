package com.example.flyingaround.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.flyingaround.R

class CompositeElements {

    public val progressBar = onView(withId(R.id.progressBar))
}