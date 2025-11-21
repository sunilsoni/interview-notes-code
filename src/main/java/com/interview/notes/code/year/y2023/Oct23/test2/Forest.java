package com.interview.notes.code.year.y2023.Oct23.test2;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    private final List<Tree> trees;

    public Forest() {
        this.trees = new ArrayList<>();
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void addTree(Tree tree) {
        this.trees.add(tree);
    }
}