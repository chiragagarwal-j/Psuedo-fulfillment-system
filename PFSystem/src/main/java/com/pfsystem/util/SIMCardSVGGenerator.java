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

public class SIMCardSVGGenerator {

    public static void Main(String[] args) {
        // Create a DOM implementation
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an SVG document
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Define the dimensions of the SVG
        int svgWidth = 240;
        int svgHeight = 160;

        // Set the width and height attributes of the SVG root element
        Element svgRoot = document.getDocumentElement();
        svgRoot.setAttribute("width", String.valueOf(svgWidth));
        svgRoot.setAttribute("height", String.valueOf(svgHeight));

        // Create an SVGGraphics2D instance
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Define the SIM card dimensions
        int simCardWidth = 200;
        int simCardHeight = 120;

        // Create a group for the SIM card elements
        Element simCardGroup = document.createElementNS(svgNS, "g");
        simCardGroup.setAttribute("transform", "translate(20, 20)");

        // Draw the SIM card outline
        Element simCardOutline = document.createElementNS(svgNS, "rect");
        simCardOutline.setAttribute("width", String.valueOf(simCardWidth));
        simCardOutline.setAttribute("height", String.valueOf(simCardHeight));
        simCardOutline.setAttribute("rx", "10");
        simCardOutline.setAttribute("ry", "10");
        simCardOutline.setAttribute("fill", "#FFFFFF");
        simCardGroup.appendChild(simCardOutline);

        // Draw the golden chip
        Element goldenChip = document.createElementNS(svgNS, "rect");
        goldenChip.setAttribute("x", "40");
        goldenChip.setAttribute("y", "80");
        goldenChip.setAttribute("width", "120");
        goldenChip.setAttribute("height", "20");
        goldenChip.setAttribute("fill", "gold");
        simCardGroup.appendChild(goldenChip);

        // Add the operator name
        Element operatorName = document.createElementNS(svgNS, "text");
        operatorName.setAttribute("x", "100");
        operatorName.setAttribute("y", "30");
        operatorName.setAttribute("font-family", "Arial");
        operatorName.setAttribute("font-size", "12");
        operatorName.setTextContent("Operator Name");
        simCardGroup.appendChild(operatorName);

        // Add IMSI
        Element imsiText = document.createElementNS(svgNS, "text");
        imsiText.setAttribute("x", "20");
        imsiText.setAttribute("y", "100");
        imsiText.setAttribute("font-family", "Arial");
        imsiText.setAttribute("font-size", "10");
        imsiText.setTextContent("IMSI: 123456789012345");
        simCardGroup.appendChild(imsiText);

        // Add ICCID
        Element iccidText = document.createElementNS(svgNS, "text");
        iccidText.setAttribute("x", "20");
        iccidText.setAttribute("y", "115");
        iccidText.setAttribute("font-family", "Arial");
        iccidText.setAttribute("font-size", "10");
        iccidText.setTextContent("ICCID: 8912345678901234567");
        simCardGroup.appendChild(iccidText);

        // Append the SIM card group to the SVG document
        svgRoot.appendChild(simCardGroup);

        // Output the SVG content to a file
        try {
            File svgFile = new File("simcard.svg");
            FileOutputStream fileOutputStream = new FileOutputStream(svgFile);
            OutputStreamWriter fileWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");

            // Draw the SVG content onto the graphics context before streaming
            svgGenerator.stream(fileWriter, true); // Set the useCSS parameter to true
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SIM card SVG template generated as simcard.svg");
    }
}
