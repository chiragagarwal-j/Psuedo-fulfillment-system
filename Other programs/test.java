package com.pfsystem.util;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class test {

    public static void Main(String[] args) {
        // Create a DOM implementation
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an SVG document
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an SVGGraphics2D instance
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Create a rectangle
        Element rectangle = document.createElementNS(svgNS, "rect");
        rectangle.setAttribute("x", "50");
        rectangle.setAttribute("y", "50");
        rectangle.setAttribute("width", "100");
        rectangle.setAttribute("height", "50");
        rectangle.setAttribute("fill", "blue");

        // Append the rectangle to the SVG document
        document.getDocumentElement().appendChild(rectangle);

        // Output the SVG content to a file
        try {
            File svgFile = new File("simple.svg");
            FileOutputStream fileOutputStream = new FileOutputStream(svgFile);
            OutputStreamWriter fileWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");

            // Draw the SVG content onto the graphics context before streaming
            svgGenerator.stream(fileWriter, true); // Set the useCSS parameter to true
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Simple SVG generated as simple.svg");
    }
}
