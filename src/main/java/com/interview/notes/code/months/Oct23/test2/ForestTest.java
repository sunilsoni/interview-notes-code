package com.interview.notes.code.months.Oct23.test2;

/**
 *

 Using the whiteboard (https://app.diagrams.net/) ask the candidate to draw a diagram to express the relationships between the following:

 Forest
 Tree
 Oak Tree
 Acorn
 Branch
 Leaf
 Trunk
 Root
 Bark
 Oak Leaf



 We aren’t looking for strict UML but the ability to express the concept to the audience by appropriate means.

 graph LR
 class Forest {
 + trees : List<Tree>
 }
 class Tree {
 + branches : List<Branch>
 + leaves : List<Leaf>
 + trunk : Trunk
 + roots : List<Root>
 + bark : Bark
 }
 class OakTree extends Tree
 class Acorn extends Seed
 class Branch
 class Leaf
 class Trunk
 class Root
 class Bark

 Forest -->* Tree
 Tree -->* Branch
 Tree -->* Leaf
 Tree --> 1 Trunk
 Tree -->* Root
 Tree --> 1 Bark
 OakTree --> 1 Acorn
 */
public class ForestTest {
}
