package com.blackpigstudio.classweek.test;

import android.test.ActivityInstrumentationTestCase2;

import com.blackpigstudio.classweek.main.ui.MainActivity;


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super("com.blackpigstudio.classweek.test", MainActivity.class);
    }



    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFoo() throws Exception
    {
        fail("must fail foo");
    }
}
