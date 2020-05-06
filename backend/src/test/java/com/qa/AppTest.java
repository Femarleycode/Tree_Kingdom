package com.qa;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue(){
        App app = new app();
        String[] args = null;
        App.main(args);
        assertTrue( app instanceof App );
    }


}
