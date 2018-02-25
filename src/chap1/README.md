### Chapter 1 - Introduction
There are many fine books on Artificial Intelligence (AI) and good tutorials and software on the web. This book is intended for professional programmers who either already have an interest in AI or need to use specific AI technologies at work. The material is not intended as a complete reference for AI theory. Instead, I provide enough theoretical background to understand the example programs and to provide a launching point if you want or need to delve deeper into any of the topics covered.

#### Other JVM Languages
The Java language and JVM platform are very widely used so that techniques that you learn can be broadly useful. There are other JVM languages like JRuby, Clojure, Jython, and Scala that can use existing Java classes. While the examples in this book are written in Java you should have little trouble using my Java example classes and the open source libraries with these alternative JVM languages.

####  Use of Java Generics and Native Types
In general I usually use Java generics and the new collection classes for almost all of my Java programming. That is also the case for the examples in this book except when using native types and arrays provides a real performance advantage (for example, in the search examples).

Since arrays must contain reifiable types they play poorly with generics so I prefer not to mix coding styles in the same code base. There are some obvious cases where not using primitive types leads to excessive object creation and boxing/unboxing. That said, I expect Java compilers, Hotspot, and the JVM in general to keep getting better and this may be a non-issue in the future.

#### Book Summary
- Chapter 1 is the introduction for this book.
- Chapter 2 deals with heuristic search in two domains: two-dimensional grids (for example mazes) and graphs (defined by nodes and edges connecting nodes).
- Chapter 3 covers logic, knowledge representation, and reasoning using the Power-Loom system.
- Chapter 4 covers the Semantic Web. You will learn how to use RDF and RDFS data for knowledge representation and how to use the popular Sesame open source Semantic Web system.
- Chapter 5 introduces you to rule-based or production systems. We will use the open source Drools system to implement simple expert systems for solving “blocks world” problems and to simulate a help desk system.
- Chapter 6 gives an overview of Genetic Algorithms, provides a Java library, and solves a test problem. The chapter ends with suggestions for projects you might want to try.
- Chapter 7 introduces Hopfield and Back Propagation Neural Networks. In addition to Java libraries you can use in your own projects, we will use two Swing-based Java applications to visualize how neural networks are trained.
- Chapter 8 introduces you to the GPLed Weka project. Weka is a best of breed toolkit for solving a wide range of machine learning problems. 
- Chapter 9 covers several Statistical Natural Language Processing (NLP) techniques that I often use in my own work: processing text (tokenizing, stemming, and determining part of speech), named entity extraction from text, using the WordNet lexical database, automatically assigning tags to text, text clustering, three different approaches to spelling correction, and a short tutorial on Markov Models.
- Chapter 10 provides useful techniques for gathering and using information: using the Open Calais web services for extracting semantic information from text, information discovery in relational databases, and three different approaches to indexing and searching text.

#### Copyright Mark Watson
Practical Artificial Intelligence Programming With Java - Third Edition - Mark Watson Copyright 2001-2008 Mark Watson. All rights reserved. This work is licensed under a Creative Commons Attribution-Noncommercial-No Derivative Works Version 3.0 United States License.