/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved.
 * 
 * Originally written by Mark Watson and later review by Rambod Rahmani. The
 * following might be slightly different from the original one.
 * 
 */
package chap1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 *
 */
public class Problem
{
    // constants for appliance types
    enum Appliance
    {
	REFRIGERETOR, MICROWAVE, TV, DVD
    };

    // constants for problem types:
    enum ProblemType
    {
	NOT_RUNNING, SMOKING, ON_FIRE, MAKES_NOISE
    };

    // constants for environmental data:
    enum EnvironmentalDescription
    {
	CIRCUIT_BREAKER_OFF, LIGHTS_OFF_IN_ROOM
    };

    Appliance			   applianceType;

    List<ProblemType>		   problemTypes	     = new ArrayList<ProblemType>();

    List<EnvironmentalDescription> environmentalData = new ArrayList<EnvironmentalDescription>();

    // etc.
}

// Notes on Java Coding Styles Used in this Book
/*
 * Many of the example programs do not strictly follow common Java programming
 * idioms – this is usually done for brevity. For example, when a short example
 * is all in one Java package I will save lines of code and programing listing
 * space by not declaring class data private with public getters and setters;
 * instead, I will sometimes simply use package visibility as in this example.
 * 
 * Please understand that I do not advocate this style of programming in large
 * projects but one challenge in writing about software development is the
 * requirement to make the examples short and easily read and understood. Many
 * of the examples started as large code bases for my own projects that I
 * “whittled down” to a small size to show one or two specific techniques.
 * Forgoing the use of “getters and setters” in many of the examples is just
 * another way to shorten the examples.
 * 
 */
