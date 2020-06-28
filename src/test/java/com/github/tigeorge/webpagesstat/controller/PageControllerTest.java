package com.github.tigeorge.webpagesstat.controller;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class PageControllerTest extends TestCase {

    @Test
    public void testCountWords() throws Exception {

        PageController controller = new PageController();

        String testString = "aaa, bbb, ccc Aaa (aaA)Ccc";

        Map<String, Integer> map = controller.countWords(new ByteArrayInputStream(testString.getBytes()), "");

        Assert.assertEquals("{AAA=3, CCC=2, BBB=1}", map.toString());
    }
}